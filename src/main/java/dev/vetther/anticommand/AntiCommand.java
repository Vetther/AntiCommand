package dev.vetther.anticommand;

import dev.vetther.anticommand.configuration.ConfigurationManager;
import dev.vetther.anticommand.group.GroupFactory;
import dev.vetther.anticommand.group.GroupService;
import dev.vetther.anticommand.listener.CommandPreProcessListener;
import dev.vetther.anticommand.listener.CommandSendListener;
import dev.vetther.anticommand.utils.LegacyColorProcessor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class AntiCommand extends JavaPlugin {

    public static MiniMessage MINIMESSAGE;

    @Override
    public void onEnable() {

        /* configuration */
        saveDefaultConfig();
        ConfigurationManager configurationManager = new ConfigurationManager(this.getConfig(), Bukkit.spigot().getConfig());

        /* factories */
        GroupFactory groupFactory = new GroupFactory();

        /* services */
        GroupService groupService = new GroupService(groupFactory, configurationManager);
        MINIMESSAGE = MiniMessage.builder()
                .postProcessor(new LegacyColorProcessor())
                .build();

        /* listeners */
        Bukkit.getPluginManager().registerEvents(new CommandSendListener(groupService), this);
        Bukkit.getPluginManager().registerEvents(new CommandPreProcessListener(configurationManager, groupService), this);

    }
}
