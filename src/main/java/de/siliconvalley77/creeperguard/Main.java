package de.siliconvalley77.creeperguard;

import de.siliconvalley77.creeperguard.Listeners.CreeperListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        Bukkit.getPluginManager().registerEvents(new CreeperListener(this), this);

        // Generate the default configuration file
        generateDefaultConfig();
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    // Method to generate the configuration file with default values
    private void generateDefaultConfig() {
        File configFile = new File(getDataFolder(), "config.yml");

        if (!configFile.exists()) {
            // Create the plugin's data folder if it doesn't exist
            if (!configFile.getParentFile().exists()) {
                configFile.getParentFile().mkdirs();
            }

            // Copy the default config.yml from the plugin JAR
            try (InputStream defaultConfigStream = getResource("config.yml")) {
                Files.copy(defaultConfigStream, configFile.toPath());
            } catch (IOException e) {
                getLogger().severe("Error while creating the default config.yml file: " + e.getMessage());
            }
        }
    }
}
