package uk.co.alt236.thejsonappyouaskedfor.loader.data.responses;

/**
 * Callback interface
 *
 * @param <T> object
 */
public interface CommonCallback<T> {

    void onFailure(final CommonError error);

    void onSuccess(final CommonDeliverable<T> deliverable);
}