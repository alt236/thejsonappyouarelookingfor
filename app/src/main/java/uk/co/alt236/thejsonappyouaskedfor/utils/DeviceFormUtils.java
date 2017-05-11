package uk.co.alt236.thejsonappyouaskedfor.utils;

import android.content.Context;

import uk.co.alt236.thejsonappyouaskedfor.R;

/**
 */
public class DeviceFormUtils {
    private final boolean isTablet;
    private final boolean isLandscape;
    private final boolean isSw600dp;
    private final boolean isSw720dp;

    public DeviceFormUtils(final Context context) {
        isTablet = context.getResources().getBoolean(R.bool.isTablet);
        isLandscape = context.getResources().getBoolean(R.bool.isLandscape);
        isSw600dp = context.getResources().getBoolean(R.bool.is600);
        isSw720dp = context.getResources().getBoolean(R.bool.is720);
    }

    public int getColumnsForScreen() {
        if (isLandscape()) {
            if (isSw720dp() || isSw600dp()) {
                return 3;
            } else {
                return 2;
            }
        } else if (isSw600dp()) {
            return 2;
        } else {
            return 1;
        }
    }

    public boolean isLandscape() {
        return isLandscape;
    }

    public boolean isSw720dp() {
        return isSw720dp;
    }

    public boolean isSw600dp() {
        return isSw600dp;
    }

    public boolean isTablet() {
        return isTablet;
    }
}
