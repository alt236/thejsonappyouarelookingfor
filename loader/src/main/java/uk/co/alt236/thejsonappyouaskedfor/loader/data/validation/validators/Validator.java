package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators;


import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.results.ValidationResult;

/**
 *
 */
public interface Validator<T> {
    ValidationResult validate(T item);
}
