package uk.co.alt236.thejsonappyouaskedfor.loader.data.responses;

import java.util.Calendar;

/**
 *
 */
public class CommonTimedElementImpl implements CommonTimedElement {

    private final long mCreationTime;

    public CommonTimedElementImpl() {
        this.mCreationTime = Calendar.getInstance().getTimeInMillis();
    }

    public long getCreationTime() {
        return mCreationTime;
    }
}
