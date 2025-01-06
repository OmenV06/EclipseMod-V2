package net.omen.eclipsemod.networking.packets;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.omen.eclipsemod.power.PlayerPowerProvider;
import net.omen.eclipsemod.power.PowerBase;
import net.omen.eclipsemod.power.PowerPool;
import net.omen.eclipsemod.power.powers.EmptyPower;

import java.util.function.Supplier;

public class ExecuteAbilityC2SPacket {
    private final int abilityIndex;

    public ExecuteAbilityC2SPacket(int abilityIndex) {
        this.abilityIndex = abilityIndex;
    }

    public ExecuteAbilityC2SPacket(FriendlyByteBuf buf) {
        this.abilityIndex = buf.readInt();
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeInt(abilityIndex);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {

            ServerPlayer player = context.getSender();

            if (player != null && !player.isSpectator()) {
                player.getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(cap -> {
                    PowerBase power = PowerPool.REGISTERED_POWERS.getOrDefault(cap.getPower(), new EmptyPower());
                    if (abilityIndex >= 1 && abilityIndex <= power.getAbilities().size()) {
                        power.getAbilities().get(abilityIndex - 1).executeAbility(player);
                    }
                });

            }
        });
        return true;
    }
}