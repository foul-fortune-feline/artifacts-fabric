package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.AquaDashersModel;
import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.trinkets.Slots;
import be.florens.expandability.api.fabric.LivingFluidCollisionCallback;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.material.FluidState;

public class AquaDashersItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/aqua_dashers.png");

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
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new AquaDashersModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
