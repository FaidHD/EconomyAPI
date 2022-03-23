package de.faidhd.economysystem.bungee.objects;

import de.faidhd.economysystem.api.ChangeCause;
import de.faidhd.economysystem.api.IEcoUser;
import de.faidhd.economysystem.api.events.BungeeEconomyUserUpdateEvent;
import de.faidhd.economysystem.bungee.BungeeEconomySystemPlugin;

import java.util.UUID;

public class EconomyUser implements IEcoUser {

    private final BungeeEconomySystemPlugin plugin;

    private UUID uuid;
    private double coins;

    public EconomyUser(BungeeEconomySystemPlugin plugin, UUID uuid, double coins) {
        this.plugin = plugin;
        this.uuid = uuid;
        this.coins = coins;
    }

    @Override
    public void addCoins(double coins) {
        double oldCoins = this.coins;
        this.coins += coins;
        plugin.getProxy().getPluginManager().callEvent(new BungeeEconomyUserUpdateEvent(this, oldCoins, this.coins, ChangeCause.ADD));
    }

    @Override
    public void removeCoins(double coins) {
        double oldCoins = this.coins;
        this.coins -= coins;
        plugin.getProxy().getPluginManager().callEvent(new BungeeEconomyUserUpdateEvent(this, oldCoins, this.coins, ChangeCause.REMOVE));
    }

    @Override
    public void setCoins(double coins) {
        plugin.getProxy().getPluginManager().callEvent(new BungeeEconomyUserUpdateEvent(this, this.coins, coins, ChangeCause.SET));
        this.coins = coins;
    }

    @Override
    public double getCoins() {
        return coins;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
