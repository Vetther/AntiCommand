package dev.vetther.anticommand.listener;

import dev.vetther.anticommand.configuration.ConfigurationManager;
import dev.vetther.anticommand.group.GroupService;
import dev.vetther.anticommand.utils.CommandUtils;
import dev.vetther.anticommand.utils.LegacyUtils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.Optional;
import java.util.Set;

public class CommandPreProcessListener implements Listener {

    private final ConfigurationManager configurationManager;
    private final GroupService groupService;

    public CommandPreProcessListener(ConfigurationManager configurationManager, GroupService groupService) {
        this.configurationManager = configurationManager;
        this.groupService = groupService;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {

        if (event.getPlayer().hasPermission("anticommand.bypass")) {
            return;
        }

        Optional<Set<String>> allowedOpt = this.groupService.getCommands(event.getPlayer());
        if (!allowedOpt.isPresent()) {
            return;
        }

        Set<String> allowed = allowedOpt.get();
        String command = CommandUtils.getFixedName(event.getMessage());

        if (allowed.contains(command)) {
            return;
        }

        event.setCancelled(true);
        event.getPlayer().sendMessage(LegacyUtils.format(this.configurationManager.noCommandMessage()));
    }
}
