package net.omen.eclipsemod.item.custom.artifacts.illuminite;

import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.TierSortingRegistry;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class DaggerOfDecayItem extends SwordItem {

    public static final Tier DAGGER_OF_DECAY = TierSortingRegistry.registerTier(
            new ForgeTier(3, 800, 2.0f, 3f, 20,
                    Tags.Blocks.NEEDS_NETHERITE_TOOL, () -> Ingredient.of(ModItems.ILLUMINITE_GEM.get())),
            new ResourceLocation(EclipseMod.MOD_ID, "dagger_of_decay"), List.of(Tiers.DIAMOND), List.of());



    public DaggerOfDecayItem() {
        super(DAGGER_OF_DECAY, 3, -1.6f, new Properties());

    }



    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
         {
            target.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 2));
             target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 40, 1));
        }
        return super.hurtEnemy(stack, target, attacker);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);

        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.legendary.tooltip").withStyle(ChatFormatting.YELLOW));
        pTooltipComponents.add(Component.translatable("tooltip.eclipsemod.dagger_of_decay.tooltip"));
    }


}

