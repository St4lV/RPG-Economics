package fr.st4lV.mcrpgeco.event;

import fr.st4lV.mcrpgeco.RPGEconomics;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import fr.st4lV.mcrpgeco.block.ModBlocks;
import fr.st4lV.mcrpgeco.item.ModItems;
import fr.st4lV.mcrpgeco.villager.ModVillagers;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.List;

@Mod.EventBusSubscriber(modid = RPGEconomics.MODID)
public class ModEvents {

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        if(event.getType() == ModVillagers.STOCK_TRADER.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();

            //LEVEL 1

                //BRONZE 2 SILVER COINS

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.BRONZE_COIN.get(), 64),
                    new ItemStack(ModItems.BRONZE_COIN.get(), 41),
                    new ItemStack(ModItems.SILVER_COIN.get(), 1),
                    10, 1, 0.02f));

                //PURSE 4 SILVER COINS

            trades.get(1).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.SILVER_COIN.get(), 15),
                    new ItemStack(ModItems.PURSE.get(), 1),
                    5, 2, 0.02f));

            //LEVEL 2

                //SILVER 2 GOLD COINS

            trades.get(2).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.SILVER_COIN.get(), 64),
                    new ItemStack(ModItems.SILVER_COIN.get(), 41),
                    new ItemStack(ModItems.GOLD_COIN.get(), 1),
                    10, 5, 0.02f));

            //LEVEL 3

                //BLOCKBERG TERMINAL 4 GOLD COINS

            trades.get(3).add((pTrader, pRandom) -> new MerchantOffer(
                    new ItemStack(ModItems.GOLD_COIN.get(), 6),
                    new ItemStack(ModBlocks.BLOCKBERG_TERMINAL.get(), 1),
                    3, 20, 0.02f));
        }
    }
}
