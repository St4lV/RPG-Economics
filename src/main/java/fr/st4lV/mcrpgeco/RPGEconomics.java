package fr.st4lV.mcrpgeco;

//import net.minecraft.world.level.Ser
import fr.st4lV.mcrpgeco.config.ServConfDatagen;
import fr.st4lV.mcrpgeco.core.MarketItem;
import fr.st4lV.mcrpgeco.screen.BlockbergTerminalScreen;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import com.mojang.logging.LogUtils;

import fr.st4lV.mcrpgeco.block.ModBlocks;
import fr.st4lV.mcrpgeco.block.entity.ModBlockEntities;
import fr.st4lV.mcrpgeco.core.MarketCalculs;
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
        import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import static fr.st4lV.mcrpgeco.config.Serverconfig.SERVERCONFIG;

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

        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVERCONFIG);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);

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

    private static void setup(FMLCommonSetupEvent event) {

        fr.st4lV.mcrpgeco.config.Serverconfig.registerConfig();

    }
    private void addCreative(BuildCreativeModeTabContentsEvent event) {
        if(event.getTabKey() == CreativeModeTabs.TOOLS_AND_UTILITIES) {

            event.accept(ModItems.PURSE);
            event.accept(ModItems.BRONZE_COIN);
            event.accept(ModItems.SILVER_COIN);
            event.accept(ModItems.GOLD_COIN);
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
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");

        ServConfDatagen.getMarketItemsFile();

        fr.st4lV.mcrpgeco.config.Serverconfig.initConfig();
        MarketItem marketItem = MarketItem.getInstance();
        //MarketItem.getInstance().updateValues("",50000,512,85,16);

        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        marketCalculs.initValue(marketItem);
        marketCalculs.updateMarketValues();

        BlockbergTerminalScreen blockbergTerminalScreen = BlockbergTerminalScreen.getInstance();
        blockbergTerminalScreen.updatePriceValues();
        ServConfDatagen.getValuesToJSON();
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
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getUuid());
        }
    }
    /*@Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
    public static class PlayerEvents {

        @SubscribeEvent
        public static void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
            // Get the player
            ServerPlayer player = (ServerPlayer) event.getPlayer();

            // Get the player's world
            ServerWorld world = player.getLevel();

            // Get the directory for player data within the world's save folder
            File saveFolder = world.getServer().getWorldPath(LevelResource.PLAYER_DATA_DIR).toFile();
            File playerDataFolder = new File(saveFolder, "playerdata");

            // Construct the file path to the player's data file
            UUID playerUUID = player.getUUID();
            File playerDataFile = new File(playerDataFolder, playerUUID.toString() + ".dat");

            // Access the player's persistent data using the constructed path
            CompoundTag playerData = null;
            try {
                // Check if the player data file exists before reading it
                if (playerDataFile.exists()) {
                    playerData = net.minecraft.nbt.NbtIo.readCompressed(new FileInputStream(playerDataFile));
                } else {
                    // If the player data file doesn't exist, create a new CompoundTag
                    playerData = new CompoundTag();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            // Now you can access the player's data
            if (playerData != null && !playerData.contains("money")) {
                // If the "money" tag doesn't exist, create it with an initial value of 0
                playerData.putInt("money", 0);
                // Save the updated data back to the file
                try {
                    net.minecraft.nbt.NbtIo.writeCompressed(playerData, new FileOutputStream(playerDataFile));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/
}
