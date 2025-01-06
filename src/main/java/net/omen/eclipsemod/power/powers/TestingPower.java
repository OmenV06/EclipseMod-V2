package net.omen.eclipsemod.power.powers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.network.chat.Component;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.ExamplePacketC2S;
import net.omen.eclipsemod.power.AbilityBase;
import net.omen.eclipsemod.power.PowerBase;
import java.util.List;

public class TestingPower implements PowerBase {

    @Override
    public void applyPower(ServerPlayer player) {

    }

    @Override
    public void removePower(ServerPlayer player) {

    }

    @Override
    public String getPowerName() {
        return "TestingPower";
    }

    @Override
    public String getPowerTier() {
        return "A";
    }

    @Override
    public String getPowerDescription() {
        return "A power for testing purposes with 2 abilities.";
    }

    private static final List<AbilityBase> ABILITIES = List.of(new TestingAbility(), new TestingAbilityTwo());

    @Override
    public List<AbilityBase> getAbilities() {
        return ABILITIES;
    }

    public static class TestingAbility implements AbilityBase {
        @Override
        public void executeAbility(ServerPlayer player) {
            ModPackets.sendToServer(new ExamplePacketC2S());
        }

        @Override
        public String getAbilityName() {
            return "Spawn Cow";
        }

    }

    public static class TestingAbilityTwo implements AbilityBase {
        @Override
        public void executeAbility(ServerPlayer player) {
            player.sendSystemMessage(Component.literal("Ability Two Activated"));
        }

        @Override
        public String getAbilityName() {
            return "Send message";
        }

    }

}