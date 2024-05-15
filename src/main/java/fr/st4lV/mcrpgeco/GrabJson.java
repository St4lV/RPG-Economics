package fr.st4lV.mcrpgeco;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import net.minecraft.client.Minecraft;
import net.minecraft.client.server.IntegratedServer;
import net.minecraft.server.dedicated.DedicatedServer;
import net.minecraftforge.server.ServerLifecycleHooks;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class GrabJson {

    private static final String SERVER_CONFIG_PATH = "serverconfig";
    private static final String MARKET_ITEMS_FILE = "market_items.json";

    public static File getConfigFilePath() {
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
        return new File(serverConfigDir, MARKET_ITEMS_FILE);
    }

    static class Item {
        int id;
        Values values;
    }

    public static class Values {
        public String itemData;
        public String mod_id;
        public String item;
        public int price;
        public int qMax;
        public int qStartRatio;
        public int qStock;
        public String itemType;

        public Values() {
            // Default constructor needed for Gson
        }

        // This method is used to split itemData and set mod_id and items
        private void initialize() {
            String[] parts = itemData.split(":");
            if (parts.length == 2) {
                this.mod_id = parts[0];
                this.item = parts[1];
            }
        }
    }

    public static int getMaxId() throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<Item>>() {}.getType();

        File configFile = getConfigFilePath();
        FileReader reader = new FileReader(configFile);
        List<Item> items = gson.fromJson(reader, itemListType);
        reader.close();

        int maxId = 0;
        for (Item item : items) {
            if (item.id > maxId) {
                maxId = item.id;
            }
        }
        return maxId;
    }

    public static Values grabJSON(int id) throws IOException {
        Gson gson = new Gson();
        Type itemListType = new TypeToken<List<Item>>() {}.getType();

        File configFile = getConfigFilePath();
        FileReader reader = new FileReader(configFile);
        List<Item> items = gson.fromJson(reader, itemListType);
        reader.close();

        for (Item item : items) {
            if (item.id == id) {
                item.values.initialize();  // Initialize mod_id and items
                return item.values;
            }
        }
        return null; // Return null if the item is not found
    }
}
