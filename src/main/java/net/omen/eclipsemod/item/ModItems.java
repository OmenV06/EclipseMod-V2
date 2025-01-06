package net.omen.eclipsemod.item;

import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.omen.eclipsemod.EclipseMod;
import net.omen.eclipsemod.item.custom.artifacts.illuminite.DaggerOfDecayItem;
import net.omen.eclipsemod.item.custom.artifacts.illuminite.DaggerOfVenomItem;
import net.omen.eclipsemod.item.custom.artifacts.illuminite.MaceOfDensityItem;
import net.omen.eclipsemod.item.custom.misc.EboniteFragmentItem;
import net.omen.eclipsemod.item.custom.misc.IlluminiteGemItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, EclipseMod.MOD_ID);

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

    //Items added here
    public static final RegistryObject<Item> ILLUMINITE_GEM = ITEMS.register("illuminite_gem", IlluminiteGemItem::new);
    public static final RegistryObject<Item> EBONITE_FRAGMENT = ITEMS.register("ebonite_fragment", EboniteFragmentItem::new);

    public static final RegistryObject<Item> MACE_OF_DENSITY = ITEMS.register("mace_of_density", MaceOfDensityItem::new);
    public static final RegistryObject<Item> DAGGER_OF_VENOM = ITEMS.register("dagger_of_venom", DaggerOfVenomItem::new);
    public static final RegistryObject<Item> DAGGER_OF_DECAY = ITEMS.register("dagger_of_decay", DaggerOfDecayItem::new);

}
