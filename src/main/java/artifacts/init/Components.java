package artifacts.init;

import artifacts.Artifacts;
import artifacts.components.ArtifactEnabledComponent;
import artifacts.components.EntityKillTrackerComponent;
import artifacts.components.SwimAbilityComponent;
import artifacts.components.SyncedBooleanComponent;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.world.entity.item.ItemEntity;

public class Components implements EntityComponentInitializer, ItemComponentInitializer {

	public static final ComponentKey<SyncedBooleanComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("dropped_item_entity"), SyncedBooleanComponent.class);
	public static final ComponentKey<ArtifactEnabledComponent> ARTIFACT_ENABLED = // TODO: can this id be changed?
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("trinket_enabled"), ArtifactEnabledComponent.class);
	public static final ComponentKey<SwimAbilityComponent> SWIM_ABILITIES =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("swim_abilities"), SwimAbilityComponent.class);
	public static final ComponentKey<EntityKillTrackerComponent> ENTITY_KILL_TRACKER =
			ComponentRegistryV3.INSTANCE.getOrCreate(Artifacts.id("entity_kill_tracker"), EntityKillTrackerComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new SyncedBooleanComponent("wasDropped"));
		registry.registerForPlayers(SWIM_ABILITIES, entity -> new SwimAbilityComponent(), RespawnCopyStrategy.LOSSLESS_ONLY);
		registry.registerForPlayers(ENTITY_KILL_TRACKER, entity -> new EntityKillTrackerComponent(), RespawnCopyStrategy.LOSSLESS_ONLY);
	}

	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		registry.registerFor(item -> item instanceof TrinketArtifactItem, ARTIFACT_ENABLED, ArtifactEnabledComponent::new);
	}
}
