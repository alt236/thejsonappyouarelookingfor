package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation;

import java.util.HashMap;
import java.util.Map;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators.AlbumValidator;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators.Validator;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

/**
 *
 */
public final class ValidatorProcessorImpl implements ValidatorProcessor {

    private final Map<Class<?>, Validator> mValidators;

    public ValidatorProcessorImpl() {
        mValidators = new HashMap<>();

        // add the validators
        mValidators.put(Album.class, new AlbumValidator());

    }

    @Override
    public <T> Validator<T> getValidator(final Class<T> clazz) {
        //noinspection unchecked
        return mValidators.get(clazz);
    }
}
