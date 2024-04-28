package fr.st4lV.mcrpgeco.datagen;

import fr.st4lV.mcrpgeco.RPGEconomics;
import fr.st4lV.mcrpgeco.item.ModItems;
import fr.st4lV.mcrpgeco.loot.AddItemModifier;
import fr.st4lV.mcrpgeco.loot.AddSusSandItemModifier;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, RPGEconomics.MODID);
    }

    @Override
    protected void start() {


        //BRONZE COINS


            //ENTITIES

        add("bronze_coin_from_creeper", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/creeper")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_zombie", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_zombie_villager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombie_villager")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_zombified_piglin", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zombified_piglin")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_drowned", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/drowned")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_husk", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/husk")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_spider", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/spider")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_cave_spider", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/cave_spider")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/skeleton")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_stray", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/stray")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_pillager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/pillager")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_witch", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/witch")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_silverfish", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/silverfish")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_blaze", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/blaze")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_endermite", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/endermite")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_magma_cube", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/magma_cube")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_slime", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/slime")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_phantom", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/phantom")).build() }, ModItems.BRONZE_COIN.get()));

            //CHESTS

        add("bronze_coin_from_villages", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/village")).build() }, ModItems.BRONZE_COIN.get()));

        add("bronze_coin_from_beached_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/beached_shipwreck")).build() }, ModItems.BRONZE_COIN.get()));


        //SILVER COINS


            //ENTITIES

        add("silver_coin_from_enderman", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/enderman")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_wither_skeleton", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/wither_skeleton")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_vindicator", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/vindicator")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_ravager", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/ravager")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_ghast", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/ghast")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_piglin", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/piglin")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_hoglin", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/hoglin")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_zoglin", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/zoglin")).build() }, ModItems.SILVER_COIN.get()));

        add("bronze_coin_from_guardian", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/guardian")).build() }, ModItems.BRONZE_COIN.get()));


        //CHESTS

        add("silver_coin_from_jungle_temples", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/jungle_pyramid")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_desert_pyramid", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/desert_pyramid")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_nether_fortress", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/nether_fortress")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_bastion_remnant", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/bastion_remnant")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_ruined_portal", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ruined_portal")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_shipwreck", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/shipwreck")).build() }, ModItems.SILVER_COIN.get()));

        add("silver_coin_from_ancient_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/ancient_city")).build() }, ModItems.SILVER_COIN.get()));

        //GOLD COINS


            //ENTITIES

        add("gold_coin_from_shulker", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/shulker")).build() }, ModItems.GOLD_COIN.get()));

        add("gold_coin_from_evoker", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/evoker")).build() }, ModItems.GOLD_COIN.get()));

        add("gold_coin_from_piglin_brute", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/piglin_brute")).build() }, ModItems.GOLD_COIN.get()));

        add("bronze_coin_from_elder_guardian", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("entities/elder_guardian")).build() }, ModItems.GOLD_COIN.get()));

        //CHESTS

        add("gold_coin_from_buried_treasure", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/buried_treasure")).build() }, ModItems.GOLD_COIN.get()));

        add("gold_coin_from_end_city", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/end_city")).build() }, ModItems.GOLD_COIN.get()));

        add("gold_coin_from_stronghold", new AddItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("chests/stronghold")).build() }, ModItems.GOLD_COIN.get()));

        //SUS SAND

        add("gold_coin_from_suspicious_sand", new AddSusSandItemModifier(new LootItemCondition[] {
                new LootTableIdCondition.Builder(new ResourceLocation("archaeology/desert_pyramid")).build() }, ModItems.GOLD_COIN.get()));

    }
}
