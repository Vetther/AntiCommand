package dev.vetther.vettheranticommand.utils;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.serializer.legacy.LegacyComponentSerializer;

import static dev.vetther.vettheranticommand.AntiCommand.MINIMESSAGE;

public class LegacyUtils {

    public static final LegacyComponentSerializer LEGACY_SERIALIZER = LegacyComponentSerializer.legacyAmpersand();

    private LegacyUtils() {
    }

    public static String format(String text) {
        return LEGACY_SERIALIZER.serialize(MINIMESSAGE.deserialize(text));
    }

    public static Component legacyToComponent(String text) {
        return LEGACY_SERIALIZER.deserialize(text).decoration(TextDecoration.ITALIC, false);
    }

}
