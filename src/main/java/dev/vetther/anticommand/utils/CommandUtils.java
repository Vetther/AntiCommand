package dev.vetther.anticommand.utils;

public class CommandUtils {

    public static String getFixedName(String command) {
        String[] parts = command.split(" ");
        if (parts[0].startsWith("/"))
            parts[0] = parts[0].substring(1);
        return parts[0];
    }
}
