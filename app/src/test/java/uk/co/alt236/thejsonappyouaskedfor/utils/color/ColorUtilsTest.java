package uk.co.alt236.thejsonappyouaskedfor.utils.color;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ColorUtilsTest {

    @Test
    public void testGetColorNameForHex() throws Exception {
        final String colorAliceBlue = "F0F8FF";

        final String resultAliceBlue = ColorUtils.getColorNameFromHex(colorAliceBlue);
        assertEquals("Alice Blue", resultAliceBlue);

    }
}