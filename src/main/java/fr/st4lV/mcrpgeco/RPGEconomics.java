package fr.st4lV.mcrpgeco;

import fr.st4lV.mcrpgeco.config.ServConfDatagen;
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

import java.io.IOException;

import static fr.st4lV.mcrpgeco.config.Serverconfig.SERVERCONFIG;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RPGEconomics.MODID)
public class RPGEconomics {
    public static final String MODID = "mcrpgeco";
    private static final Logger LOGGER = LogUtils.getLogger();
    private Inventory Config;

    public RPGEconomics() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModCreativeModTabs.register(modEventBus);
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVERCONFIG);

        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModBlockEntities.register(modEventBus);
        ModVillagers.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        modEventBus.addListener(this::commonSetup);
        MinecraftForge.EVENT_BUS.register(this);
        modEventBus.addListener(this::addCreative);
    }

    private void commonSetup(final FMLCommonSetupEvent event) {}

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

    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        LOGGER.info("HELLO from server starting");

        ServConfDatagen.getMarketItemsFile();
        fr.st4lV.mcrpgeco.config.Serverconfig.initConfig();

        MarketCalculs marketCalculs = MarketCalculs.getInstance();
        try {
            int maxId = GrabJson.getMaxId();
            marketCalculs.initValuesForRange(1, maxId);
            marketCalculs.updateMarketValues();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ServConfDatagen.getValuesToJSON();
    }

    @Mod.EventBusSubscriber(modid = MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getUuid());
        }
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

