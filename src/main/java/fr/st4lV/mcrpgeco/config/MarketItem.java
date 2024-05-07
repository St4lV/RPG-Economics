package fr.st4lV.mcrpgeco.config;

public class MarketItem {
    private String item;
    private int price = 10000;
    private int qMax = 512;
    private int qStartRatio = 85;
    private int qStock = 8;

    private static MarketItem instance;

    private MarketItem() {
        // Private constructor to prevent instantiation from outside
    }

    public static MarketItem getInstance() {
        if (instance == null) {
            instance = new MarketItem();
        }
        return instance;
    }

    public static void init(
            String itemGrab,
            int priceGrab,
            int qMaxGrab,
            int qStartRatioGrab,
            int qStockGrab) {
        instance = new MarketItem();
        instance.setItem(itemGrab);
        instance.setPrice(priceGrab);
        instance.setQmax(qMaxGrab);
        instance.setQStartRatio(qStartRatioGrab);
        instance.setQStock(qStockGrab);

    }
    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQmax() {
        return qMax;
    }

    public void setQmax(int qMax) {
        this.qMax = qMax;
    }

    public int getQStartRatio() {
        return qStartRatio;
    }

    public void setQStartRatio(int qStartRatio) {
        this.qStartRatio = qStartRatio;
    }

    public int getQStock() {
        return qStock;
    }

    public void setQStock(int qStock) {
        this.qStock = qStock;
    }
}
