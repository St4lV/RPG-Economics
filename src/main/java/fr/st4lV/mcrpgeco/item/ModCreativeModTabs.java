package fr.st4lV.mcrpgeco.item;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RPGEconomics.MODID);

    public static final RegistryObject<CreativeModeTab> MCRPGECO_TAB = CREATIVE_MODE_TABS.register("mcrpgeco_tab",
            () -> CreativeModeTab.builder().icon(()-> new ItemStack(ModItems.BRONZE_COIN.get()))
                    .title(Component.translatable("creativetab.mcrpgeco_tab"))
                    .displayItems((pParameters, pOutput) -> {

                        for(RegistryObject<Item> item : ModItems.ITEMS.getEntries()) {

                            pOutput.accept(item.get());

                        }

                    })
                    .build());

    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }
}