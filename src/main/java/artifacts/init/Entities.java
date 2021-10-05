package artifacts.init;

import artifacts.Artifacts;
import artifacts.entity.MimicEntity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class Entities {

	public static final EntityType<MimicEntity> MIMIC = Registry.register( Registry.ENTITY_TYPE, Artifacts.id("mimic"),
			FabricEntityTypeBuilder.create(MobCategory.MISC, MimicEntity::new)
					.dimensions(EntityDimensions.fixed(14 / 16F, 14 / 16F))
					.trackRangeBlocks(64)
					.build());

	static {
		// Register mob attributes
		FabricDefaultAttributeRegistry.register(MIMIC, MimicEntity.createMobAttributes());
	}

	private Entities() {
	}
}