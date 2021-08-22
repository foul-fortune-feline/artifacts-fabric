package artifacts.init;

import artifacts.Artifacts;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import java.util.Arrays;
import java.util.List;

public class LootTables {

	public static final ResourceLocation MIMIC = Artifacts.id("entities/mimic");
	public static final ResourceLocation CAMPSITE_CHEST = Artifacts.id("chests/campsite_chest");

	public static final List<ResourceLocation> INJECT_TABLE_IDS = Arrays.asList(
			new ResourceLocation("minecraft", "chests/village/village_armorer"),
			new ResourceLocation("minecraft", "chests/village/village_butcher"),
			new ResourceLocation("minecraft", "chests/village/village_tannery"),
			new ResourceLocation("minecraft", "chests/village/village_temple"),
			new ResourceLocation("minecraft", "chests/village/village_toolsmith"),
			new ResourceLocation("minecraft", "chests/village/village_weaponsmith"),
			new ResourceLocation("minecraft", "chests/village/village_desert_house"),
			new ResourceLocation("minecraft", "chests/village/village_plains_house"),
			new ResourceLocation("minecraft", "chests/village/village_savanna_house"),
			new ResourceLocation("minecraft", "chests/village/village_snowy_house"),
			new ResourceLocation("minecraft", "chests/village/village_taiga_house"),
			new ResourceLocation("minecraft", "chests/abandoned_mineshaft"),
			new ResourceLocation("minecraft", "chests/bastion_hoglin_stable"),
			new ResourceLocation("minecraft", "chests/bastion_treasure"),
			new ResourceLocation("minecraft", "chests/buried_treasure"),
			new ResourceLocation("minecraft", "chests/desert_pyramid"),
			new ResourceLocation("minecraft", "chests/end_city_treasure"),
			new ResourceLocation("minecraft", "chests/jungle_temple"),
			new ResourceLocation("minecraft", "chests/nether_bridge"),
			new ResourceLocation("minecraft", "chests/pillager_outpost"),
			new ResourceLocation("minecraft", "chests/ruined_portal"),
			new ResourceLocation("minecraft", "chests/shipwreck_treasure"),
			new ResourceLocation("minecraft", "chests/spawn_bonus_chest"),
			new ResourceLocation("minecraft", "chests/stronghold_corridor"),
			new ResourceLocation("minecraft", "chests/underwater_ruin_big"),
			new ResourceLocation("minecraft", "chests/woodland_mansion"),
			new ResourceLocation("minecraft", "entities/cow")
	);

	public static void onLootTableLoad(ResourceLocation id, FabricLootSupplierBuilder supplier) {
		if (INJECT_TABLE_IDS.contains(id)) {
			supplier.withPool(LootPool.lootPool().add(getInjectEntry(id.getPath())));
		}
	}

	private static LootPoolEntryContainer.Builder<?> getInjectEntry(String name) {
		ResourceLocation table = Artifacts.id("inject/" + name);
		return LootTableReference.lootTableReference(table).setWeight(1);
	}

	private LootTables() {
	}
}
