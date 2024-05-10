package fr.st4lV.mcrpgeco.config;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;

import java.util.ArrayList;
import java.util.List;

import static com.mojang.text2speech.Narrator.LOGGER;

public class Config {
    // Define your config values as static fields
    public static ForgeConfigSpec.ConfigValue<List<String>> marketItems;

    // Define your entire configuration as a ForgeConfigSpec object
    public static final ForgeConfigSpec CONFIG;

    static {
        // Create a ForgeConfigSpec builder
        ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

        // Define your config values using the builder
        builder.comment("Market Items Configuration")
                .push("marketItems")
                .comment(
                        """
                                This mod while need fews values before doing it job so here a little explanation ! :

                                Price : has to be a positive integer, uses =>1000 values for better results.
                                (!! remind !! : 101010 is 10 gold coins 10 silver coins & 10 bronze coins.)

                                qMax: has to be a positive integer, maximum of stocks available inside the trade.

                                qStartRatio : range : 1-100, quantity in % of Max Quantity value available inside the trade a init.

                                qStock: range : 1-64, quantity of items traded by stocks.
                                
                                !! You need to specify the type of item you're selling (block,item) !!


                                List of Market Items

                                Format: mod:item,Price,qMax,qStartRatio,qStock
                                """
                )
                .define("marketItems", new ArrayList<String>() {{
                    add("minecraft:iron_ingot,25000,512,85,48,item");
                    add("minecraft:copper_ingot,25000,512,85,64,item");
                    add("minecraft:gold_ingot,330000,512,85,48,item");
                    add("minecraft:ancient_debris,250000,512,85,1,item");
                }});

        // Pop the current configuration section
        builder.pop();

        // Assign the built configuration to CONFIG
        CONFIG = builder.build();
    }

    // Method to register the configuration
    public static void registerConfig() {
        // Register the configuration
        ModLoadingContext.get().registerConfig(ModConfig.Type.SERVER, CONFIG);
    }

    public static void initConfig() {
        MarketItem.getInstance().updateValues("minecraft:iron_block",50000,512,85,16, "block");
    }
}

