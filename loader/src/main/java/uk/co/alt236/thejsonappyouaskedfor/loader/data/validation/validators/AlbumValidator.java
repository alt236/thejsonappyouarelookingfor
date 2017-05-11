package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators;


import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.results.ValidationResult;
import uk.co.alt236.thejsonappyouaskedfor.loader.utils.DataUtils;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

/**
 *
 */
public class AlbumValidator implements Validator<Album> {

    @Override
    public ValidationResult validate(final Album item) {
        if (item == null) {
            return new ValidationResult(false, "Null Album");
        } else if (item.getId() == null) {
            return new ValidationResult(false, "Null Album id");
        } else if (!DataUtils.isURL(item.getUrl())) {
            return new ValidationResult(false, "Invalid Album image url");
        } else if (!DataUtils.isURL(item.getThumbnailUrl())) {
            return new ValidationResult(false, "Invalid Album thumbnail url");
        } else {
            return new ValidationResult(true);
        }
    }
}
