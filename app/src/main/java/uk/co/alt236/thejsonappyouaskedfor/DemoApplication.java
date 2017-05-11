package uk.co.alt236.thejsonappyouaskedfor;

import android.app.Application;

import uk.co.alt236.thejsonappyouaskedfor.di.ComponentStore;
import uk.co.alt236.thejsonappyouaskedfor.di.Injector;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.CoreLog;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.LogEngine;
import uk.co.alt236.thejsonappyouaskedfor.utils.AppLog;

/**
 *
 */
public class DemoApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        AppLog.d("Application initialised");
        Injector.setComponentStore(new ComponentStore(this));
        CoreLog.setLogEngine(new LogEngine() {
            @Override
            public void d(final String tag, final String message) {
                AppLog.d(message);
            }

            @Override
            public void e(final String tag, final String message) {
                AppLog.e(message);
            }

            @Override
            public void e(final String tag, final String message, final Exception e) {
                AppLog.e(message, e);
            }

            @Override
            public void w(final String tag, final String message) {
                AppLog.w(message);
            }

            @Override
            public void w(final String tag, final String message, final Exception e) {
                AppLog.w(message, e);
            }
        });
    }

}
