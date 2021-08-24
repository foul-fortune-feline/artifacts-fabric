package artifacts.mixin.mixins.item.runningshoes;

import artifacts.init.Items;
import artifacts.item.curio.feet.RunningShoesItem;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
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

		if (!TrinketsHelper.isEquipped(Items.RUNNING_SHOES, self)) {
			return;
		}

		AttributeInstance movementSpeed = self.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance stepHeight = self.getAttribute(StepHeightEntityAttributeMain.STEP_HEIGHT);

		if (sprinting) {
			TrinketArtifactItem.addModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			TrinketArtifactItem.addModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		} else {
			TrinketArtifactItem.removeModifier(movementSpeed, RunningShoesItem.SPEED_BOOST_MODIFIER);
			TrinketArtifactItem.removeModifier(stepHeight, RunningShoesItem.STEP_HEIGHT_MODIFIER);
		}
	}
}
