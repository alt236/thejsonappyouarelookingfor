package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.recyclerview.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.activity.BaseActivity;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher.ImageFetcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcherImpl;
import uk.co.alt236.thejsonappyouaskedfor.utils.AppLog;

public abstract class BaseRecyclerViewAdapter<D, VH extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<VH> {

    private final ImageFetcher mImageFetcher;
    private final Activity mActivity;
    private final IntentDispatcher mIntentDispatcher;
    private final List<D> mItems = new ArrayList<>();
    private boolean dataAdditionAttempted = false;
    private OnItemsChangedListener mListener;

    protected BaseRecyclerViewAdapter(final Activity activity,
                                      final ImageFetcher imageFetcher) {
        mActivity = activity;
        mImageFetcher = imageFetcher;
        mIntentDispatcher = createIntentDispatcher(activity);
    }

    protected BaseRecyclerViewAdapter(final Activity activity,
                                      final ImageFetcher imageFetcher,
                                      final IntentDispatcher intentDispatcher) {
        mActivity = activity;
        mImageFetcher = imageFetcher;
        mIntentDispatcher = intentDispatcher;
    }

    protected ImageFetcher getImageFetcher() {
        return mImageFetcher;
    }

    public Activity getActivity() {
        return mActivity;
    }

    public void addItems(final Collection<D> items) {
        dataAdditionAttempted = true;
        for (final D item : items) {
            addItem(item);
        }
    }

    public void addItem(final D item) {
        addItem(item, mItems.size());
    }

    public void addItem(final D item, final int position) {
        dataAdditionAttempted = true;
        if (isItemValid(item)) {
            mItems.add(item);
            this.notifyItemInserted(position);
            this.callItemsChangedListener();
        } else {
            AppLog.w("Not adding item in recycler because its invalid. Type: '" + getClassName(item) + "'");
        }
    }

    protected abstract boolean isItemValid(final D item);

    private void callItemsChangedListener() {
        if (mListener != null) {
            mListener.onItemsChanged();
        }
    }

    public void clearItems() {
        mItems.clear();
        this.notifyDataSetChanged();
        callItemsChangedListener();
    }

    protected IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    public D getItem(final int position) {
        return mItems.get(position);
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public boolean hasAttemptedDataAddition() {
        return dataAdditionAttempted;
    }

    public void removeItem(final D item) {
        final int position = mItems.indexOf(item);
        if (position > -1) {
            removeItem(position);
        }
    }

    public void removeItem(final int position) {
        mItems.remove(position);
        this.notifyItemRemoved(position);
        this.callItemsChangedListener();
    }

    public void setItems(final List<D> items) {
        AppLog.d("Will try to SET '" + items.size() + "' items of type " + getClassName(items));

        final List<D> filteredList = new ArrayList<>();

        for (final D item : items) {
            if (isItemValid(item)) {
                filteredList.add(item);
            } else {
                AppLog.w("Not adding item in recycler because its invalid. Type: '" + getClassName(item) + "'");
            }
        }

        AppLog.d("Actually setting '" + filteredList.size() + "' items of type " + getClassName(items));

        dataAdditionAttempted = true;
        mItems.clear();
        mItems.addAll(filteredList);
        this.notifyDataSetChanged();
        callItemsChangedListener();
    }

    public void setOnItemsChangedListener(final OnItemsChangedListener listener) {
        this.mListener = listener;
    }

    protected static IntentDispatcher createIntentDispatcher(final Activity activity) {
        if (activity instanceof BaseActivity) {
            return ((BaseActivity) activity).getIntentDispatcher();
        } else {
            return new IntentDispatcherImpl(activity);
        }
    }

    private static String getClassName(final Object object) {
        return object.getClass().getSimpleName();
    }

    private static String getClassName(final List<?> list) {
        if (list.isEmpty()) {
            return "<unknown>";
        } else {
            return getClassName(list.get(0));
        }
    }

    public interface OnItemsChangedListener {

        void onItemsChanged();
    }
}