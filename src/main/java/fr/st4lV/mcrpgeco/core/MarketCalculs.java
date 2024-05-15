package fr.st4lV.mcrpgeco.core;

import fr.st4lV.mcrpgeco.GrabJson;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import java.io.IOException;

public class MarketCalculs {

    private static MarketCalculs instance;

    private MarketCalculs() {
    }

    public static MarketCalculs getInstance() {
        if (instance == null) {
            instance = new MarketCalculs();
        }
        return instance;
    }

    // Variables
    private int InitPrice, InitPlayerAccount, Qmax, Qratio, PlayerAccount, updatedPlayerAccount, QstartRatio, ActPrice;
    private double buyPrice, sellPrice;
    public int gcPlayer, scPlayer, bcPlayer, gcBuy, scBuy, bcBuy, gcSell, scSell, bcSell, Qact;
    private int QJoueur = 15;

    // Initialize values based on item ID
    public void initValuesById(int itemId) throws IOException {
        GrabJson.Values values = GrabJson.grabJSON(itemId);
        if (values != null) {
            InitPrice = values.price;
            Qmax = values.qMax;
            QstartRatio = values.qStartRatio;
            Qratio = QstartRatio;
            Qact = Qmax * QstartRatio / 100;
            ActPrice = InitPrice;
            InitPlayerAccount = 6942000;  // This should be dynamic based on player data
            PlayerAccount = InitPlayerAccount;
            updatedPlayerAccount = PlayerAccount;

            // Update values initially after setting them
            updateMarketValues();
        } else {
            System.err.println("Item not found for ID: " + itemId);
        }
    }

    // Initialize values for a range of IDs
    public void initValuesForRange(int minID, int maxID) throws IOException {
        for (int id = minID; id <= maxID; id++) {
            initValuesById(id);
        }
    }

    // Update market values
    public void updateMarketValues() {
        PlayerAccount = updatedPlayerAccount;
        buyPrice = ActPrice + ((double) (ActPrice * (101 - Qratio)) / 10000);
        sellPrice = ActPrice - ((double) (ActPrice * (101 - Qratio)) / 20000);

        gcPlayer = PlayerAccount / 10000;
        scPlayer = (PlayerAccount % 10000) / 100;
        bcPlayer = PlayerAccount % 100;

        gcBuy = (int) buyPrice / 10000;
        scBuy = (int) (buyPrice % 10000) / 100;
        bcBuy = (int) buyPrice % 100;

        gcSell = (int) sellPrice / 10000;
        scSell = (int) (sellPrice % 10000) / 100;
        bcSell = (int) sellPrice % 100;

        System.out.println("You currently have " + gcPlayer + " gold coins " + scPlayer + " silver coins " + bcPlayer + " bronze coin ");
        System.out.println("You currently have " + QJoueur + " Golden Apple");
        System.out.println("Quantity available: " + Qact + " Golden Apple");
        System.out.println("Actual quantity ratio(Qratio): " + Qratio);
        System.out.println("Actual price (PrixAct): " + ActPrice + "bc");
        System.out.println("Buy price: " + gcBuy + "g" + scBuy + "s" + bcBuy + "b");
        System.out.println("Sell price: " + gcSell + "g" + scSell + "s" + bcSell + "b");
        System.out.println("PlayerAccount: " + PlayerAccount);
    }

    // Buy item
    public void buyItem() {
        if (Qact <= 0) {
            System.out.println("Can't buy item: item currently unavailable");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't buy item → §o§7item currently unavailable"));
        } else if (PlayerAccount < buyPrice) {
            System.out.println("Can't buy item: not enough money!");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't buy item → §o§7not enough money!"));
        } else if (PlayerAccount >= buyPrice && Qact >= 0) {
            System.out.println("Successfully bought for " + gcBuy + " gold coins " + scBuy + " silver coins " + bcBuy + " bronze coin ");
            updatedPlayerAccount = PlayerAccount - (int) buyPrice;
            Qact--;
            QJoueur++;
            ActPrice += (int) (ActPrice * ((100 - Qratio)/4) / Qmax);
            Qratio = Qact * 100 / Qmax;

            String buy_successful = "§a✔ §b⇊ §f" +
                    (gcBuy != 0 ? gcBuy + "§e§l⌾ §f" : "") +
                    (scBuy != 0 ? scBuy + "§7§l⌾ §f" : "") +
                    bcBuy + "§6§l⌾ §f : Item bought.";

            Minecraft.getInstance().player.sendSystemMessage(Component.literal(buy_successful));

            updateMarketValues();
        } else {
            System.err.println("Can't buy item: error please report.");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't buy item → §o§7error please report."));
        }
    }

    // Sell item
    public void sellItem() {
        if (Qact == Qmax) {
            System.out.println("Can't sell item: too much in the market.");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't sell item → §o§7too much in the market."));
        } else if (QJoueur <= 0) {
            System.out.println("Can't sell item: you are not in possession of this item.");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't sell item → §o§7you are not in possession of this item."));
        } else if (ActPrice <= 5) {
            System.out.println("Can't sell item: price too low!");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't sell item → §o§7price too low!"));
        } else if (QJoueur >= 1 && Qact <= Qmax) {
            System.out.println("Successfully sold for: " + gcSell + " gold coins " + scSell + " silver coins " + bcSell + " bronze coins ");
            String sell_successful = "§a✔ §d⇈ §f" +
                    (gcSell != 0 ? gcSell + "§e§l⌾ §f" : "") +
                    (scSell != 0 ? scSell + "§7§l⌾ §f" : "") +
                    bcSell + "§6§l⌾ §f : Item sold.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_successful));

            updatedPlayerAccount = PlayerAccount + (int) sellPrice;
            Qact++;
            QJoueur--;
            ActPrice -= (int) (ActPrice * ((100 - Qratio)/5) / Qmax);
            Qratio = Qact * 100 / Qmax;

            updateMarketValues();
        } else {
            System.err.println("Can't sell item: error.");
            Minecraft.getInstance().player.sendSystemMessage(Component.literal("§4❌ §f⇛ Can't sell item → §o§7error."));
        }
    }
}
