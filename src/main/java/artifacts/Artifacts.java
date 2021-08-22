package artifacts;

import artifacts.compat.HaemaCompat;
import artifacts.compat.OriginsCompat;
import artifacts.config.ModConfig;
import artifacts.init.*;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketSlots;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.fabricmc.loader.api.FabricLoader;
import net.fabricmc.loader.api.ModContainer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Artifacts implements ModInitializer {

	public static final String MODID = "artifacts";
	public static final Logger LOGGER = LogManager.getLogger(MODID);
	public static final CreativeModeTab ITEM_GROUP = FabricItemGroupBuilder.build(
			id("item_group"),
			() -> new ItemStack(Items.BUNNY_HOPPERS)
	);
	public static ModConfig CONFIG;
	private static final Map<String, Runnable> COMPAT_HANDLERS = Collections.unmodifiableMap(new HashMap<String, Runnable>() {{
		put("origins", new OriginsCompat());
		put("haema", new HaemaCompat());
	}});

	@Override
	@SuppressWarnings("ResultOfMethodCallIgnored")
	public void onInitialize() {
		// Config
		Artifacts.CONFIG = AutoConfig.register(ModConfig.class,
				PartitioningSerializer.wrap(Toml4jConfigSerializer::new)).getConfig();

		// Trinkets setup
		TrinketSlots.addSlot(SlotGroups.LEGS, Slots.BELT,
				new ResourceLocation("trinkets", "textures/item/empty_trinket_slot_belt.png"));
		TrinketSlots.addSlot(SlotGroups.CHEST, Slots.NECKLACE,
				new ResourceLocation("trinkets", "textures/item/empty_trinket_slot_necklace.png"));
		TrinketSlots.addSlot(SlotGroups.HEAD, artifacts.trinkets.Slots.HAT,
				id("textures/item/empty_trinket_slot_hat.png"));
		TrinketSlots.addSlot(SlotGroups.FEET, artifacts.trinkets.Slots.SHOES,
				id("textures/item/empty_trinket_slot_shoes.png"));
		TrinketSlots.addSlot(SlotGroups.HAND, Slots.GLOVES,
				new ResourceLocation("trinkets", "textures/item/empty_trinket_slot_gloves.png"));
		TrinketSlots.addSlot(SlotGroups.OFFHAND, Slots.GLOVES,
				new ResourceLocation("trinkets", "textures/item/empty_trinket_slot_gloves.png"));

		// Loot table setup
		LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> LootTables.onLootTableLoad(id, supplier));

		// Force loading init classes
		// Entities is loaded by items, loot tables can load lazily (no registration)
		Items.ANTIDOTE_VESSEL.toString();
		SoundEvents.MIMIC_CLOSE.toString();
		Features.register();

		// Compat Handlers
		COMPAT_HANDLERS.forEach((modId, compatHandler) -> {
			if (FabricLoader.getInstance().isModLoaded(modId)) {
				Optional<ModContainer> modContainer = FabricLoader.getInstance().getModContainer(modId);
				String modName = modContainer.isPresent() ? modContainer.get().getMetadata().getName() : modId;

				LOGGER.info("[Artifacts] Running compat handler for " + modName);
				compatHandler.run();
			}
		});

		// Tool Handlers
		ToolHandlers.register();
		LOGGER.info("[Artifacts] Finished initialization");
	}

	public static ResourceLocation id(String path) {
		return new ResourceLocation(MODID, path);
	}
}
