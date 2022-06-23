package dev.vetther.vettheranticommand.group;

import java.util.Set;

public class GroupFactory {

    Group create(String name, String permission, Set<String> commands, boolean bypass) {
        return new Group(name, permission, commands, bypass);
    }
}
