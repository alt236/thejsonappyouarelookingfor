package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators;

import org.junit.Test;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.ValidatorProcessor;
import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.ValidatorProcessorImpl;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ValidatorProcessorTest {

    @Test
    public void testGetValidator() throws Exception {
        final ValidatorProcessor processor = new ValidatorProcessorImpl();

        final Validator validator = processor.getValidator(Album.class);
        assertNotNull("Null validator", validator);
        assertTrue(AlbumValidator.class.isInstance(validator));

    }
}