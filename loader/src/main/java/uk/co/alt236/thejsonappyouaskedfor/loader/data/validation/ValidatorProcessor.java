package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation;


import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators.Validator;

/**
 *
 */
public interface ValidatorProcessor {
    <T> Validator<T> getValidator(Class<T> clazz);
}
