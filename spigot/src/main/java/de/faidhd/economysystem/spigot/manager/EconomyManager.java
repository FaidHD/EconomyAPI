package de.faidhd.economysystem.spigot.manager;

import de.faidhd.economysystem.api.IEcoManager;
import de.faidhd.economysystem.api.IEcoUser;
import de.faidhd.economysystem.spigot.SpigotEconomySystemPlugin;
import de.faidhd.economysystem.spigot.objects.EconomyUser;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class EconomyManager implements IEcoManager {

    private final SpigotEconomySystemPlugin plugin;

    public EconomyManager(SpigotEconomySystemPlugin plugin) {
        this.plugin = plugin;
    }


    @Override
    public CompletableFuture<IEcoUser> getCoinsUser(UUID uuid) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                PreparedStatement ps = plugin.getDatabaseManager().getConnection().prepareStatement("SELECT * FROM `coins` WHERE `uuid` = ?;");
                ps.setString(1, uuid.toString());
                ps.execute();
                ResultSet rs = ps.getResultSet();
                double balance = 0;
                if (rs.next())
                    balance = rs.getDouble("balance");
                rs.close();
                ps.close();
                return new EconomyUser(plugin, uuid, balance);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return new EconomyUser(plugin, uuid, 0);
        });
    }

    @Override
    public void modifyIEcoUser(UUID uuid, Consumer<IEcoUser> consumer) {
        getCoinsUser(uuid).thenAccept(iEcoUser -> {
            consumer.accept(iEcoUser);
            saveCoinsUser(iEcoUser);
        });
    }

    @Override
    public void saveCoinsUser(IEcoUser iEcoUser) {
        CompletableFuture.runAsync(() -> {
            try {
                PreparedStatement ps = plugin.getDatabaseManager().getConnection().prepareStatement("INSERT INTO `coins` VALUES(?, ?) ON DUPLICATE KEY UPDATE `balance` = VALUE(`balance`);");
                ps.setString(1, iEcoUser.getUUID().toString());
                ps.setDouble(2, iEcoUser.getCoins());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
