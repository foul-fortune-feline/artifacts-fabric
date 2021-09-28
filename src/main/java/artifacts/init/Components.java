package artifacts.init;

import artifacts.Artifacts;
import artifacts.components.ArtifactEnabledComponent;
import artifacts.components.BooleanComponent;
import artifacts.components.EntityKillTrackerComponent;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.item.ItemComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.item.ItemComponentInitializer;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.entity.ItemEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.concurrent.atomic.AtomicInteger;

public class Components implements EntityComponentInitializer, ItemComponentInitializer {

	public static final ComponentKey<BooleanComponent> DROPPED_ITEM_ENTITY =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "dropped_item_entity"),
					BooleanComponent.class);
	public static final ComponentKey<ArtifactEnabledComponent> ARTIFACT_ENABLED =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "trinket_enabled"), // TODO: can this id be changed?
					ArtifactEnabledComponent.class);
	/*public static final ComponentKey<ArtifactAbilitiesComponent> ARTIFACT_ABILITIES =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "artifact_abilities"),
					ArtifactAbilitiesComponent.class);*/
	public static final ComponentKey<EntityKillTrackerComponent> ENTITY_KILL_TRACKER =
			ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(Artifacts.MODID, "entity_kill_tracker"),
					EntityKillTrackerComponent.class);

	@Override
	public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
		registry.registerFor(ItemEntity.class, DROPPED_ITEM_ENTITY, entity -> new BooleanComponent("wasDropped"));
		// TODO: disabled for now (Helium Flamingo)
		// registry.registerForPlayers(ARTIFACT_ABILITIES, ArtifactAbilitiesComponent::new, RespawnCopyStrategy.LOSSLESS_ONLY);
		registry.registerForPlayers(ENTITY_KILL_TRACKER, entity -> new EntityKillTrackerComponent(), RespawnCopyStrategy.LOSSLESS_ONLY);
	}

	@Override
	public void registerItemComponentFactories(ItemComponentFactoryRegistry registry) {
		// Non-dynamic component registration, might fix this issue: https://github.com/florensie/artifacts-fabric/issues/35
		AtomicInteger registerCount = new AtomicInteger();
		Registry.ITEM.stream().filter(item -> item instanceof TrinketArtifactItem).forEach(item -> {
			registry.registerFor(item, ARTIFACT_ENABLED, ArtifactEnabledComponent::new);
			registerCount.getAndIncrement();
		});
		Artifacts.LOGGER.info("[Artifacts] Registered item components for {} Artifacts", registerCount.get());
	}
}
