package uk.co.alt236.thejsonappyouaskedfor.ui.components.home.recycler;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.Bind;
import me.grantland.widget.AutofitTextView;
import uk.co.alt236.thejsonappyouaskedfor.R;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.recyclerview.viewholder.BaseRecyclerViewHolder;


/*package*/ final class AlbumRecyclerViewHolder extends BaseRecyclerViewHolder {

    private static final int LAYOUT_ID = R.layout.list_item_album_overview;

    @Bind(R.id.image)
    ImageView imageView;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.subtitle)
    AutofitTextView subTitle;

    public AlbumRecyclerViewHolder(final View view) {
        super(view);
    }

    public static int getLayoutId() {
        return LAYOUT_ID;
    }

}