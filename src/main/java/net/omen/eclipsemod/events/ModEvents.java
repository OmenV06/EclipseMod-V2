package net.omen.eclipsemod.events;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.RegisterCapabilitiesEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.power.PlayerPowerCapability;
import net.omen.eclipsemod.power.PlayerPowerProvider;
import net.omen.eclipsemod.power.PowerBase;
import net.omen.eclipsemod.power.PowerPool;
import net.omen.eclipsemod.power.powers.EmptyPower;

@Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID)
public class ModEvents {

    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {

        event.register(PlayerPowerCapability.class);

    }

    @SubscribeEvent
    public static void attachCapabilities(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            if (!event.getObject().getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).isPresent()) {
                event.addCapability(new ResourceLocation(EclipseMod.MOD_ID, "eclipse_power"), new PlayerPowerProvider());
            }
        }
    }

    @SubscribeEvent
    public static void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        if (event.getEntity() instanceof ServerPlayer player) {
            player.getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(capability -> {
                if (capability.getPower().equalsIgnoreCase("none")) {
                    PowerBase randomPower = PowerPool.getRandomObtainablePower();
                    capability.setPower(randomPower.getPowerName());
                    randomPower.applyPower(player);
                }
            });
        }
    }

    @SubscribeEvent
    public static void onPlayerClone(PlayerEvent.Clone event) {

            event.getOriginal().reviveCaps();

            event.getOriginal().getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(oldCap -> {
                event.getEntity().getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(newCap -> {
                    newCap.setPower(oldCap.getPower());

                    PowerPool.REGISTERED_POWERS.getOrDefault(newCap.getPower(), new EmptyPower())
                            .applyPower((ServerPlayer) event.getEntity());

                });
            });

            event.getOriginal().invalidateCaps();

    }

}
