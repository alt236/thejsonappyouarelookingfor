package uk.co.alt236.thejsonappyouaskedfor.utils.color;

/**
 * Helper Class of ColorUtils. In order to lookup color name
 *
 * @author Xiaoxiao Li
 */
/*package*/ class ColorName {
    public int r, g, b;
    public String name;

    public ColorName(final String name, final int r, final int g, final int b) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.name = name;
    }

    public int computeMSE(final int pixR, final int pixG, final int pixB) {
        return ((pixR - r) * (pixR - r) + (pixG - g) * (pixG - g) + (pixB - b)
                * (pixB - b)) / 3;
    }

    public int getR() {
        return r;
    }

    public int getG() {
        return g;
    }

    public int getB() {
        return b;
    }

    public String getName() {
        return name;
    }
}