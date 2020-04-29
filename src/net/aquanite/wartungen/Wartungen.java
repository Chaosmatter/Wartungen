package net.aquanite.wartungen;

import net.aquanite.wartungen.commands.playerCommands;
import net.aquanite.wartungen.listener.playerListener;
import net.md_5.bungee.api.ServerPing;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Wartungen extends Plugin {

    private static Configuration config;
    private ServerPing.Protocol protocol;
    private boolean maintenance = false;
    public static String prefix = "§cMaintenance §8» ";

    @Override
    public void onEnable() {
        getProxy().getPluginManager().registerCommand(this, new playerCommands(this));
        getProxy().getPluginManager().registerListener(this, new playerListener(this));

        try {
            if (!getDataFolder().exists()) {
                getDataFolder().mkdir();
            }
            File file = new File(getDataFolder().getPath(), "config.yml");
            if (!file.exists()) {
                file.createNewFile();
            }
            Configuration config = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);
            config.set("MOTD", "&eDies ist eine MOTD.");
            ConfigurationProvider.getProvider(YamlConfiguration.class).save(config, file);
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        protocol = new ServerPing.Protocol("§c✘ §4Maintenance", 0);
    }

    @Override
    public void onDisable() {

    }

    public boolean isMaintenance() {
        return this.maintenance;
    }

    public void setMaintenance(boolean maintenance) {
        this.maintenance = maintenance;
    }

    public ServerPing.Protocol getProtocol() {
        return protocol;
    }

    public static Configuration getConfig() {
        return config;
    }



}
