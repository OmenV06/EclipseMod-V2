package net.omen.eclipsemod.power;

import net.minecraft.nbt.CompoundTag;

public class PlayerPowerCapability {

    private String powerName = "None";

    public void setPower(String powerName) {
        this.powerName = powerName;
    }

    public String getPower() {
        return powerName;
    }

    public void saveNBTData(CompoundTag tag) {
        tag.putString("PowerName", powerName);
    }

    public void loadNBTData(CompoundTag tag) {
        this.powerName = tag.getString("PowerName");
    }

}
