package uk.co.alt236.thejsonappyouaskedfor.loader.data.error;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonError;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.responses.CommonErrorKind;

/**
 *
 */
public final class LoaderUtils {


    private LoaderUtils() {
        // DO NOT INSTANTIATE
    }

    public static CommonError getErrorFromHttpCode(final int code) {

        if (code >= 500) {
            return CommonError.from(
                    "Internal Server Error",
                    CommonErrorKind.INTERNAL_SERVER_ERROR
            );
        } else if (code >= 300) {
            return CommonError.from(
                    "Invalid Request",
                    CommonErrorKind.INVALID_REQUEST_PARAMETERS
            );
        } else {
            return CommonError.from(
                    "Communication Error",
                    CommonErrorKind.COMMUNICATION
            );
        }

    }

}
