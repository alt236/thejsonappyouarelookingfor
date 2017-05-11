package uk.co.alt236.optional.debug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.inject.Inject;

import uk.co.alt236.thejsonappyouaskedfor.di.Injector;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.DataProvider;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonDeliverable;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

/**
 *
 */
public class JsonAlbumFragment extends BaseJsonViewFragment {

    @Inject
    protected DataProvider dataProvider;

    private List<Album> mItems;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponentStore().getAndroidAwareComponent().inject(this);
    }

    @Override
    protected void loadData() {
        dataProvider.getPhotos(new CommonCallback<List<Album>>() {
            @Override
            public void onFailure(final CommonError error) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        setText(error.getErrorMessage());
                    }
                });

            }

            @Override
            public void onSuccess(final CommonDeliverable<List<Album>> deliverable) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mItems = deliverable.getContent();
                        setUp();
                    }
                });

            }
        });

    }

    @Override
    protected void setUp() {

        final Set<String> set = new TreeSet<>();

        int i = 0;
        for (final Album item : mItems) {

            final String id = "Item No. " + i++ + ": ID= " + item.getId();

            set.add(id);
        }

        final List<String> list = new ArrayList<>();
        list.addAll(set);

        final ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, list);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setSpinnerAdapter(dataAdapter);

    }

    @Override
    public void onItemSelected(final AdapterView<?> parent, final View view, final int position, final long id) {
        if (mItems != null) {
            setText(mItems.get(position));
        }
    }

    @Override
    public void onNothingSelected(final AdapterView<?> parent) {
        setText("Nothing selected");
    }

    public static Fragment newInstance() {
        return new JsonAlbumFragment();
    }
}
