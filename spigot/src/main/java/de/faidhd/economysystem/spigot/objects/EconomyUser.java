package de.faidhd.economysystem.spigot.objects;

import de.faidhd.economysystem.api.ChangeCause;
import de.faidhd.economysystem.api.IEcoUser;
import de.faidhd.economysystem.api.events.BukkitEconomyUserUpdateEvent;
import de.faidhd.economysystem.spigot.SpigotEconomySystemPlugin;

import java.util.UUID;

public class EconomyUser implements IEcoUser {

    private final SpigotEconomySystemPlugin plugin;

    private UUID uuid;
    private double coins;

    public EconomyUser(SpigotEconomySystemPlugin plugin, UUID uuid, double coins) {
        this.plugin = plugin;
        this.uuid = uuid;
        this.coins = coins;
    }

    @Override
    public void addCoins(double coins) {
        double oldCoins = this.coins;
        this.coins += coins;
        plugin.getServer().getPluginManager().callEvent(new BukkitEconomyUserUpdateEvent(this, oldCoins, this.coins, ChangeCause.ADD));
    }

    @Override
    public void removeCoins(double coins) {
        double oldCoins = this.coins;
        this.coins -= coins;
        plugin.getServer().getPluginManager().callEvent(new BukkitEconomyUserUpdateEvent(this, oldCoins, this.coins, ChangeCause.REMOVE));
    }

    @Override
    public void setCoins(double coins) {
        plugin.getServer().getPluginManager().callEvent(new BukkitEconomyUserUpdateEvent(this, this.coins, coins, ChangeCause.SET));
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
