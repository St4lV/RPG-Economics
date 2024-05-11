package fr.st4lV.mcrpgeco.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import fr.st4lV.mcrpgeco.core.MarketItem;

import java.io.*;
import java.lang.reflect.Type;
import java.util.List;

public class ServConfDatagen {

    private static final File JSON_FILE = new File("market_items.json");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final Type LIST_TYPE = new TypeToken<List<MarketItem>>() {}.getType();

    public static void GenerateJSON() {
        if (!JSON_FILE.exists()) {
            try {
                // Create the file if it doesn't exist
                        JSON_FILE.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public static List<MarketItem> loadMarketItems() {
        List<MarketItem> marketItems = null;
        // Check if the file is empty
        if (JSON_FILE.length() == 0) {
            return null; // Return null if the file is empty
        }
        try (FileReader reader = new FileReader(JSON_FILE)) {
            marketItems = GSON.fromJson(reader, LIST_TYPE);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return marketItems;
    }

    public static void saveMarketItems(List<MarketItem> marketItems) {
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            GSON.toJson(marketItems, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
