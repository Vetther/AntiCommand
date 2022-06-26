package dev.vetther.anticommand.configuration;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.HashSet;
import java.util.Set;

public class ConfigurationManager {

    private final FileConfiguration config;
    private final String noCommandMessage;

    public ConfigurationManager(FileConfiguration config, YamlConfiguration spigotConfig) {
        this.config = config;
        this.noCommandMessage = spigotConfig.getString("messages.unknown-command");
    }

    public Set<String> getGroupsNames() {

        ConfigurationSection groups = config.getConfigurationSection("groups");

        if (groups == null) {
            throw new NullPointerException("Groups are not defined in config!");
        }

        return groups.getKeys(false);
    }

    public Set<String> getGroupCommands(String groupName) {

        return new HashSet<>(this.config.getStringList("groups." + groupName));
    }

    public boolean hasBypass(String groupName) {
        return this.config.contains("groups." + groupName + ".bypass") && this.config.getBoolean("groups." + groupName + ".bypass");
    }

    public String noCommandMessage() {
        return this.noCommandMessage;
    }

}
