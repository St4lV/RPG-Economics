package fr.st4lV.mcrpgeco.core;

import java.util.Map;

public class MarketItem {
    private String itemData,itemMod,item,itemType;
    private int price, qMax,qStartRatio,qStock;
    int MaxPage = 10;

    /*if (((int)Qentries/6) < ((double)Qentries/6)) {
        MaxPage = (int) Qentries + 1
    } else {
        MaxPage = (int) (Qentries/6)
    }*/

    private static MarketItem instance;

    private MarketItem() {
    }

    public static MarketItem getInstance() {
        if (instance == null) {
            instance = new MarketItem();
        }
        return instance;
    }

    public void StringValues(Map<String, Integer> itemDataMap, Map<String, String> itemTypes) {
        for (Map.Entry<String, Integer> entry : itemDataMap.entrySet()) {
            String itemGrab = entry.getKey();
            int priceGrab = entry.getValue();
            int qMaxGrab = 0;
            int qStartRatioGrab = 0;
            int qStockGrab = 0;

            String stockType = itemTypes.getOrDefault(itemGrab, "");

            updateValues(itemGrab, priceGrab, qMaxGrab, qStartRatioGrab, qStockGrab, stockType);
        }
    }

    public void updateValues(String itemGrab, int priceGrab, int qMaxGrab, int qStartRatioGrab, int qStockGrab, String stockType) {
        // Split the itemGrab string into two parts based on ":"
        String[] parts = itemGrab.split(":");
        if (parts.length >= 2) {
            this.itemMod = parts[0];
            this.item = parts[1];
        } else {
            // Handle case where there is no ":" in the string
            this.itemMod = "mod_id";
            this.item = itemGrab; // or whatever default value you prefer
        }

        // Update other values
        this.price = priceGrab;
        this.qMax = qMaxGrab;
        this.qStartRatio = qStartRatioGrab;
        this.qStock = qStockGrab;
        this.itemType = stockType;
    }



    public String getItemData() {
        return itemData;
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
