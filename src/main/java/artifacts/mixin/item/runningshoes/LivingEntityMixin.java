package artifacts.mixin.item.runningshoes;

import artifacts.Artifacts;
import artifacts.common.init.ModItems;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.item.curio.feet.RunningShoesItem;
import artifacts.common.trinkets.TrinketsHelper;
import dev.emi.stepheightentityattribute.StepHeightEntityAttributeMain;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.Attributes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {

	@Inject(method = "setSprinting", at = @At("TAIL"))
	private void onSetSprinting(boolean sprinting, CallbackInfo info) {
		LivingEntity self = (LivingEntity) (Object) this;

		if (!TrinketsHelper.isEquipped(ModItems.RUNNING_SHOES, self)) {
			return;
		}

		AttributeInstance movementSpeed = self.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance stepHeight = self.getAttribute(StepHeightEntityAttributeMain.STEP_HEIGHT);

		if (movementSpeed == null || stepHeight == null) {
			Artifacts.LOGGER.debug("Entity {} missing entity attribute(s)", this);
			return;
		}

		if (sprinting) {
			CurioItem.addModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			CurioItem.addModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		} else {
			CurioItem.removeModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			CurioItem.removeModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		}
	}
}
