package fr.st4lV.mcrpgeco.config;

import fr.st4lV.mcrpgeco.core.MarketItem;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLPaths;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

public class Serverconfig {

    public static ForgeConfigSpec.ConfigValue<List<? extends String>> marketItemsList;
    public static ForgeConfigSpec.BooleanValue autoActiveMarket;

    public static final ForgeConfigSpec SERVERCONFIG;

    static {

        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        builder.comment(
                "RPGEconomics Server Configuration :\n\n"
                        + "This mod requires a few values before it can function. Here's an explanation:\n\n"
                        + "marketItemsList :\n\n"
                        + "Format: mod:item,Price,qMax,qStartRatio,qStock,type\n\n"
                        + "Price : The price must be a positive integer, using values >= 1000 for better results.\n\n"
                        + "(!! reminder !! : 101010 represents 10 gold coins, 10 silver coins, and 10 bronze coins.)\n\n"
                        + "qMax: The maximum number of stocks available inside the trade.\n\n"
                        + "qStartRatio : Range: 1-100. Represents the quantity in % of Max Quantity value available inside the trade at initialization.\n\n"
                        + "qStock: Range: 1-64. Represents the quantity of items traded by stocks.\n\n"
                        + "type : item , block : You need to specify the type of item you're selling.\n\n"
                        + "autoActiveMarket:\n\n"
                        + "Controls whether the market evolves by itself by buying or selling stocks randomly."
        );

        marketItemsList = builder
                .comment("List of market items in the format: mod:item,Price,qMax,qStartRatio,qStock,type.")
                .define("marketItemsList", Arrays.asList(
                        "minecraft:iron_ingot,25000,512,2,48,item",
                        "minecraft:copper_ingot,25000,512,2,64,item",
                        "minecraft:gold_ingot,330000,512,2,48,item",
                        "minecraft:ancient_debris,250000,0,85,1,item"
                ));

        autoActiveMarket = builder
                .comment("Enable auto active market with : true/false.")
                .define("autoActiveMarket", true);

        SERVERCONFIG = builder.build();
    }

    // Method to register the configuration
    public static void registerConfig() {
        // Register the configuration
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, SERVERCONFIG);
    }

    public static void initConfig() {
        File backupFile = FMLPaths.CONFIGDIR.get().resolve("mcrpgeco-server-1.toml.bak").toFile();
        if (backupFile.exists()) {
            try {
                Files.deleteIfExists(backupFile.toPath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //ServConfDatagen.getValuesToJSON(2,"minecraft:gold_ingot", 25000, 512, 85, 16);
        MarketItem.getInstance().updateValues(1,"minecraft:iron_block", 50000, 512, 85, 16, "block");
        MarketItem.getInstance().updateValues(2, "minecraft:copper_ingot",25000,512,85,16, "item");
    }
}
