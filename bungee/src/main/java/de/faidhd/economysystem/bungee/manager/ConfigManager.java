package de.faidhd.economysystem.bungee.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import de.faidhd.economysystem.bungee.BungeeEconomySystemPlugin;
import de.faidhd.economysystem.bungee.objects.ConfigObject;
import lombok.Getter;
import lombok.SneakyThrows;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ConfigManager {

    private final BungeeEconomySystemPlugin plugin;

    private File file;
    @Getter
    private ConfigObject configObject;

    public ConfigManager(BungeeEconomySystemPlugin plugin) {
        this.plugin = plugin;
        loadConfig();
    }

    @SneakyThrows
    private void loadConfig() {
        this.file = new File(plugin.getDataFolder(), "config.json");
        Gson gson = new GsonBuilder().disableHtmlEscaping().setPrettyPrinting().create();
        if (!file.exists()) {
            file.getParentFile().mkdir();
            file.createNewFile();
            OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            this.configObject = ConfigObject.builder().build();
            writer.write(gson.toJson(configObject));
            writer.flush();
            writer.close();
        } else
            this.configObject = gson.fromJson(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8), ConfigObject.class);
    }
}
