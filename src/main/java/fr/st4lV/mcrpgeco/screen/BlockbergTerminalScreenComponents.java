package fr.st4lV.mcrpgeco.screen;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.core.MarketCalculs;
import fr.st4lV.mcrpgeco.core.MarketItem;
import net.minecraft.network.chat.Component;

public class BlockbergTerminalScreenComponents {

    private Component buyComponent1, buyComponent2, buyComponent3, buyComponent4, buyComponent5, buyComponent6
            , sellComponent1, sellComponent2, sellComponent3, sellComponent4, sellComponent5, sellComponent6
            , qStockComponent1, qStockComponent2, qStockComponent3, qStockComponent4, qStockComponent5, qStockComponent6
            ,buyTooltipComp1,buyTooltipComp2,buyTooltipComp3,buyTooltipComp4,buyTooltipComp5,buyTooltipComp6
            ,sellTooltipComp1,sellTooltipComp2,sellTooltipComp3,sellTooltipComp4,sellTooltipComp5,sellTooltipComp6;

    static MarketCalculs marketCalculs = MarketCalculs.getInstance();
    private static BlockbergTerminalScreenComponents instance;

    private BlockbergTerminalScreenComponents() {
    }

    public static BlockbergTerminalScreenComponents getInstance() {
        if (instance == null) {
            instance = new BlockbergTerminalScreenComponents();
        }
        return instance;
    }

    public void updateComponent() {
            int gcBuy = marketCalculs.gcBuy;
            int scBuy = marketCalculs.scBuy;
            int bcBuy = marketCalculs.bcBuy;
            int gcSell = marketCalculs.gcSell;
            int scSell = marketCalculs.scSell;
            int bcSell = marketCalculs.bcSell;

            Component buyComponent1 = Component.literal(/*"§d⇈*/ "§f" +
                    (gcBuy != 0 ? gcBuy + "§e§l⌾ §f" : "") +
                    (gcBuy < 10000 ? (scBuy!= 0  ? scBuy + "§7§l⌾ §f" : "") : "") +
                    (gcBuy < 100 ? (bcBuy != 0 ? bcBuy + "§6§l⌾" : "") : ""));

            Component sellComponent1 = Component.literal(/*"§b⇊*/"§f" +
                    (gcSell != 0 ? gcSell + "§e§l⌾ §f" : "") +
                    (gcSell < 10000 ? (scSell!= 0  ? scSell + "§7§l⌾ §f" : "") : "") +
                    (gcSell < 100 ? (bcSell != 0 ? bcSell + "§6§l⌾" : "") : ""));

            Component qStockComponent1 = Component.translatable(
                    "" + MarketItem.getInstance().getQStock()
            );
            Component buyTooltipComp1 =  Component.literal("")
                    .append(Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.buy_button"))
                    .append(Component.literal(": x" + MarketItem.getInstance().getQStock() + " "))
                    .append(Component.translatable(MarketItem.getInstance().getItemType() +"."+ MarketItem.getInstance().getItemMod() + "." + MarketItem.getInstance().getItem()));

            Component sellTooltipComp1 =  Component.literal("")
                    .append(Component.translatable("gui." + RPGEconomics.MODID + ".blockberg_terminal.button.sell_button"))
                    .append(Component.literal(": x" + MarketItem.getInstance().getQStock() + " "))
                    .append(Component.translatable(MarketItem.getInstance().getItemType() +"."+ MarketItem.getInstance().getItemMod() + "." + MarketItem.getInstance().getItem()));

        this.buyComponent1 = buyComponent1;
        this.buyComponent2 = buyComponent2;
        this.buyComponent3 = buyComponent3;
        this.buyComponent4 = buyComponent4;
        this.buyComponent5 = buyComponent5;
        this.buyComponent6 = buyComponent6;
        this.sellComponent1 = sellComponent1;
        this.sellComponent2 = sellComponent2;
        this.sellComponent3 = sellComponent3;
        this.sellComponent4 = sellComponent4;
        this.sellComponent5 = sellComponent5;
        this.sellComponent6 = sellComponent6;
        this.qStockComponent1 = qStockComponent1;
        this.qStockComponent2 = qStockComponent2;
        this.qStockComponent3 = qStockComponent3;
        this.qStockComponent4 = qStockComponent4;
        this.qStockComponent5 = qStockComponent5;
        this.qStockComponent6 = qStockComponent6;
        this.buyTooltipComp1 = buyTooltipComp1;
        this.buyTooltipComp2 = buyTooltipComp2;
        this.buyTooltipComp3 = buyTooltipComp3;
        this.buyTooltipComp4 = buyTooltipComp4;
        this.buyTooltipComp5 = buyTooltipComp5;
        this.buyTooltipComp6 = buyTooltipComp6;
        this.sellTooltipComp1 = sellTooltipComp1;
        this.sellTooltipComp2 = sellTooltipComp2;
        this.sellTooltipComp3 = sellTooltipComp3;
        this.sellTooltipComp4 = sellTooltipComp4;
        this.sellTooltipComp5 = sellTooltipComp5;
        this.sellTooltipComp6 = sellTooltipComp6;

            }
    public Component getBuyComponent1(){
        return buyComponent1;
    }
    public Component getSellComponent1(){
        return sellComponent1;
    }
    public Component getQstockComponent1(){
        return qStockComponent1;
    }
    public Component getBuyTooltipComp1(){
        return buyTooltipComp1;
    }
    public Component getSellTooltipComp1(){
        return sellTooltipComp1;
    }

}
