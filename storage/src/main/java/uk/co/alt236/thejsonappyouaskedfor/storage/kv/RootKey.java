package uk.co.alt236.thejsonappyouaskedfor.storage.kv;

public class RootKey {
    private final String type;
    private final int version;

    public RootKey(final String type,
                   final int version) {
        this.type = type;
        this.version = version;
    }

    public String getType() {
        return type;
    }

    public int getVersion() {
        return version;
    }
}
