package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.fragment;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.activity.BaseActivity;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.notifications.ActivityNotificationController;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcherImpl;

public abstract class BaseFragment extends Fragment {
    private IntentDispatcher mIntentDispatcher;

    protected IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    protected ActivityNotificationController getNotificationController() {
        return ((BaseActivity) getActivity()).getNotificationController();
    }

    @Override
    public void onAttach(final Context context) {
        super.onAttach(context);
        if (context instanceof Activity) {
            mIntentDispatcher = new IntentDispatcherImpl((Activity) context);
        }
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

}