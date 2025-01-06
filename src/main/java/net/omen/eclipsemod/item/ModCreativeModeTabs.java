package net.omen.eclipsemod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.block.ModBlocks;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

    public static final RegistryObject<CreativeModeTab> ECLIPSE_TAB = CREATIVE_MODE_TABS.register("eclipse_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.ILLUMINITE_GEM.get()))
                    .title(Component.translatable("creativetab.eclipse_tab"))
                    .displayItems((pParameters, pOutput) -> {

                            pOutput.accept(ModItems.ILLUMINITE_GEM.get());
                            pOutput.accept(ModItems.EBONITE_FRAGMENT.get());

                            pOutput.accept(ModBlocks.BLOCK_OF_ILLUMINITE.get());
                            pOutput.accept(ModBlocks.BLOCK_OF_EBONITE.get());

                            pOutput.accept(ModItems.MACE_OF_DENSITY.get());
                            pOutput.accept(ModItems.DAGGER_OF_VENOM.get());
                            pOutput.accept(ModItems.DAGGER_OF_DECAY.get());

                    })
                    .build());

}
