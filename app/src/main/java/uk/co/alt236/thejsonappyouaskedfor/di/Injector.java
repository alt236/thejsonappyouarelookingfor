package uk.co.alt236.thejsonappyouaskedfor.di;

public final class Injector {

    private static ComponentStore componentStore;

    private Injector() {
        // NOOP
    }

    public static ComponentStore getComponentStore() {
        return componentStore;
    }

    public static void setComponentStore(final ComponentStore store) {
        componentStore = store;
    }
}
