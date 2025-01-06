package net.omen.eclipsemod.client.effects.vfx.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.particle.ParticleEngine;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterParticleProvidersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.client.effects.vfx.particle.fireball.FireballLargeParticle;
import net.omen.eclipsemod.client.effects.vfx.particle.fireball.FireballMediumParticle;
import net.omen.eclipsemod.client.effects.vfx.particle.fireball.FireballSmallParticle;
import net.omen.eclipsemod.client.effects.vfx.particle.firewave.FirewaveLargeParticle;
import net.omen.eclipsemod.client.effects.vfx.particle.firewave.FirewaveMediumParticle;
import net.omen.eclipsemod.client.effects.vfx.particle.firewave.FirewaveSmallParticle;

@Mod.EventBusSubscriber(modid = EclipseMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ParticleClientRegistry {

    @SubscribeEvent
    public static void onRegisterParticleProviders(RegisterParticleProvidersEvent event) {
        ParticleEngine engine = Minecraft.getInstance().particleEngine;

        event.registerSpriteSet(ParticleRegistry.FIREBALL_SMALL.get(), FireballSmallParticle.FireballSmallParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.FIREBALL_MEDIUM.get(), FireballMediumParticle.FireballMediumParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.FIREBALL_LARGE.get(), FireballLargeParticle.FireballLargeParticleFactory::new);

        event.registerSpriteSet(ParticleRegistry.FIREWAVE_SMALL.get(), FirewaveSmallParticle.FirewaveSmallParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.FIREWAVE_MEDIUM.get(), FirewaveMediumParticle.FirewaveMediumParticleFactory::new);
        event.registerSpriteSet(ParticleRegistry.FIREWAVE_LARGE.get(), FirewaveLargeParticle.FirewaveLargeParticleFactory::new);


    }
}