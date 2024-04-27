package fr.st4lV.mcrpgeco;

import com.mojang.logging.LogUtils;
import fr.st4lV.mcrpgeco.block.ModBlocks;
import fr.st4lV.mcrpgeco.item.ModCreativeModTabs;
import fr.st4lV.mcrpgeco.item.ModItems;
import fr.st4lV.mcrpgeco.loot.ModLootModifiers;

import fr.st4lV.mcrpgeco.villager.ModVillagers;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGEconomics.MODID)
public class RPGEconomics
{
    // Define mod id in a common place for everything to reference
    public static final String MODID = "mcrpgeco";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    private Inventory Config;

    public RPGEconomics()
    {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModVillagers.register(modEventBus);

        ModLootModifiers.register(modEventBus);

        // Register the commonSetup method for modloading
        modEventBus.addListener(this::commonSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);

        // Register the item to a creative tab
        modEventBus.addListener(this::addCreative);

        // Register our mod's ForgeConfigSpec so that Forge can create and load the config file for us
        // ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {

    }

    // Add the example block item to the building blocks tab
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {
            event.accept(ModItems.BRONZE_COIN);
            event.accept(ModItems.SILVER_COIN);
            event.accept(ModItems.GOLD_COIN);
            event.accept(ModItems.PURSE);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(ModBlocks.TEST_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.FUNCTIONAL_BLOCKS) {
            event.accept(ModBlocks.BLOCKBERG_TERMINAL);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}
