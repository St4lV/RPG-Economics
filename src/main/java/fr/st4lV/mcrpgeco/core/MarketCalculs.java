package fr.st4lV.mcrpgeco.core;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class MarketCalculs {

    private static MarketCalculs instance;

    private MarketCalculs() {
        // Private constructor to prevent instantiation from outside
    }

    public static MarketCalculs getInstance() {
        if (instance == null) {
            instance = new MarketCalculs();
        }
        return instance;
    }

    //init:

    private int InitPrice, InitPlayerAccount, Qmax, Qact, Qratio, PlayerAccount, updatedPlayerAccount, QstartRatio, ActPrice;
    private double buyPrice, sellPrice;
    public int gcPlayer, scPlayer, bcPlayer, gcBuy, scBuy, bcBuy, gcSell, scSell, bcSell;
    public void initValue(/*ServerPlayer player*/) {

        //!! remind !! : 101010 is 10gc 10sc 10bc
        //has to be >1000 for better results
        InitPrice = 10000;

        //CompoundTag playerData = player.getPersistentData();
        InitPlayerAccount = 69420;//playerData.getInt("money");



        // value : positive integer >= 0 : Start money in player account
        //Max quantity available in market
        Qmax = 512;

        PlayerAccount = InitPlayerAccount;
        updatedPlayerAccount = PlayerAccount;

        // RANGE : 1 - 100 ! default 85 : Quantity Available in market at init
        QstartRatio = 85;
        Qratio = QstartRatio;
        Qact = Qmax * QstartRatio / 100;
        ActPrice = InitPrice;
    }



    // Quantity of items at init for test player
    private int QJoueur = 15;

    //Initialisation of values:

    //Conversion from price Integers to in game display units
    public void updateMarketValues() {

        //differences between base, buy and sell values :
        PlayerAccount = updatedPlayerAccount;
        buyPrice  = ActPrice + ((double) (ActPrice * (101 - Qratio)) / 500);
        sellPrice = ActPrice - ((double) (ActPrice * (101 - Qratio)) / 1000);
        //Player
        gcPlayer = PlayerAccount / 10000;
        scPlayer = (PlayerAccount % 10000) / 100;
        bcPlayer = PlayerAccount % 100;

        //MarketPrices
        //Buy
        gcBuy = (int) buyPrice / 10000;
        scBuy = (int) (buyPrice % 10000) / 100;
        bcBuy = (int) buyPrice % 100;
        //Sell
        gcSell = (int) sellPrice / 10000;
        scSell = (int) (sellPrice % 10000) / 100;
        bcSell = (int) sellPrice % 100;

        System.out.println("You currently have "+ gcPlayer + "\u001B[93m gold coins\u001B[0m " + scPlayer + "\u001B[37m silver coins\u001B[0m " + bcPlayer + "\u001B[33m bronze coin\u001B[0m ");
        System.out.println("You currently have " + QJoueur + " Golden Apple");

        // Testing intended values display, uncomment for more visibility when testing :

        System.out.println("Quantity available: " + Qact + " Golden Apple");
        System.out.println("Actual quantity ratio(Qratio) : " + Qratio);
        System.out.println("Actual price (PrixAct) : " + ActPrice + "bc");
        System.out.println("Buy price : " + gcBuy + "g" + scBuy + "s" + bcBuy + "b");
        System.out.println("Sell price : " + gcSell + "g" + scSell + "s" + bcSell + "b");
        System.out.println("PlayerAccount : "+ (int)PlayerAccount);
    }

    //Buy case

    public void buyItem() {
        //Current item unavailable
        if (Qact <= 0) {

            System.out.println("Can't buy item: §o§7item currently unavailable");
            String buy_error_1= "§4❌ §f⇛ Can't buy item → §o§7item currently unavailable";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(buy_error_1));

            //Not enough money
        } else if (PlayerAccount < buyPrice) {

            System.out.println("Can't buy item: not enough money!");
            String buy_error_2= "§4❌ §f⇛ Can't buy item → §o§7not enough money!";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(buy_error_2));

            //Successful buy

        } else if (PlayerAccount >= buyPrice && Qact >= 0) {
            System.out.println("Successfully bought for " + gcBuy + "\u001B[93m⌾\u001B[0m " + scBuy + "\u001B[37m⌾\u001B[0m " + bcBuy + "\u001B[33m⌾\u001B[0m ");
            updatedPlayerAccount = PlayerAccount - (int) buyPrice;
            Qact--;
            QJoueur++;
            ActPrice += (ActPrice * (100 - Qratio) / Qmax);
            Qratio = Qact * 100 / Qmax;

            String buy_successful = "§a✔ §b⇊ §f" +
                    (gcBuy != 0 ? gcBuy + " §e⓪ §f" : "") +
                    (scBuy != 0 ? scBuy + " §7⓪ §f" : "") +
                    bcBuy + "§6 ⓪ §f : Item bought.";

            Minecraft.getInstance().player.sendSystemMessage(Component.literal(buy_successful));

            updateMarketValues();

        } else {
            System.err.println("Can't buy item: error please report.");
            String buy_error_2= "§4❌ §f⇛ Can't buy item → §o§7error please report.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(buy_error_2));
        }
    }

    //Sell case

    public void sellItem() {
        //Actual quantity = Max quantity
        if (Qact == Qmax) {
            System.out.println("Can't sell item : too much in the market.");
            String sell_error_1= "§4❌ §f⇛ Can't sell item → §o§7too much in the market.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_error_1));



            //Player doesn't have current item
        } else if (QJoueur <= 0) {
            System.out.println("Can't sell item : you are not in possession of this item.");
            String sell_error_2= "§4❌ §f⇛ Can't sell item → §o§7you are not in possession of this item.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_error_2));



        } else if (ActPrice <= 5) {
            System.out.println("Can't sell item : price too low!");
            String sell_error_3= "§4❌ §f⇛ Can't sell item → §o§7price too low!";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_error_3));



            //Successful sell
        } else if (QJoueur >= 0 && Qact <= Qmax) {
            System.out.println("Successfully sold for: " + gcSell + "\u001B[93m gold coins \u001B[0m " + scSell + "\u001B[37m silver coins \u001B[0m " + bcSell + "\u001B[33m bronze coins \u001B[0m ");
            String sell_successful = "§a✔ §d⇈ §f" +
                    (gcSell != 0 ? gcSell + " §e⓪ §f" : "") +
                    (scSell != 0 ? scSell + " §7⓪ §f" : "") +
                    bcSell + "§6⓪ §f : Item sold.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_successful));


            updatedPlayerAccount = PlayerAccount + (int) sellPrice;
            Qact++;
            QJoueur--;
            ActPrice -= (ActPrice * (100 - Qratio) / Qmax);
            Qratio = Qact * 100 / Qmax;

            updateMarketValues();

        } else {
            System.err.println("Can't sell item : error.");
            String sell_error_4= "§4❌ §f⇛ Can't sell item → §o§7error.";
            Minecraft.getInstance().player.sendSystemMessage(Component.literal(sell_error_4));
        }
    }
}