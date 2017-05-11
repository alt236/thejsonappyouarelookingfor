package uk.co.alt236.thejsonappyouaskedfor.loader.data.loaders.callbacks;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.error.LoaderUtils;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonCallback;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonDeliverable;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonErrorKind;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.ValidatorProcessorImpl;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.results.ValidationResult;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators.Validator;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.log.CoreLog;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

public class AlbumListCallback implements Callback<List<Album>> {
    private final CommonCallback<List<Album>> masterCallback;

    public AlbumListCallback(final CommonCallback<List<Album>> masterCallback) {
        this.masterCallback = masterCallback;
    }

    @Override
    public void onResponse(final Call<List<Album>> call,
                           final Response<List<Album>> response) {

        // checking 200 is good enough for the demo!
        if (response.code() == 200) {
            final ValidationResult result = validateResults(response.body());
            if (result.isValid()) {
                CoreLog.d(String.format(Locale.UK, "Received %d valid Albums", response.body().size()));
                masterCallback.onSuccess(CommonDeliverable.from(response.body()));
            } else {
                CoreLog.w(String.format("Validation error for albums %s", result.getError().getErrorMessage()));
                masterCallback.onFailure(result.getError());
            }
        } else {
            // create an internal error from the code
            CoreLog.e("Communication error while retrieving albums: code= " + response.code());
            masterCallback.onFailure(LoaderUtils.getErrorFromHttpCode(response.code()));
        }

    }

    @Override
    public void onFailure(final Call<List<Album>> call, final Throwable t) {
        CoreLog.e("Communication error while retrieving albums: throwable " + t.getMessage());
        masterCallback.onFailure(CommonError.from(t.getMessage(), CommonErrorKind.COMMUNICATION));
    }

    private static ValidationResult validateResults(final List<Album> items) {

        final Validator<Album> validator = new ValidatorProcessorImpl().getValidator(Album.class);

        for (final Album item : items) {
            final ValidationResult result = validator.validate(item);
            if (!result.isValid()) {
                return result;
            }
        }

        return new ValidationResult(true);
    }

}