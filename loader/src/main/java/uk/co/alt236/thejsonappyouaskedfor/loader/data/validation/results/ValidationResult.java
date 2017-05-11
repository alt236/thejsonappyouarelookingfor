package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.results;


import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonErrorKind;

/**
 *
 */
public class ValidationResult {

    private final CommonError mError;
    private final boolean mIsValid;


    public ValidationResult(final boolean isValid, final CommonError error) {
        this.mError = error;
        this.mIsValid = isValid;
    }

    public ValidationResult(final boolean isValid, final String errorMessage) {
        this.mError = CommonError.from(errorMessage, CommonErrorKind.INVALID_CONTENT);
        this.mIsValid = isValid;
    }

    public ValidationResult(final boolean isValid) {
        this.mError = null;
        this.mIsValid = isValid;
    }

    public CommonError getError() {
        return mError;
    }

    public boolean isValid() {
        return mIsValid;
    }
}
