package uk.co.alt236.thejsonappyouaskedfor.ui.core.common.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import uk.co.alt236.thejsonappyouaskedfor.R;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.notifications.ActivityNotificationController;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.common.notifications.SnackBarNotificationController;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcher;
import uk.co.alt236.thejsonappyouaskedfor.ui.core.intent.dispatch.IntentDispatcherImpl;
import uk.co.alt236.thejsonappyouaskedfor.utils.AppLog;

/**
 *
 */
public abstract class BaseActivity extends AppCompatActivity {

    private ActivityNotificationController mNotificationController;
    private IntentDispatcher mIntentDispatcher;

    protected void addContentFragmentIfMissing(final Fragment fragment, final String fragmentTag) {
        if (getSupportFragmentManager().findFragmentByTag(fragmentTag) == null) {
            final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment, fragmentTag);
            fragmentTransaction.commit();
        }
    }

    public ActivityNotificationController getNotificationController() {
        return mNotificationController;
    }

    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, final Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        for (final Fragment fragment : getSupportFragmentManager().getFragments()) {
            fragment.onActivityResult(requestCode, resultCode, data);
        }
    }

    public IntentDispatcher getIntentDispatcher() {
        return mIntentDispatcher;
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onCreateCommon();
    }

    private void onCreateCommon() {
        mIntentDispatcher = new IntentDispatcherImpl(this);
    }

    @Override
    public void setContentView(final int layoutResID) {
        super.setContentView(layoutResID);
        setupActionbar();
        mNotificationController = new SnackBarNotificationController(this);
    }

    @Override
    public void setContentView(final View view) {
        throw new UnsupportedOperationException("Only setContentView(layoutResID) is supported");
    }

    private void setupActionbar() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_actionbar);
        if (toolbar != null) {
            setSupportActionBar(toolbar);
            this.setTitle(getString(R.string.app_name));
        } else {
            AppLog.w(this.getClass().getName() + ": Null toolbar");
        }
    }

    @Override
    public void setTitle(final CharSequence title) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
    }

}
