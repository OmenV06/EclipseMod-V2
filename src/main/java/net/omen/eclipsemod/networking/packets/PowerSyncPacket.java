package net.omen.eclipsemod.networking.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.omen.eclipsemod.client.gui.PlayerMenu;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.power.PlayerPowerProvider;
import net.omen.eclipsemod.power.PowerBase;
import net.omen.eclipsemod.power.PowerPool;

import java.util.function.Supplier;

public class PowerSyncPacket {
    private final boolean isRequest;
    private final String powerName;
    private final String powerDescription;

    public PowerSyncPacket() {
        this.isRequest = true;
        this.powerName = "";
        this.powerDescription = "";
    }

    public PowerSyncPacket(String powerName, String powerDescription) {
        this.isRequest = false;
        this.powerName = powerName;
        this.powerDescription = powerDescription;
    }

    public PowerSyncPacket(FriendlyByteBuf buf) {
        this.isRequest = buf.readBoolean();
        this.powerName = buf.readUtf(32767);
        this.powerDescription = buf.readUtf(32767);
    }

    public void toBytes(FriendlyByteBuf buf) {
        buf.writeBoolean(this.isRequest);
        buf.writeUtf(this.powerName);
        buf.writeUtf(this.powerDescription);
    }

    public boolean handle(Supplier<NetworkEvent.Context> contextSupplier) {
        NetworkEvent.Context context = contextSupplier.get();
        context.enqueueWork(() -> {
            if (this.isRequest) {

                ServerPlayer player = context.getSender();
                if (player != null) {
                    player.getCapability(PlayerPowerProvider.ECLIPSE_POWER_CAPABILITY).ifPresent(cap -> {
                        String powerName = cap.getPower();
                        PowerBase power = PowerPool.REGISTERED_POWERS.get(powerName);
                        String description = power != null ? power.getPowerDescription() : "";

                        ModPackets.sendToPlayer(new PowerSyncPacket(powerName, description), player);
                    });
                }
            } else {

                if (Minecraft.getInstance().screen instanceof PlayerMenu playerMenu) {
                    playerMenu.updatePowerInfo(this.powerName, this.powerDescription);
                }
            }
        });
        return true;
    }
}