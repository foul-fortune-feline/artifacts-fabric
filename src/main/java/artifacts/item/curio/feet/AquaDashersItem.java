package artifacts.item.curio.feet;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;

public class AquaDashersItem extends TrinketArtifactItem {

	public AquaDashersItem() {
		//noinspection UnstableApiUsage
		LivingFluidCollisionCallback.EVENT.register(AquaDashersItem::onFluidCollision);
	}

	private static boolean onFluidCollision(LivingEntity entity, FluidState fluidState) {
		if (entity.isSprinting() && entity.fallDistance < 6 && !entity.isUsingItem() && !entity.isCrouching()) {
			SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(entity);

			if (!swimAbilities.isWet() && !swimAbilities.isSwimming()) {
				if (fluidState.is((FluidTags.LAVA))) {
					if (!entity.fireImmune() && !EnchantmentHelper.hasFrostWalker(entity)) {
						entity.hurt(DamageSource.HOT_FLOOR, 1);
					}
				}
				return true;
			}
		}

		return false;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
