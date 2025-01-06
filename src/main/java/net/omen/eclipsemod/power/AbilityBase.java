package net.omen.eclipsemod.power;

import net.minecraft.server.level.ServerPlayer;

public interface AbilityBase {
    void executeAbility(ServerPlayer player);
    String getAbilityName();
}
