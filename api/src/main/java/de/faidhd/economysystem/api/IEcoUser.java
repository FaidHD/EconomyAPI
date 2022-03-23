package de.faidhd.economysystem.api;

import java.util.UUID;

public interface IEcoUser {

    void addCoins(double coins);

    void removeCoins(double coins);

    void setCoins(double coins);

    double getCoins();

    UUID getUUID();

}
