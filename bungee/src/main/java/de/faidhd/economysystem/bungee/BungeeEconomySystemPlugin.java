package de.faidhd.economysystem.bungee;

import de.faidhd.economysystem.bungee.manager.ConfigManager;
import de.faidhd.economysystem.bungee.manager.DatabaseManager;
import de.faidhd.economysystem.bungee.manager.EconomyManager;
import lombok.Getter;
import net.md_5.bungee.api.plugin.Plugin;

@Getter
public class BungeeEconomySystemPlugin extends Plugin {

    private ConfigManager configManager;
    private DatabaseManager databaseManager;
    private EconomyManager economyManager;

    @Override
    public void onEnable() {
        this.configManager = new ConfigManager(this);
        this.databaseManager = new DatabaseManager(this);
        this.economyManager = new EconomyManager(this);
    }
}
