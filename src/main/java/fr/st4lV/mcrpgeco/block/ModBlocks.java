package fr.st4lV.mcrpgeco.block;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.custom.BlockbergTerminal;
//import fr.st4lV.mcrpgeco.block.custom.ScreenBlock;
import fr.st4lV.mcrpgeco.item.ModItems;
import fr.st4lV.mcrpgeco.block.entity.BlockbergTerminalBlockEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, RPGEconomics.MODID);

    //TEST BLOCK

    public static final RegistryObject<Block> TEST_BLOCK = registerBlock("test_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.SMOOTH_STONE).sound(SoundType.CALCITE)));

    //BLOCKBERG TERMINAL
    public static final RegistryObject<Block> BLOCKBERG_TERMINAL = registerBlock("blockberg_terminal",
            () -> new BlockbergTerminal(BlockBehaviour.Properties.copy(Blocks.CRYING_OBSIDIAN).sound(SoundType.NETHERITE_BLOCK)));

    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
