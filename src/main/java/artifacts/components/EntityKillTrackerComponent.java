package artifacts.components;

import artifacts.mixin.extensions.DefaultedRegistryExtensions;
import com.google.common.collect.EvictingQueue;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import java.util.Optional;
import java.util.Queue;
import java.util.stream.Collectors;
import net.minecraft.core.Registry;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;

@SuppressWarnings("UnstableApiUsage")
public class EntityKillTrackerComponent implements PlayerComponent<Component> {

	private static final int MAX_SIZE = 25;

	private EvictingQueue<EntityType<?>> lastKilledEntityTypes = EvictingQueue.create(25);

	public void addKilledEntityType(EntityType<?> type) {
		this.lastKilledEntityTypes.add(type);
	}

	public Queue<EntityType<?>> getlastKilledEntityTypes() {
		return this.lastKilledEntityTypes;
	}

	public double getKillRatio(EntityType<?> type) {
		return this.getlastKilledEntityTypes().stream().filter(type::equals).count() / (double) MAX_SIZE;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains("lastKilledEntities")) {
			this.lastKilledEntityTypes = tag.getList("lastKilledEntities", 8).stream()
					.map(entityTypeId -> Registry.ENTITY_TYPE.getOptional(new ResourceLocation(entityTypeId.getAsString())))
					.filter(Optional::isPresent)
					.map(Optional::get)
					.collect(Collectors.toCollection(() -> EvictingQueue.create(MAX_SIZE)));
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		//noinspection unchecked
		tag.put("lastKilledEntities", this.lastKilledEntityTypes.stream()
				.map(((DefaultedRegistryExtensions<EntityType<?>>) Registry.ENTITY_TYPE)::artifacts$getIdOrEmpty)
				//.flatMap(Optional::stream) TODO: Java 9+
				.filter(Optional::isPresent)
				.map(identifier -> StringTag.valueOf(identifier.get().toString()))
				.collect(Collectors.toCollection(ListTag::new)));
	}
}
