package net.omen.eclipsemod.client.gui;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.networking.packets.PowerSyncPacket;
import net.omen.eclipsemod.power.PowerBase;
import net.omen.eclipsemod.power.PowerPool;

public class PlayerMenu extends Screen {
    private static final ResourceLocation MENU_BACKGROUND_TEXTURE = new ResourceLocation(EclipseMod.MOD_ID, "textures/gui/player_menu.png");
    private int menuWidth = 256;
    private int menuHeight = 256;
    private String playerPowerName = "None";
    private String powerDescription = "";

    public PlayerMenu(Component title) {
        super(title);
    }

    @Override
    protected void init() {
        super.init();

        ModPackets.sendToServer(new PowerSyncPacket());
    }

    @Override
    public void render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTicks) {

        this.renderBackground(guiGraphics);

        guiGraphics.blit(MENU_BACKGROUND_TEXTURE, (this.width - this.menuWidth) / 2, (this.height - this.menuHeight) / 2, 0, 0, this.menuWidth, this.menuHeight);

        int x = (this.width - this.menuWidth) / 2;
        int y = (this.height - this.menuHeight) / 2;
        String username = Minecraft.getInstance().player.getName().getString();
        guiGraphics.drawString(this.font, username, x + 118, y + 16, 0xFFFFFF, false);
        guiGraphics.drawString(this.font, playerPowerName, x + 118, y + 36, 0xFFFFFF, false);
        guiGraphics.drawString(this.font, powerDescription, x + 118, y + 56, 0xFFFFFF, false);

        super.render(guiGraphics, mouseX, mouseY, partialTicks);
    }

    @OnlyIn(Dist.CLIENT)
    public void updatePowerInfo(String powerName, String description) {
        this.playerPowerName = powerName;
        this.powerDescription = description;
    }

    @Override
    public boolean isPauseScreen() {
        return true;
    }

    private String getPowerDescription(String powerName) {
        PowerBase power = PowerPool.REGISTERED_POWERS.get(powerName);
        if (power != null) {
            return power.getPowerDescription();
        }
        return "";
    }

}