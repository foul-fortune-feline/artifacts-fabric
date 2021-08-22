package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.UniversalAttractorModel;
import artifacts.init.Components;
import artifacts.mixin.mixins.accessors.ItemEntityAccessor;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import java.util.List;

public class UniversalAttractorItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/universal_attractor.png");

	@Override
	// magnet logic from Botania, see https://github.com/Vazkii/Botania
	protected void effectTick(Player player, ItemStack stack) {
		Vec3 playerPos = player.position().add(0, 0.75, 0);

		int range = 5;
		List<ItemEntity> items = player.level.getEntitiesOfClass(ItemEntity.class, new AABB(playerPos.x - range, playerPos.y - range, playerPos.z - range, playerPos.x + range, playerPos.y + range, playerPos.z + range));
		int pulled = 0;
		for (ItemEntity item : items) {
			boolean attractable = Components.DROPPED_ITEM_ENTITY.maybeGet(item).isPresent()
					&& (!Components.DROPPED_ITEM_ENTITY.get(item).get() || ((ItemEntityAccessor) item).getAge() > 100);
			if (attractable && item.isAlive() && !item.hasPickUpDelay()) {
				if (pulled++ > 200) {
					break;
				}

				Vec3 motion = playerPos.subtract(item.position().add(0, item.getBbHeight() / 2, 0));
				if (Math.sqrt(motion.x * motion.x + motion.y * motion.y + motion.z * motion.z) > 1) {
					motion = motion.normalize();
				}
				item.setDeltaMovement(motion.scale(0.6));
			}
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new UniversalAttractorModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}
}
