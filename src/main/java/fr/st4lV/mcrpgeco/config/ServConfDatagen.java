package fr.st4lV.mcrpgeco.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.st4lV.mcrpgeco.core.MarketItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ServConfDatagen {

    public static class MarketItemList {
        public int id;
        public Values values;

        public MarketItemList() {
            this.id = 2;
            this.values = new Values();
        }

        public static class Values {
            public String itemData;
            public int price;
            public int qMax;
            public int qStartRatio;
            public int qStock;
            public String itemType;

            public Values() {
                this.itemData = MarketItem.getInstance().getItemData(MarketItem.getInstance().getItemId());
                this.price = MarketItem.getInstance().getPrice();
                this.qMax = MarketItem.getInstance().getQMax();
                this.qStartRatio = MarketItem.getInstance().getQStartRatio();
                this.qStock = MarketItem.getInstance().getQStock();
                this.itemType = MarketItem.getInstance().getItemType();
            }
        }
    }

    private static final String SERVER_CONFIG_PATH = "serverconfig";
    private static final String MARKET_ITEMS_FILE = "market_items.json";
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    public static File getMarketItemsFile() {

        Object serverInstance = ServerLifecycleHooks.getCurrentServer();
        boolean isMultiplayer;

        if (serverInstance instanceof DedicatedServer) {
            isMultiplayer = true;
        } else if (serverInstance instanceof IntegratedServer) {
            isMultiplayer = false;
        } else {
            isMultiplayer = false;
        }

        File serverConfigDir;
        if (isMultiplayer) {
            String gameDir = ((DedicatedServer) serverInstance).getLevelIdName();
            serverConfigDir = new File(gameDir, SERVER_CONFIG_PATH);
            return new File(serverConfigDir, MARKET_ITEMS_FILE);
        } else {
            IntegratedServer integratedServer = Minecraft.getInstance().getSingleplayerServer();
            if (integratedServer == null || integratedServer.getWorldData() == null) {
                File gameDir = Minecraft.getInstance().gameDirectory;
                serverConfigDir = new File(gameDir, SERVER_CONFIG_PATH);
            } else {
                String worldName = integratedServer.getWorldData().getLevelName();
                File gameDir = Minecraft.getInstance().gameDirectory;
                File savesDir = new File(gameDir, "saves");
                File worldDir = new File(savesDir, worldName);
                serverConfigDir = new File(worldDir, SERVER_CONFIG_PATH);
            }
        }

        if (!serverConfigDir.exists()) {
            serverConfigDir.mkdirs();
        }

        File marketItemsFile = new File(serverConfigDir, MARKET_ITEMS_FILE);

        if (!marketItemsFile.exists()) {
            generateJSON(marketItemsFile);
            grabServerconfigValues();
        }
        return marketItemsFile;
    }

    private static void generateJSON(File jsonFile) {
        try {
            jsonFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static void grabServerconfigValues() {
        System.out.println("Handling serverconfig init values");
        ForgeConfigSpec.ConfigValue<List<? extends String>> marketItemsConfigValue = Serverconfig.marketItemsList;
        List<? extends String> marketItemsList = marketItemsConfigValue.get();
    }

    public static List<MarketItemList> loadMarketItems() {
        File jsonFile = getMarketItemsFile();
        if (jsonFile != null && jsonFile.exists() && jsonFile.length() > 0) {
            try (FileReader reader = new FileReader(jsonFile)) {
                Type listType = new TypeToken<List<MarketItemList>>() {}.getType();
                return GSON.fromJson(reader, listType);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Handling json init values");
        return null;
    }

    public static void saveMarketItems(List<MarketItemList> marketProvidedItems) {
        File jsonFile = getMarketItemsFile();
        if (jsonFile != null) {
            List<MarketItemList> existingMarketItems = loadMarketItems();

            if (existingMarketItems != null) {
                for (MarketItemList newItem : marketProvidedItems) {
                    int id = newItem.id;
                    boolean found = false;
                    for (MarketItemList existingItem : existingMarketItems) {
                        if (existingItem.id == id) {
                            existingItem.values.itemData = newItem.values.itemData;
                            existingItem.values.price = newItem.values.price;
                            existingItem.values.qMax = newItem.values.qMax;
                            existingItem.values.qStartRatio = newItem.values.qStartRatio;
                            existingItem.values.qStock = newItem.values.qStock;
                            existingItem.values.itemType = newItem.values.itemType;
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        existingMarketItems.add(newItem);
                    }
                }
            } else {
                existingMarketItems = marketProvidedItems;
            }
            try (FileWriter writer = new FileWriter(jsonFile)) {
                GSON.toJson(existingMarketItems, writer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void getValuesToJSON() {
        MarketItemList newItem = new MarketItemList();
        List<MarketItemList> newMarketItemList = new ArrayList<>();
        newMarketItemList.add(newItem);
        saveMarketItems(newMarketItemList);
    }
}
