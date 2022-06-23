package dev.vetther.vettheranticommand.listener;

import dev.vetther.vettheranticommand.group.GroupService;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class CommandSendListener implements Listener {

    private final GroupService groupService;

    public CommandSendListener(GroupService groupService) {
        this.groupService = groupService;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerCommandSend(PlayerCommandSendEvent event) {

        if (event.getPlayer().hasPermission("vettheranticommand.bypass")) {
            return;
        }

        Set<String> toRemove = new HashSet<>();
        Optional<Set<String>> allowedOpt = this.groupService.getCommands(event.getPlayer());

        if (!allowedOpt.isPresent()) {
            return;
        }

        Set<String> allowed = allowedOpt.get();

        for (String command : event.getCommands()) {

            if (!allowed.contains(command)) {
                toRemove.add(command);
            }
        }

        for (String command : toRemove) {
            event.getCommands().remove(command);
        }

    }
}
