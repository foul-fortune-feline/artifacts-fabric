package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LocalPlayer.class)
public abstract class LocalPlayerMixin {

	@Shadow @Final protected Minecraft minecraft;
	@Unique private boolean wasSprintKeyDown;
	@Unique private boolean wasSprintingOnGround;
	@Unique private boolean hasTouchedGround;

	@Inject(method = "aiStep", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/player/Input;tick(Z)V", shift = At.Shift.AFTER))
	private void handleAirSwimmingInput(CallbackInfo info) {
		LocalPlayer self = (LocalPlayer) (Object) this;
		boolean isSprintKeyDown = this.minecraft.options.keySprint.isDown();
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (!swimAbilities.isSwimming()) {
			if (self.isOnGround()) {
				hasTouchedGround = true;
			} else if (!swimAbilities.isSwimming()
					&& self.getAirSupply() > 0
					&& TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self)
					&& (self.isSwimming()
					|| isSprintKeyDown
					&& !wasSprintKeyDown
					&& !wasSprintingOnGround
					&& hasTouchedGround
					&& !self.isOnGround()
					&& (!self.isInWater() || swimAbilities.isSinking())
					&& !self.isFallFlying()
					&& !self.abilities.flying
					&& !self.isPassenger())) {
				swimAbilities.setSwimming(true);
				swimAbilities.syncSwimming();
				hasTouchedGround = false;
			}
		} else if (self.abilities.flying) {
			swimAbilities.setSwimming(false);
			swimAbilities.syncSwimming();
			hasTouchedGround = true;
		}

		wasSprintKeyDown = isSprintKeyDown;
		if (!isSprintKeyDown) {
			wasSprintingOnGround = false;
		} else if (self.isOnGround()) {
			wasSprintingOnGround = true;
		}
	}
}
