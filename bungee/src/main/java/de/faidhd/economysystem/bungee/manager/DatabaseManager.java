package de.faidhd.economysystem.bungee.manager;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import de.faidhd.economysystem.bungee.BungeeEconomySystemPlugin;
import de.faidhd.economysystem.bungee.objects.ConfigObject;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CompletableFuture;

public class DatabaseManager {

    private final BungeeEconomySystemPlugin plugin;

    private HikariDataSource hikariDataSource;

    public DatabaseManager(BungeeEconomySystemPlugin plugin) {
        this.plugin = plugin;
        initConnection();
    }

    private void initConnection() {
        ConfigObject config = plugin.getConfigManager().getConfigObject();
        CompletableFuture.runAsync(() -> {
            HikariConfig hikariConfig = new HikariConfig();
            hikariConfig.setJdbcUrl("jdbc:mysql://" + config.getHostname() + ":" + config.getPort() + "/" + config.getDatabase());
            hikariConfig.setUsername(config.getUsername());
            hikariConfig.setPassword(config.getPassword());
            hikariConfig.setDriverClassName("com.mysql.cj.jdbc.Driver");
            hikariConfig.setMinimumIdle(5);
            hikariConfig.setMaximumPoolSize(50);
            hikariConfig.setConnectionTimeout(10000);
            hikariConfig.setIdleTimeout(600000);
            hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
            hikariConfig.addDataSourceProperty("prepStmtCacheSize", "250");
            hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
            hikariConfig.addDataSourceProperty("autoReconnect", "true");

            this.hikariDataSource = new HikariDataSource(hikariConfig);

            try {
                getConnection().prepareStatement("CREATE TABLE IF NOT EXISTS coins(`uuid` VARCHAR(64) NOT NULL, `balance` BIGINT DEFAULT 0, PRIMARY KEY(`uuid`))").execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public Connection getConnection() {
        try {
            return hikariDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
