package artifacts.init;

import artifacts.Artifacts;
import artifacts.components.EntityKillTrackerComponent;
import artifacts.components.SwimAbilityComponent;
import artifacts.components.SyncedBooleanComponent;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import net.minecraft.world.entity.item.ItemEntity;

public class Components implements EntityComponentInitializer {

	public static final ComponentKey<SyncedBooleanComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("dropped_item_entity"), SyncedBooleanComponent.class);
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
}
