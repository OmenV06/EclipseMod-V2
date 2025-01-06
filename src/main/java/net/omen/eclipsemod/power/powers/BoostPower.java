package net.omen.eclipsemod.power.powers;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.omen.eclipsemod.power.PowerBase;

import java.util.UUID;

public class BoostPower implements PowerBase {

    private static final UUID BOOST_HEALTH_MODIFIER_UUID = UUID.fromString("a84cb736-d5d3-4b67-b687-9e3df84f7b7d");
    private static final UUID BOOST_ARMOR_MODIFIER_UUID = UUID.fromString("32442a25-91c4-4210-9e80-76536ad246a4");
    private static final UUID BOOST_TOUGHNESS_MODIFIER_UUID = UUID.fromString("f19e96c6-88be-4b89-b3b4-f5fbcf50ddc6");
    private static final UUID BOOST_STRENGTH_MODIFIER_UUID = UUID.fromString("ba2c4160-c5d8-45a1-aacf-cc7c9baf8534");
    private static final UUID BOOST_ATTACK_SPEED_MODIFIER_UUID = UUID.fromString("2a95b1d2-f55b-4dc9-b92c-1a12275df6b2");
    private static final UUID BOOST_MOVEMENT_SPEED_MODIFIER_UUID = UUID.fromString("81a089de-f382-4458-9d76-4a2c4de4ad45");

    @Override
    public void applyPower(ServerPlayer player) {
        abilitiesBoost(player);
    }

    @Override
    public void removePower(ServerPlayer player) {
        abilitiesBoost(player);
    }

    @Override
    public String getPowerName() {
        return "Boost";
    }

    @Override
    public String getPowerTier() {
        return "B";
    }

    @Override
    public String getPowerDescription() {
        return "+20 Health, +10 Armor, +2 Toughness, +8 Strength, +0.5 Attack Speed, +0.2 Movement Speed";
    }

    private void abilitiesBoost(ServerPlayer player) {

        applyOrRemoveModifier(player, Attributes.MAX_HEALTH, BOOST_HEALTH_MODIFIER_UUID,
                "Boost Health", 20.0, AttributeModifier.Operation.ADDITION);


        applyOrRemoveModifier(player, Attributes.ARMOR, BOOST_ARMOR_MODIFIER_UUID,
                "Boost Armor", 10.0, AttributeModifier.Operation.ADDITION);


        applyOrRemoveModifier(player, Attributes.ARMOR_TOUGHNESS, BOOST_TOUGHNESS_MODIFIER_UUID,
                "Boost Toughness", 2.0, AttributeModifier.Operation.ADDITION);


        applyOrRemoveModifier(player, Attributes.ATTACK_DAMAGE, BOOST_STRENGTH_MODIFIER_UUID,
                "Boost Strength", 8.0, AttributeModifier.Operation.ADDITION);


        applyOrRemoveModifier(player, Attributes.ATTACK_SPEED, BOOST_ATTACK_SPEED_MODIFIER_UUID,
                "Boost Attack Speed", 0.5, AttributeModifier.Operation.ADDITION);


        applyOrRemoveModifier(player, Attributes.MOVEMENT_SPEED, BOOST_MOVEMENT_SPEED_MODIFIER_UUID,
                "Boost Movement Speed", 0.02, AttributeModifier.Operation.ADDITION);


        player.setHealth(player.getHealth() + 20);
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