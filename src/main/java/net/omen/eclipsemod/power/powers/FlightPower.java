package net.omen.eclipsemod.power.powers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.power.PowerBase;

@Mod.EventBusSubscriber
public class FlightPower implements PowerBase {

    @Override
    public void applyPower(ServerPlayer player) {

        addFlightAbility(player);

    }

    @Override
    public void removePower(ServerPlayer player) {

        removeFlightAbility(player);

    }

    @Override
    public String getPowerName() {
        return "Flight";
    }

    @Override
    public String getPowerTier() {
        return "A";
    }

    @Override
    public String getPowerDescription() {
        return "Allows Flight and Prevents Fall Damage";
    }

    public void addFlightAbility (ServerPlayer player) {



    }

    public void removeFlightAbility (ServerPlayer player) {



    }



}
