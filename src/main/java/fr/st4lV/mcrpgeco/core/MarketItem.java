package fr.st4lV.mcrpgeco.core;

import java.util.Map;

public class MarketItem {
    private String itemData,itemMod,item,itemType;
    private int price, qMax,qStartRatio,qStock, itemId;
    int MaxPage = 10;

    /*if (((int)Qentries/6) < ((double)Qentries/6)) {
        MaxPage = (int) Qentries + 1
    } else {
        MaxPage = (int) (Qentries/6)
    }*/

    private static MarketItem instance;

    public MarketItem() {
    }

    public static MarketItem getInstance() {
        if (instance == null) {
            instance = new MarketItem();
        }
        return instance;
    }

    public void StringValues(Map<Integer, String> itemIdMap, Map<String, Integer> itemDataMap, Map<String, String> itemTypes) {
        for (Map.Entry<Integer, String> entry : itemIdMap.entrySet()) {
            int itemId = entry.getKey();
            String itemGrab = entry.getValue();
            int priceGrab = itemDataMap.getOrDefault(itemGrab, 0);
            int qMaxGrab = 0;
            int qStartRatioGrab = 0;
            int qStockGrab = 0;

            String stockType = itemTypes.getOrDefault(itemGrab, "");

            updateValues(itemId, itemGrab, priceGrab, qMaxGrab, qStartRatioGrab, qStockGrab, stockType);
        }
    }

    public void updateValues(int itemId, String itemGrab, int priceGrab, int qMaxGrab, int qStartRatioGrab, int qStockGrab, String stockType) {
        // Split the itemGrab string into two parts based on ":"
        String[] parts = itemGrab.split(":");
        if (parts.length >= 2) {
            this.itemMod = parts[0];
            this.item = parts[1];
            this.itemData = itemGrab;
        } else {
            // Handle case where there is no ":" in the string
            this.itemMod = "mod_id";
            this.item = itemGrab; // or whatever default value you prefer
            this.itemData = itemGrab;
        }

        // Update other values
        this.itemId = itemId;
        this.price = priceGrab;
        this.qMax = qMaxGrab;
        this.qStartRatio = qStartRatioGrab;
        this.qStock = qStockGrab;
        this.itemType = stockType;
    }


    public int getItemId() { return itemId; }

    public String getItemData(int getItemId) {
        if (this.itemId == itemId) {
        return itemData;
        } else {
            return null;
        }
    }
    public String getItemMod() {
        return itemMod;
    }
    public String getItem() {
        return item;
    }

    public int getPrice() {
        return price;
    }

    public int getQMax() {
        return qMax;
    }

    public int getQStartRatio() {
        return qStartRatio;
    }

    public int getQStock() {
        return qStock;
    }

    public String getItemType() {
        return itemType;
    }

    public int getMaxPage() {
        return MaxPage;
    }
}
