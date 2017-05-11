package uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.validators;

import org.junit.Before;
import org.junit.Test;

import uk.co.alt236.thejsonappyouaskedfor.loader.data.validation.results.ValidationResult;
import uk.co.alt236.thejsonappyouaskedfor.model.album.Album;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AlbumValidatorTest {

    private AlbumValidator mValidator;

    @Before
    public void setUp() {
        mValidator = new AlbumValidator();
    }


    @Test
    public void testValidateValidAlbum() throws Exception {

        final Album album1 = mock(Album.class);

        when(album1.getId()).thenReturn(1);
        when(album1.getUrl()).thenReturn("https://www.facebook.com/");
        when(album1.getThumbnailUrl()).thenReturn("https://www.facebook2.com/");

        final ValidationResult result = mValidator.validate(album1);
        assertNotNull("Null validation result", result);
        assertTrue("Was expecting true validation result. Got " + result.getError(), result.isValid());

    }

    @Test
    public void testValidateInValidAlbum() throws Exception {

        final Album album2 = mock(Album.class);

        when(album2.getId()).thenReturn(2);
        when(album2.getUrl()).thenReturn(null);

        final ValidationResult result = mValidator.validate(album2);
        assertNotNull("Null validation result", result);
        assertFalse("Was expecting false validation result", result.isValid());

    }
}