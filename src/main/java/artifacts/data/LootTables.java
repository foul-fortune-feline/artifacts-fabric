package artifacts.data;

import artifacts.Artifacts;
import artifacts.common.init.ModItems;
import artifacts.common.init.ModLootTables;
import artifacts.common.loot.ConfigurableRandomChance;
import com.google.common.base.Preconditions;
import com.mojang.datafixers.util.Pair;
import net.minecraft.advancements.criterion.EntityFlagsPredicate;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.LootTableProvider;
import net.minecraft.item.Item;
import net.minecraft.loot.*;
import net.minecraft.loot.conditions.EntityHasProperty;
import net.minecraft.loot.functions.Smelt;
import net.minecraft.resources.ResourcePackType;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class LootTables extends LootTableProvider {

    private final List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> tables = new ArrayList<>();

    private final ExistingFileHelper existingFileHelper;

    public LootTables(DataGenerator dataGenerator, ExistingFileHelper existingFileHelper) {
        super(dataGenerator);
        this.existingFileHelper = existingFileHelper;
    }

    @Override
    protected List<Pair<Supplier<Consumer<BiConsumer<ResourceLocation, LootTable.Builder>>>, LootParameterSet>> getTables() {
        tables.clear();
        addDrinkingHatsLootTable();
        addArtifactsLootTable();
        addChestLootTables();
        addLootTable("inject/entities/cow", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(1 / 500F))
                        .addEntry(createItemEntry(ModItems.EVERLASTING_BEEF.get(), 1)
                                .acceptFunction(
                                        Smelt.func_215953_b().acceptCondition(
                                                EntityHasProperty.builder(
                                                        LootContext.EntityTarget.THIS,
                                                        EntityPredicate.Builder.create().flags(
                                                                EntityFlagsPredicate.Builder.create()
                                                                        .onFire(true)
                                                                        .build()
                                                        )
                                                )
                                        )
                                )
                        )
                ),
                LootParameterSets.ENTITY
        );

        return tables;
    }

    private void addArtifactsLootTable() {
        addLootTable("artifact", LootTable.builder()
                .addLootPool(
                        LootPool.builder()
                                .name("main")
                                .rolls(ConstantRange.of(1))
                                .addEntry(createItemEntry(ModItems.SNORKEL.get(), 8))
                                .addEntry(createItemEntry(ModItems.NIGHT_VISION_GOGGLES.get(), 8))
                                .addEntry(createItemEntry(ModItems.PANIC_NECKLACE.get(), 8))
                                .addEntry(createItemEntry(ModItems.SHOCK_PENDANT.get(), 8))
                                .addEntry(createItemEntry(ModItems.FLAME_PENDANT.get(), 8))
                                .addEntry(createItemEntry(ModItems.THORN_PENDANT.get(), 8))
                                .addEntry(createItemEntry(ModItems.FLIPPERS.get(), 8))
                                .addEntry(createItemEntry(ModItems.OBSIDIAN_SKULL.get(), 8))
                                .addEntry(createItemEntry(ModItems.FIRE_GAUNTLET.get(), 8))
                                .addEntry(createItemEntry(ModItems.FERAL_CLAWS.get(), 8))
                                .addEntry(createItemEntry(ModItems.POCKET_PISTON.get(), 8))
                                .addEntry(createItemEntry(ModItems.POWER_GLOVE.get(), 8))
                                .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 8))
                                .addEntry(createItemEntry(ModItems.ANTIDOTE_VESSEL.get(), 8))
                                .addEntry(createItemEntry(ModItems.LUCKY_SCARF.get(), 8))
                                .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 8))
                                .addEntry(createItemEntry(ModItems.SCARF_OF_INVISIBILITY.get(), 8))
                                .addEntry(createItemEntry(ModItems.DIGGING_CLAWS.get(), 8))
                                .addEntry(createItemEntry(ModItems.STEADFAST_SPIKES.get(), 8))
                                .addEntry(createItemEntry(ModItems.UNIVERSAL_ATTRACTOR.get(), 8))
                                .addEntry(createItemEntry(ModItems.KITTY_SLIPPERS.get(), 8))
                                .addEntry(createItemEntry(ModItems.RUNNING_SHOES.get(), 8))
                                .addEntry(createItemEntry(ModItems.BUNNY_HOPPERS.get(), 8))
                                .addEntry(createItemEntry(ModItems.CRYSTAL_HEART.get(), 8))
                                .addEntry(createItemEntry(ModItems.VILLAGER_HAT.get(), 8))
                                .addEntry(createItemEntry(ModItems.CLOUD_IN_A_BOTTLE.get(), 8))
                                .addEntry(createItemEntry(ModItems.VAMPIRIC_GLOVE.get(), 8))
                                .addEntry(createItemEntry(ModItems.GOLDEN_HOOK.get(), 8))
                                .addEntry(createItemEntry(ModItems.CHARM_OF_SINKING.get(), 8))
                                .addEntry(createItemEntry(ModItems.AQUA_DASHERS.get(), 8))
                                .addEntry(createDrinkingHatEntry(8))
                                .addEntry(createItemEntry(ModItems.UMBRELLA.get(), 5))
                                .addEntry(createItemEntry(ModItems.WHOOPEE_CUSHION.get(), 5))
                                .addEntry(createItemEntry(ModItems.HELIUM_FLAMINGO.get(), 4))
                                .addEntry(createItemEntry(ModItems.EVERLASTING_BEEF.get(), 2))
                )
        );
    }

    private void addDrinkingHatsLootTable() {
        addLootTable("items/drinking_hat", LootTable.builder()
                .addLootPool(
                        LootPool.builder()
                                .name("main")
                                .rolls(ConstantRange.of(1))
                                .addEntry(createItemEntry(ModItems.PLASTIC_DRINKING_HAT.get(), 3))
                                .addEntry(createItemEntry(ModItems.NOVELTY_DRINKING_HAT.get(), 1))
                )
        );
    }

    private void addChestLootTables() {
        for (String biome : Arrays.asList("desert", "plains", "savanna", "snowy", "taiga")) {
            addLootTable(
                    String.format("inject/chests/village/village_%s_house", biome),
                    LootTable.builder().addLootPool(
                            LootPool.builder()
                                    .name("main")
                                    .rolls(ConstantRange.of(1))
                                    .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.02F))
                                    .addEntry(createItemEntry(ModItems.VILLAGER_HAT.get(), 1))
                    )
            );
        }
        addLootTable("inject/chests/spawn_bonus_chest", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(1))
                        .rolls(ConstantRange.of(1))
                        .addEntry(createItemEntry(ModItems.WHOOPEE_CUSHION.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_armorer", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.1F))
                        .addEntry(createItemEntry(ModItems.STEADFAST_SPIKES.get(), 1))
                        .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 1))
                        .addEntry(createItemEntry(ModItems.RUNNING_SHOES.get(), 1))
                        .addEntry(createItemEntry(ModItems.VAMPIRIC_GLOVE.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_butcher", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.02F))
                        .addEntry(createItemEntry(ModItems.EVERLASTING_BEEF.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_tannery", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.2F))
                        .addEntry(createItemEntry(ModItems.UMBRELLA.get(), 3))
                        .addEntry(createItemEntry(ModItems.WHOOPEE_CUSHION.get(), 2))
                        .addEntry(createItemEntry(ModItems.KITTY_SLIPPERS.get(), 1))
                        .addEntry(createItemEntry(ModItems.BUNNY_HOPPERS.get(), 1))
                        .addEntry(createItemEntry(ModItems.SCARF_OF_INVISIBILITY.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_temple", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.2F))
                        .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 1))
                        .addEntry(createItemEntry(ModItems.ANTIDOTE_VESSEL.get(), 1))
                        .addEntry(createItemEntry(ModItems.CHARM_OF_SINKING.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_toolsmith", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.15F))
                        .addEntry(createItemEntry(ModItems.DIGGING_CLAWS.get(), 1))
                        .addEntry(createItemEntry(ModItems.POCKET_PISTON.get(), 1))
                )
        );
        addLootTable("inject/chests/village/village_weaponsmith", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.1F))
                        .addEntry(createItemEntry(ModItems.FERAL_CLAWS.get(), 1))
                )
        );
        addLootTable("inject/chests/abandoned_mineshaft", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.30F))
                        .addEntry(createItemEntry(ModItems.NIGHT_VISION_GOGGLES.get(), 2))
                        .addEntry(createItemEntry(ModItems.PANIC_NECKLACE.get(), 2))
                        .addEntry(createItemEntry(ModItems.OBSIDIAN_SKULL.get(), 2))
                        .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 2))
                        .addEntry(createItemEntry(ModItems.DIGGING_CLAWS.get(), 2))
                        .addEntry(createItemEntry(ModItems.CLOUD_IN_A_BOTTLE.get(), 2))
                        .addEntry(createItemEntry(ModItems.VAMPIRIC_GLOVE.get(), 2))
                        .addEntry(createItemEntry(ModItems.AQUA_DASHERS.get(), 2))
                        .addEntry(createDrinkingHatEntry(2))
                )
        );
        addLootTable("inject/chests/bastion_hoglin_stable", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.20F))
                        .addEntry(createArtifactEntry(5))
                        .addEntry(createItemEntry(ModItems.BUNNY_HOPPERS.get(), 3))
                        .addEntry(createItemEntry(ModItems.FLAME_PENDANT.get(), 3))
                        .addEntry(createItemEntry(ModItems.EVERLASTING_BEEF.get(), 1))
                )
        );
        addLootTable("inject/chests/bastion_treasure", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.65F))
                        .addEntry(createArtifactEntry(6))
                        .addEntry(createItemEntry(ModItems.GOLDEN_HOOK.get(), 3))
                        .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 3))
                        .addEntry(createItemEntry(ModItems.FIRE_GAUNTLET.get(), 2))
                        .addEntry(createItemEntry(ModItems.STEADFAST_SPIKES.get(), 1))
                        .addEntry(createItemEntry(ModItems.PANIC_NECKLACE.get(), 1))
                        .addEntry(createItemEntry(ModItems.CRYSTAL_HEART.get(), 1))
                        .addEntry(createItemEntry(ModItems.ANTIDOTE_VESSEL.get(), 1))
                )
        );
        addLootTable("inject/chests/buried_treasure", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.25F))
                        .addEntry(createItemEntry(ModItems.SNORKEL.get(), 5))
                        .addEntry(createItemEntry(ModItems.FLIPPERS.get(), 5))
                        .addEntry(createItemEntry(ModItems.UMBRELLA.get(), 5))
                        .addEntry(createItemEntry(ModItems.GOLDEN_HOOK.get(), 5))
                        .addEntry(createItemEntry(ModItems.FERAL_CLAWS.get(), 3))
                        .addEntry(createItemEntry(ModItems.DIGGING_CLAWS.get(), 3))
                        .addEntry(createItemEntry(ModItems.KITTY_SLIPPERS.get(), 3))
                        .addEntry(createItemEntry(ModItems.BUNNY_HOPPERS.get(), 3))
                        .addEntry(createItemEntry(ModItems.LUCKY_SCARF.get(), 3))
                        .addEntry(createItemEntry(ModItems.AQUA_DASHERS.get(), 3))
                        .addEntry(createDrinkingHatEntry(3))
                )
        );
        addLootTable("inject/chests/desert_pyramid", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.2F))
                        .addEntry(createItemEntry(ModItems.FLAME_PENDANT.get(), 2))
                        .addEntry(createItemEntry(ModItems.THORN_PENDANT.get(), 2))
                        .addEntry(createItemEntry(ModItems.WHOOPEE_CUSHION.get(), 2))
                        .addEntry(createItemEntry(ModItems.CHARM_OF_SINKING.get(), 2))
                        .addEntry(createItemEntry(ModItems.SHOCK_PENDANT.get(), 1))
                        .addEntry(createItemEntry(ModItems.UMBRELLA.get(), 1))
                        .addEntry(createItemEntry(ModItems.SCARF_OF_INVISIBILITY.get(), 1))
                        .addEntry(createItemEntry(ModItems.UNIVERSAL_ATTRACTOR.get(), 1))
                        .addEntry(createItemEntry(ModItems.VAMPIRIC_GLOVE.get(), 1))
                )
        );
        addLootTable("inject/chests/end_city_treasure", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.3F))
                        .addEntry(createArtifactEntry(3))
                        .addEntry(createItemEntry(ModItems.CRYSTAL_HEART.get(), 1))
                        .addEntry(createItemEntry(ModItems.CLOUD_IN_A_BOTTLE.get(), 1))
                        .addEntry(createItemEntry(ModItems.HELIUM_FLAMINGO.get(), 4))
                )
        );
        addLootTable("inject/chests/jungle_temple", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.3F))
                        .addEntry(createItemEntry(ModItems.KITTY_SLIPPERS.get(), 2))
                        .addEntry(createItemEntry(ModItems.BUNNY_HOPPERS.get(), 1))
                )
        );
        addLootTable("inject/chests/nether_bridge", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.15F))
                        .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 3))
                        .addEntry(createItemEntry(ModItems.NIGHT_VISION_GOGGLES.get(), 3))
                        .addEntry(createItemEntry(ModItems.POCKET_PISTON.get(), 3))
                        .addEntry(createItemEntry(ModItems.RUNNING_SHOES.get(), 3))
                        .addEntry(createDrinkingHatEntry(3))
                )
        );
        addLootTable("inject/chests/pillager_outpost", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.25F))
                        .addEntry(createItemEntry(ModItems.PANIC_NECKLACE.get(), 1))
                        .addEntry(createItemEntry(ModItems.POCKET_PISTON.get(), 1))
                        .addEntry(createItemEntry(ModItems.STEADFAST_SPIKES.get(), 1))
                        .addEntry(createItemEntry(ModItems.POWER_GLOVE.get(), 1))
                        .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 1))
                        .addEntry(createItemEntry(ModItems.SCARF_OF_INVISIBILITY.get(), 1))
                        .addEntry(createItemEntry(ModItems.CRYSTAL_HEART.get(), 1))
                        .addEntry(createItemEntry(ModItems.CLOUD_IN_A_BOTTLE.get(), 1))
                        .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 1))
                )
        );
        addLootTable("inject/chests/ruined_portal", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.15F))
                        .addEntry(createItemEntry(ModItems.NIGHT_VISION_GOGGLES.get(), 1))
                        .addEntry(createItemEntry(ModItems.THORN_PENDANT.get(), 1))
                        .addEntry(createItemEntry(ModItems.FIRE_GAUNTLET.get(), 1))
                        .addEntry(createItemEntry(ModItems.POWER_GLOVE.get(), 1))
                        .addEntry(createItemEntry(ModItems.UNIVERSAL_ATTRACTOR.get(), 1))
                        .addEntry(createItemEntry(ModItems.OBSIDIAN_SKULL.get(), 1))
                        .addEntry(createItemEntry(ModItems.LUCKY_SCARF.get(), 1))
                )
        );
        addLootTable("inject/chests/shipwreck_treasure", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.15F))
                        .addEntry(createItemEntry(ModItems.GOLDEN_HOOK.get(), 3))
                        .addEntry(createItemEntry(ModItems.SNORKEL.get(), 1))
                        .addEntry(createItemEntry(ModItems.FLIPPERS.get(), 1))
                        .addEntry(createItemEntry(ModItems.SCARF_OF_INVISIBILITY.get(), 1))
                        .addEntry(createItemEntry(ModItems.STEADFAST_SPIKES.get(), 1))
                        .addEntry(createItemEntry(ModItems.UNIVERSAL_ATTRACTOR.get(), 1))
                        .addEntry(createItemEntry(ModItems.FERAL_CLAWS.get(), 1))
                        .addEntry(createItemEntry(ModItems.NIGHT_VISION_GOGGLES.get(), 1))
                        .addEntry(createItemEntry(ModItems.OBSIDIAN_SKULL.get(), 1))
                        .addEntry(createItemEntry(ModItems.RUNNING_SHOES.get(), 1))
                        .addEntry(createItemEntry(ModItems.CHARM_OF_SINKING.get(), 1))
                )
        );
        addLootTable("inject/chests/stronghold_corridor", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.3F))
                        .addEntry(createArtifactEntry(3))
                        .addEntry(createItemEntry(ModItems.POWER_GLOVE.get(), 1))
                        .addEntry(createItemEntry(ModItems.ANTIDOTE_VESSEL.get(), 1))
                        .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 1))
                        .addEntry(createItemEntry(ModItems.LUCKY_SCARF.get(), 1))
                        .addEntry(createItemEntry(ModItems.AQUA_DASHERS.get(), 1))
                        .addEntry(createItemEntry(ModItems.HELIUM_FLAMINGO.get(), 1))
                )
        );
        addLootTable("inject/chests/underwater_ruin_big", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.45F))
                        .addEntry(createItemEntry(ModItems.SNORKEL.get(), 3))
                        .addEntry(createItemEntry(ModItems.FLIPPERS.get(), 3))
                        .addEntry(createItemEntry(ModItems.SUPERSTITIOUS_HAT.get(), 1))
                        .addEntry(createItemEntry(ModItems.LUCKY_SCARF.get(), 1))
                        .addEntry(createItemEntry(ModItems.FIRE_GAUNTLET.get(), 1))
                        .addEntry(createItemEntry(ModItems.CROSS_NECKLACE.get(), 1))
                        .addEntry(createItemEntry(ModItems.POWER_GLOVE.get(), 1))
                        .addEntry(createItemEntry(ModItems.CLOUD_IN_A_BOTTLE.get(), 1))
                )
        );
        addLootTable("inject/chests/woodland_mansion", LootTable.builder().addLootPool(
                LootPool.builder()
                        .name("main")
                        .rolls(ConstantRange.of(1))
                        .acceptCondition(ConfigurableRandomChance.configurableRandomChance(0.25F))
                        .addEntry(createArtifactEntry(1))
                )
        );
    }

    private static StandaloneLootEntry.Builder<?> createItemEntry(Item item, int weight) {
        return ItemLootEntry.builder(item).weight(weight);
    }

    private static LootEntry.Builder<?> createArtifactEntry(int weight) {
        return createLootTableEntry("artifact", weight);
    }

    private static LootEntry.Builder<?> createDrinkingHatEntry(int weight) {
        return createLootTableEntry("items/drinking_hat", weight);
    }

    private static LootEntry.Builder<?> createLootTableEntry(String lootTable, int weight) {
        return TableLootEntry.builder(new ResourceLocation(Artifacts.MODID, lootTable)).weight(weight);
    }

    private void addLootTable(String location, LootTable.Builder lootTable, LootParameterSet lootParameterSet) {
        if (location.startsWith("inject/")) {
            String actualLocation = location.replace("inject/", "");
            Preconditions.checkArgument(existingFileHelper.exists(new ResourceLocation("loot_tables/" + actualLocation + ".json"), ResourcePackType.SERVER_DATA), "Loot table %s does not exist in any known data pack", actualLocation);
            Preconditions.checkArgument(ModLootTables.LootTableEvents.LOOT_TABLE_LOCATIONS.contains(actualLocation), "Loot table %s does not exist in list of injected loot tables", actualLocation);
        }
        tables.add(Pair.of(() -> lootBuilder -> lootBuilder.accept(new ResourceLocation(Artifacts.MODID, location), lootTable), lootParameterSet));
    }

    private void addLootTable(String location, LootTable.Builder lootTable) {
        addLootTable(location, lootTable, LootParameterSets.GENERIC);
    }

    @Override
    protected void validate(Map<ResourceLocation, LootTable> map, ValidationTracker validationtracker) {
        map.forEach((loc, table) -> LootTableManager.validateLootTable(validationtracker, loc, table));
    }
}
