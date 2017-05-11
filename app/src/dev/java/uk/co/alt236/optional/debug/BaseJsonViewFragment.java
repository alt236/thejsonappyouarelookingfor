package uk.co.alt236.optional.debug;

import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.gson.Gson;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.alt236.thejsonappyouaskedfor.R;
import uk.co.alt236.thejsonappyouaskedfor.di.Injector;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.fragment.BaseFragment;

/**
 *
 */
public abstract class BaseJsonViewFragment extends BaseFragment implements AdapterView.OnItemSelectedListener {

    private static final String EMPTY_TEXT = "Empty";
    @Inject
    protected Gson gson;
    @Bind(R.id.spinner)
    Spinner mSpinner;
    @Bind(R.id.text)
    TextView mTextView;

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.getComponentStore().getAndroidAwareComponent().inject(this);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater,
                             final ViewGroup container,
                             final Bundle savedInstanceState) {

        /** Inflating the layout for this fragment **/
        return inflater.inflate(R.layout.fragment_default_spinner_text, container, false);

    }

    @Override
    public void onViewCreated(final View view, final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        mTextView.setText(EMPTY_TEXT);
        mSpinner.setOnItemSelectedListener(this);
        new Thread(new Runnable() {
            @Override
            public void run() {
                loadData();
            }
        }).start();

    }

    protected abstract void loadData();

    protected void setSpinnerAdapter(final ArrayAdapter<String> dataAdapter) {
        if (dataAdapter != null) {
            mSpinner.setAdapter(dataAdapter);
        }
    }

    protected void setText(final Object object) {
        final String message = "*Item*\n" + gson.toJson(object);
        setText(message);
    }

    protected void setText(final CharSequence contents) {

        mTextView.setText(contents);
        Linkify.addLinks(mTextView, Linkify.WEB_URLS);
        mTextView.setMovementMethod(LinkMovementMethod.getInstance());

    }

    protected abstract void setUp();

}
