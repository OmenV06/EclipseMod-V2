package net.omen.eclipsemod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBindings {

    public static final String KEY_CATEGORY_ECLIPSE = "key.category.eclipsemod.eclipse";

    public static final String KEY_PLAYER_MENU = "key.eclipsemod.player_menu";
    public static final String KEY_ABILITY_BAR = "key.eclipsemod.ability_bar";

    public static final String KEY_ABILITY_ONE = "key.eclipsemod.ability_one";
    public static final String KEY_ABILITY_TWO = "key.eclipsemod.ability_two";
    public static final String KEY_ABILITY_THREE = "key.eclipsemod.ability_three";
    public static final String KEY_ABILITY_FOUR = "key.eclipsemod.ability_four";
    public static final String KEY_ABILITY_FIVE = "key.eclipsemod.ability_five";
    public static final String KEY_ABILITY_SIX = "key.eclipsemod.ability_six";
    public static final String KEY_ABILITY_SEVEN = "key.eclipsemod.ability_seven";
    public static final String KEY_ABILITY_EIGHT = "key.eclipsemod.ability_eight";
    public static final String KEY_ABILITY_NINE = "key.eclipsemod.ability_nine";


    public static final KeyMapping PLAYER_MENU = new KeyMapping(KEY_PLAYER_MENU, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_DOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_BAR = new KeyMapping(KEY_ABILITY_BAR, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_LEFT_ALT, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_ONE = new KeyMapping(KEY_ABILITY_ONE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_TWO = new KeyMapping(KEY_ABILITY_TWO, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_THREE = new KeyMapping(KEY_ABILITY_THREE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_FOUR = new KeyMapping(KEY_ABILITY_FOUR, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_FIVE = new KeyMapping(KEY_ABILITY_FIVE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_SIX = new KeyMapping(KEY_ABILITY_SIX, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_SEVEN = new KeyMapping(KEY_ABILITY_SEVEN, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_EIGHT = new KeyMapping(KEY_ABILITY_EIGHT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);

    public static final KeyMapping ABILITY_NINE = new KeyMapping(KEY_ABILITY_NINE, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_UNKNOWN, KEY_CATEGORY_ECLIPSE);
}
