package net.omen.eclipsemod.power;

import net.minecraft.server.level.ServerPlayer;

import java.util.ArrayList;
import java.util.List;

public interface PowerBase {
    void applyPower(ServerPlayer player);
    void removePower(ServerPlayer player);
    String getPowerName();
    String getPowerTier();
    String getPowerDescription();

    default List<AbilityBase> getAbilities() {
        return new ArrayList<>();
    }

}

