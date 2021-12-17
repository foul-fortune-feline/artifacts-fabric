package artifacts.components;

import com.google.common.collect.EvictingQueue;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;

import java.util.Queue;

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

	/*
	 * Read and write implementation removed because of https://github.com/florensie/artifacts-fabric/issues/50
	 * Didn't remove entire functionality to make sure we're compatible with clients
	 */

	@Override
	public void readFromNbt(CompoundTag tag) {
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
	}
}
