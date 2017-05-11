package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.recyclerview.viewbinder;

import android.content.Context;
import android.view.View;

import uk.co.alt236.thejsonappyouaskedfor.data.models.base.BaseUiModel;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.recyclerview.viewholder.BaseRecyclerViewHolder;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.imagefetcher.ImageFetcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcher;
import uk.co.alt236.thejsonappyouaskedfor.utils.view.ViewUtils;


/**
 *
 */
public abstract class BaseRecyclerViewBinder<VH extends BaseRecyclerViewHolder, D extends BaseUiModel> {

    private final Context mContext;
    private final ImageFetcher mImageFetcher;
    private final IntentDispatcher mIntentDispatcher;

    protected BaseRecyclerViewBinder(final Context context,
                                     final ImageFetcher imageFetcher,
                                     final IntentDispatcher intentDispatcher) {
        this.mContext = context;
        this.mImageFetcher = imageFetcher;
        this.mIntentDispatcher = intentDispatcher;
    }

    protected Context getContext() {
        return mContext;
    }

    protected ImageFetcher getImageFetcher() {
        return mImageFetcher;
    }

    protected IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    protected abstract void bind(final VH holder, final D item);

    protected abstract void reset(final VH holder);

    @SuppressWarnings("MethodMayBeStatic")
    protected void showView(final View view, final boolean show) {
        ViewUtils.showView(view, show);
    }

}
