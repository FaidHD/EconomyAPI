package de.faidhd.economysystem.spigot;

import de.faidhd.economysystem.spigot.manager.ConfigManager;
import de.faidhd.economysystem.spigot.manager.DatabaseManager;
import de.faidhd.economysystem.spigot.manager.EconomyManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public class SpigotEconomySystemPlugin extends JavaPlugin {

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
