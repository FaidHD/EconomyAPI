package de.faidhd.economysystem.api;

public final class EconomyProvider {

    private static IEcoManager iEcoManager;

    public static void setIEcoManager(IEcoManager iEcoManager) {
        EconomyProvider.iEcoManager = iEcoManager;
    }

    public static IEcoManager getIEcoManager() {
        return iEcoManager;
    }
}
