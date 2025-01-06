package net.omen.eclipsemod.power;

import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityToken;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.INBTSerializable;
import net.minecraftforge.common.util.LazyOptional;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class PlayerPowerProvider implements ICapabilityProvider, INBTSerializable<CompoundTag> {

    public static Capability<PlayerPowerCapability> ECLIPSE_POWER_CAPABILITY = CapabilityManager.get(new CapabilityToken<PlayerPowerCapability>() {});

    private PlayerPowerCapability powerCapability = null;
    private final LazyOptional<PlayerPowerCapability> optional = LazyOptional.of(this::createPlayerPowerCapability);

    private PlayerPowerCapability createPlayerPowerCapability() {
        if (this.powerCapability == null) {
            this.powerCapability = new PlayerPowerCapability();
        }
        return this.powerCapability;
    }


    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> capability, @Nullable Direction direction) {
        if (capability == ECLIPSE_POWER_CAPABILITY) {
            return optional.cast();
        }
        return LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        createPlayerPowerCapability().saveNBTData(tag);
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag tag) {
        createPlayerPowerCapability().loadNBTData(tag);
    }
}
