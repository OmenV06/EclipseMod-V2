package net.omen.eclipsemod.power.powers;

import net.minecraft.server.level.ServerPlayer;
import net.omen.eclipsemod.power.PowerBase;

public class EmptyPower implements PowerBase {

    @Override
    public void applyPower(ServerPlayer player) {

    }

    @Override
    public void removePower(ServerPlayer player) {

    }

    @Override
    public String getPowerName() {
        return "EmptyPower";
    }

    @Override
    public String getPowerTier() {
        return "EmptyTier";
    }

    @Override
    public String getPowerDescription() {
        return "EmptyDescription";
    }

}
