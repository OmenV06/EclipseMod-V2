package net.omen.eclipsemod;

import com.mojang.logging.LogUtils;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.omen.eclipsemod.block.ModBlocks;
import net.omen.eclipsemod.client.effects.vfx.particle.ParticleRegistry;
import net.omen.eclipsemod.item.ModCreativeModeTabs;
import net.omen.eclipsemod.item.ModItems;
import net.omen.eclipsemod.networking.ModPackets;
import net.omen.eclipsemod.util.commands.CheckPlayerPowerCommand;
import net.omen.eclipsemod.util.commands.SetPlayerPowerCommand;
import org.slf4j.Logger;

@Mod(EclipseMod.MOD_ID)
public class EclipseMod {

    public static final String MOD_ID = "eclipsemod";

    private static final Logger LOGGER = LogUtils.getLogger();

    public EclipseMod(FMLJavaModLoadingContext context) {

        IEventBus modEventBus = context.getModEventBus();

        ModCreativeModeTabs.register(modEventBus);
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ParticleRegistry.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);

    }

    private void commonSetup(final FMLCommonSetupEvent event) {

        ModPackets.register();

    }

    private void addCreative(BuildCreativeModeTabContentsEvent event) {



    }

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {



    }

    @Mod.EventBusSubscriber(modid = MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {



        }
    }

    @Mod.EventBusSubscriber
    public class CommandRegister {

        @SubscribeEvent
        public static void onRegisterCommandsEvent(RegisterCommandsEvent event) {
            // Register commands here
            CheckPlayerPowerCommand.register(event.getDispatcher());
            SetPlayerPowerCommand.register(event.getDispatcher());

        }
    }

}
