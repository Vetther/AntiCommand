package dev.vetther.vettheranticommand.group;

import dev.vetther.vettheranticommand.configuration.ConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GroupService {

    public static final String DEFAULT_PERMISSION = "anticommand.group.default";
    private final Set<Group> groups;
    private final ConfigurationManager configurationManager;
    private final GroupFactory groupFactory;

    public GroupService(GroupFactory groupFactory, ConfigurationManager configurationManager) {
        this.groupFactory = groupFactory;
        this.configurationManager = configurationManager;
        this.groups = cacheGroups();
    }

    private Set<Group> cacheGroups() {

        Set<Group> groups = new HashSet<>();

        for (String groupName : this.configurationManager.getGroupsNames()) {

            String groupPermission = "anticommand.group." + groupName;
            Set<String> groupCommands = this.configurationManager.getGroupCommands(groupName);
            boolean bypass = this.configurationManager.hasBypass(groupName);

            Group group = this.groupFactory.create(groupName, groupPermission, groupCommands, bypass);
            groups.add(group);
        }

        return groups;
    }

    public Optional<Set<String>> getCommands(Player player) {

        Set<String> commands = new HashSet<>();

        for (Group group : this.groups) {

            if (!player.hasPermission(group.getPermission()) && !group.getPermission().equalsIgnoreCase(DEFAULT_PERMISSION)) {
                continue;
            }

            if (group.hasBypass()) {
                return Optional.empty();
            }

            commands.addAll(group.getCommands());
        }

        return Optional.of(commands);
    }
}
