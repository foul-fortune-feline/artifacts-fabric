package artifacts;

import artifacts.common.compat.CompatHandler;
import artifacts.common.config.ModConfig;
import artifacts.common.init.ModFeatures;
import artifacts.common.init.ModItems;
import artifacts.common.init.ModLootConditions;
import artifacts.common.init.ModLootTables;
import artifacts.common.init.ModSoundEvents;
import artifacts.common.init.ModToolHandlers;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Artifacts implements ModInitializer {

	public static final String MOD_ID = "artifacts";
	public static final Logger LOGGER = LogManager.getLogger(MOD_ID);
	public static final CreativeModeTab ITEM_GROUP = FabricItemGroupBuilder.build(
			id("item_group"),
			() -> new ItemStack(ModItems.BUNNY_HOPPERS)
	);
	public static ModConfig CONFIG;

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Config
		Artifacts.CONFIG = AutoConfig.register(ModConfig.class,
				PartitioningSerializer.wrap(Toml4jConfigSerializer::new)).getConfig();

		// Loot table setup
		ModLootConditions.register();
		LootTableLoadingCallback.EVENT.register((rm, lt, id, supplier, s) ->
				ModLootTables.onLootTableLoad(id, supplier));

		// Force loading init classes
		// Entities is loaded by items, loot tables can load lazily (no registration)
		ModItems.ANTIDOTE_VESSEL.toString();
		ModSoundEvents.MIMIC_CLOSE.toString();
		ModFeatures.register();

		runCompatibilityHandlers();

		// Tool Handlers
		ModToolHandlers.register();
		LOGGER.info("[Artifacts] Finished initialization");
	}

	private void runCompatibilityHandlers() {
		FabricLoader.getInstance().getEntrypoints("artifacts:compat_handlers", CompatHandler.class).stream()
				.filter(h -> FabricLoader.getInstance().isModLoaded(h.getModId()))
				.forEach(ch -> {
					String modName = FabricLoader.getInstance().getModContainer(ch.getModId())
							.map(c -> c.getMetadata().getName())
							.orElse(ch.getModId());
					LOGGER.info("[Artifacts] Running compat handler for " + modName);

					ch.run();
				});
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MOD_ID, path);
	}
}
