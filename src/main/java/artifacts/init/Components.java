package artifacts.init;

import artifacts.Artifacts;
import artifacts.components.BooleanComponent;
import artifacts.components.EntityKillTrackerComponent;
import artifacts.components.SwimAbilityComponent;
import artifacts.components.SyncedBooleanComponent;
import artifacts.item.curio.TrinketArtifactItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.item.ItemEntity;

import java.util.concurrent.atomic.AtomicInteger;

public class Components implements EntityComponentInitializer, ItemComponentInitializer {

	public static final ComponentKey<SyncedBooleanComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("dropped_item_entity"), SyncedBooleanComponent.class);
	public static final ComponentKey<BooleanComponent> ARTIFACT_ENABLED =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("trinket_enabled"), BooleanComponent.class);
	public static final ComponentKey<SwimAbilityComponent> SWIM_ABILITIES =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("swim_abilities"), SwimAbilityComponent.class);
	public static final ComponentKey<EntityKillTrackerComponent> ENTITY_KILL_TRACKER =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("entity_kill_tracker"), EntityKillTrackerComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new SyncedBooleanComponent("wasDropped"));
		registry.registerForPlayers(SWIM_ABILITIES, SwimAbilityComponent::new, RespawnCopyStrategy.LOSSLESS_ONLY);
		registry.registerForPlayers(ENTITY_KILL_TRACKER, entity -> new EntityKillTrackerComponent(), RespawnCopyStrategy.LOSSLESS_ONLY);
	}

	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		// Non-dynamic component registration, might fix this issue: https://github.com/florensie/artifacts-fabric/issues/35
		AtomicInteger registerCount = new AtomicInteger();
		Registry.ITEM.stream().filter(item -> item instanceof TrinketArtifactItem).forEach(item -> {
			registry.registerFor(item, ARTIFACT_ENABLED, stack -> new BooleanComponent("isEnabled", true));
			registerCount.getAndIncrement();
		});
		Artifacts.LOGGER.info("[Artifacts] Registered item components for {} Artifacts", registerCount.get());
	}
}
