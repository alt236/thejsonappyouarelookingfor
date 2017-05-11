package uk.co.alt236.thejsonappyouaskedfor.loader.data.responses;

/**
 * Wrapper object for loading results.
 * Contains the object and the datasource type
 */
public final class CommonDeliverable<T> implements CommonTimedElement {

    private final T mObjectContent;
    private final CommonTimedElement mTimedElement;

    private CommonDeliverable(final T deliverable) {
        this.mObjectContent = deliverable;
        this.mTimedElement = new CommonTimedElementImpl();
    }

    public T getContent() {
        return mObjectContent;
    }

    @Override
    public long getCreationTime() {
        return mTimedElement.getCreationTime();
    }


    public static <T> CommonDeliverable<T> from(final T deliverable) {
        return new CommonDeliverable<>(deliverable);
    }

}
