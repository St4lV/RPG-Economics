package fr.st4lV.mcrpgeco.villager;

import com.google.common.collect.ImmutableSet;
import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.block.ModBlocks;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
public class ModVillagers {
    public static final DeferredRegister<PoiType> POI_TYPES =
            DeferredRegister.create(ForgeRegistries.POI_TYPES, RPGEconomics.MODID);
    public static final DeferredRegister<VillagerProfession> VILLAGER_PROFESSIONS =
            DeferredRegister.create(ForgeRegistries.VILLAGER_PROFESSIONS, RPGEconomics.MODID);

    public static final RegistryObject<PoiType> BLOCKBERG_POI = POI_TYPES.register("blockberg_poi",
            () -> new PoiType(ImmutableSet.copyOf(ModBlocks.BLOCKBERG_TERMINAL.get().getStateDefinition().getPossibleStates()),
                    1, 1));

    public static final RegistryObject<VillagerProfession> STOCK_TRADER =
            VILLAGER_PROFESSIONS.register("stocktrader", () -> new VillagerProfession("stocktrader",
                    holder -> holder.get() == BLOCKBERG_POI.get(), holder -> holder.get() == BLOCKBERG_POI.get(),
                    ImmutableSet.of(), ImmutableSet.of(), SoundEvents.VILLAGER_WORK_ARMORER));



    public static void register(IEventBus eventBus) {
        POI_TYPES.register(eventBus);
        VILLAGER_PROFESSIONS.register(eventBus);
    }
}