package net.omen.eclipsemod.power;

import net.omen.eclipsemod.power.powers.*;

import java.util.*;

public class PowerPool {

    public static final Map<String, PowerBase> REGISTERED_POWERS = new HashMap<>();
    private static final Map<String, PowerBase> OBTAINABLE_POWERS = new HashMap<>();
    static {

        registerPower(new EmptyPower(), false);

        registerPower(new VitalityPower(), true);
        registerPower(new BoostPower(), true);
        registerPower(new BurstPower(), true);
        registerPower(new FlightPower(), false);

        registerPower(new TestingPower(), false);

    }

    private static void registerPower(PowerBase power, boolean isObtainable) {
        REGISTERED_POWERS.put(power.getPowerName(), power);
        if (isObtainable) {
            OBTAINABLE_POWERS.put(power.getPowerName(), power);
        }
    }

    public static PowerBase getRandomObtainablePower() {
        List<PowerBase> powers = new ArrayList<>(OBTAINABLE_POWERS.values());
        if (powers.isEmpty()) {
            return new EmptyPower();
        }
        return powers.get(new Random().nextInt(powers.size()));
    }

}