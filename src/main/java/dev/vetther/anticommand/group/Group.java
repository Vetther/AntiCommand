package dev.vetther.anticommand.group;

import java.util.Set;

public class Group {

    private final String name;
    private final String permission;
    private final Set<String> commands;
    private final boolean bypass;

    Group(String name, String permission, Set<String> commands, boolean bypass) {
        this.name = name;
        this.permission = permission;
        this.commands = commands;
        this.bypass = bypass;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public Set<String> getCommands() {
        return commands;
    }

    public boolean hasBypass() {
        return this.bypass;
    }
}
