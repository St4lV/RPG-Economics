package fr.st4lV.mcrpgeco.block.entity;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RPGEconomics.MODID);

    public static final RegistryObject<BlockEntityType<BlockbergTerminalBlockEntity>> BLOCKBERG_BE =
            BLOCK_ENTITIES.register("blockberg_be", () ->
                    BlockEntityType.Builder.of(BlockbergTerminalBlockEntity::new,
                            ModBlocks.BLOCKBERG_TERMINAL.get()).build(null));


    public static void register(IEventBus eventBus) {
        BLOCK_ENTITIES.register(eventBus);
    }
}