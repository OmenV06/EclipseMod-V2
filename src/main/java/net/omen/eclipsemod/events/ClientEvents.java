package net.omen.eclipsemod.events;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.client.gui.PlayerMenu;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.ExecuteAbilityC2SPacket;
import net.omen.eclipsemod.power.AbilityBar;
import net.omen.eclipsemod.util.KeyBindings;

public class ClientEvents {

    @Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID, value = Dist.CLIENT)
    public static class ClientForgeEvents {

        @SubscribeEvent
        public static void onKeyInput(InputEvent.Key event) {

            if (KeyBindings.PLAYER_MENU.consumeClick()) {
                Minecraft.getInstance().setScreen(new PlayerMenu(Component.literal("Player Menu")));
            }

            if (KeyBindings.ABILITY_BAR.consumeClick()) {
                AbilityBar.toggleAbilityBar();
            }

            handleAbilityKeyInput();

        }

        private static void handleAbilityKeyInput() {

            if (KeyBindings.ABILITY_ONE.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(1));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 0;
                }
            }

            if (KeyBindings.ABILITY_TWO.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(2));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 1;
                }
            }

            if (KeyBindings.ABILITY_THREE.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(3));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 2;
                }
            }

            if (KeyBindings.ABILITY_FOUR.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(4));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 3;
                }
            }

            if (KeyBindings.ABILITY_FIVE.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(5));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 4;
                }
            }

            if (KeyBindings.ABILITY_SIX.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(6));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 5;
                }
            }

            if (KeyBindings.ABILITY_SEVEN.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(7));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 6;
                }
            }

            if (KeyBindings.ABILITY_EIGHT.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(8));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 7;
                }
            }

            if (KeyBindings.ABILITY_NINE.consumeClick()) {
                if (AbilityBar.isActive()) {
                    ModPackets.sendToServer(new ExecuteAbilityC2SPacket(9));
                } else {
                    Minecraft.getInstance().player.getInventory().selected = 8;
                }
            }
        }

    }

    @Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {

        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {

            event.register(KeyBindings.PLAYER_MENU);
            event.register(KeyBindings.ABILITY_BAR);

            event.register(KeyBindings.ABILITY_ONE);
            event.register(KeyBindings.ABILITY_TWO);
            event.register(KeyBindings.ABILITY_THREE);
            event.register(KeyBindings.ABILITY_FOUR);
            event.register(KeyBindings.ABILITY_FIVE);
            event.register(KeyBindings.ABILITY_SIX);
            event.register(KeyBindings.ABILITY_SEVEN);
            event.register(KeyBindings.ABILITY_EIGHT);
            event.register(KeyBindings.ABILITY_NINE);

        }

    }

}
