package net.omen.eclipsemod.power.powers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.omen.eclipsemod.power.PowerBase;

import java.util.UUID;

public class VitalityPower implements PowerBase {
    private static final UUID VITALITY_HEALTH_MODIFIER_UUID = UUID.fromString("c4183a03-88a2-471e-9906-88be52d34169");

    @Override
    public void applyPower(ServerPlayer player) {

        addVitality(player);

    }

    @Override
    public void removePower(ServerPlayer player) {

        addVitality(player);

    }

    @Override
    public String getPowerName() {
        return "Vitality";
    }

    @Override
    public String getPowerTier() {
        return "C";
    }

    @Override
    public String getPowerDescription() {
        return "+80 Maximum Health";
    }

    private void addVitality(ServerPlayer player) {

        applyOrRemoveModifier(player, Attributes.MAX_HEALTH, VITALITY_HEALTH_MODIFIER_UUID,
                "Vitality Health", 80.0, AttributeModifier.Operation.ADDITION);

        player.setHealth(player.getHealth() + 80);

    }

    private void applyOrRemoveModifier(ServerPlayer player, Attribute attribute,
                                       UUID uuid, String name, double amount, AttributeModifier.Operation operation) {
        var attributeInstance = player.getAttribute(attribute);
        if (attributeInstance == null) return;

        AttributeModifier modifier = new AttributeModifier(uuid, name, amount, operation);

        if (attributeInstance.hasModifier(modifier)) {
            attributeInstance.removeModifier(uuid);
            player.setHealth(Math.min(player.getHealth(), player.getMaxHealth()));
        } else {
            attributeInstance.addPermanentModifier(modifier);
        }
    }

}