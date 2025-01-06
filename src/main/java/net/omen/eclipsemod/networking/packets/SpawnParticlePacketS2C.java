package net.omen.eclipsemod.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class SpawnParticlePacketS2C {
    private final ParticleType<?> particleType;
    private final double x, y, z;
    private final double dx, dy, dz;

    public SpawnParticlePacketS2C(ParticleType<?> particleType, double x, double y, double z, double dx, double dy, double dz) {
        this.particleType = particleType;
        this.x = x;
        this.y = y;
        this.z = z;
        this.dx = dx;
        this.dy = dy;
        this.dz = dz;
    }

    public SpawnParticlePacketS2C(FriendlyByteBuf buf) {
        RegistryAccess registryAccess = Minecraft.getInstance().level.registryAccess();
        this.particleType = buf.readById(registryAccess.registryOrThrow(Registries.PARTICLE_TYPE));
        this.x = buf.readDouble();
        this.y = buf.readDouble();
        this.z = buf.readDouble();
        this.dx = buf.readDouble();
        this.dy = buf.readDouble();
        this.dz = buf.readDouble();
    }

    public void toBytes(FriendlyByteBuf buf) {
        RegistryAccess registryAccess = Minecraft.getInstance().level.registryAccess();
        buf.writeId(registryAccess.registryOrThrow(Registries.PARTICLE_TYPE), this.particleType);
        buf.writeDouble(this.x);
        buf.writeDouble(this.y);
        buf.writeDouble(this.z);
        buf.writeDouble(this.dx);
        buf.writeDouble(this.dy);
        buf.writeDouble(this.dz);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier) {
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            Minecraft.getInstance().level.addParticle(
                    (ParticleOptions) this.particleType,
                    this.x, this.y, this.z,
                    this.dx, this.dy, this.dz
            );
        });
        return true;
    }
}
