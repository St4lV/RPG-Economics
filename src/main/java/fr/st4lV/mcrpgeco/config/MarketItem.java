package fr.st4lV.mcrpgeco.config;

public class MarketItem {
    private String itemData,itemMod,item;
    private int price, qMax,qStartRatio,qStock;

    private static MarketItem instance;

    private MarketItem() {
    }

    public static MarketItem getInstance() {
        if (instance == null) {
            instance = new MarketItem();
        }
        return instance;
    }

    public void updateValues(
            String itemGrab,
            int priceGrab,
            int qMaxGrab,
            int qStartRatioGrab,
            int qStockGrab) {
        this.itemData = itemGrab;

        // Split the itemGrab string into two parts based on ":"
        String[] parts = itemGrab.split(":");

        // Assign the parts to itemMod and item
        if (parts.length >= 2) {
            this.itemMod = parts[0];
            this.item = parts[1];
        } else {
            // Handle case where there is no ":" in the string
            this.itemMod = "mod_id";
            this.item = itemGrab; // or whatever default value you prefer
        }

        this.price = priceGrab;
        this.qMax = qMaxGrab;
        this.qStartRatio = qStartRatioGrab;
        this.qStock = qStockGrab;
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
}
