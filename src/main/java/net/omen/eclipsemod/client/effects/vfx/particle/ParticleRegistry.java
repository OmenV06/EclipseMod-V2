package net.omen.eclipsemod.client.effects.vfx.particle;

import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;

public class ParticleRegistry {
    public static final DeferredRegister<ParticleType<?>> PARTICLES = DeferredRegister.create(ForgeRegistries.PARTICLE_TYPES, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        PARTICLES.register(eventBus);
    }

    public static final RegistryObject<SimpleParticleType> FIREBALL_SMALL = PARTICLES.register("fireball_small",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIREBALL_MEDIUM = PARTICLES.register("fireball_medium",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIREBALL_LARGE = PARTICLES.register("fireball_large",
            () -> new SimpleParticleType(true));

    public static final RegistryObject<SimpleParticleType> FIREWAVE_SMALL = PARTICLES.register("firewave_small",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIREWAVE_MEDIUM = PARTICLES.register("firewave_medium",
            () -> new SimpleParticleType(true));
    public static final RegistryObject<SimpleParticleType> FIREWAVE_LARGE = PARTICLES.register("firewave_large",
            () -> new SimpleParticleType(true));
}