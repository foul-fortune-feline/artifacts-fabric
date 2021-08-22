package artifacts.mixin.mixins.item.heliumflamingo;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.Items;
import artifacts.init.SoundEvents;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class PlayerMixin {
	
	@Inject(method = "tick", at = @At("TAIL"))
	private void stopAirSwimming(CallbackInfo info) {
		Player self = (Player) (Object) this;
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (swimAbilities.isSwimming()) {
			if (!TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self) || self.getAirSupply() <= 0
					|| self.isInWater() && !self.isSwimming() && !swimAbilities.isSinking()
					|| (!self.isInWater() || swimAbilities.isSinking()) && self.isOnGround()) {
				swimAbilities.setSwimming(false);
				if (!self.isOnGround() && !self.isInWater()) {
					self.playSound(SoundEvents.POP, 0.5F, 0.75F);
				}
			}

			if (TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, self) && !self.isEyeInFluid(FluidTags.WATER)) {
				// TODO: durability
				/*if (self.age % 20 == 0) {
					damageEquippedStacks(self);
				}*/

				// TODO: config
				if (!self.abilities.invulnerable /*&& ModConfig.server.heliumFlamingo.airSupplyDrainRate.get() > 0*/) {
					// compensate for bonus air
					int airSupply = self.getAirSupply() - 4;
					self.setAirSupply(airSupply - 2 /*ModConfig.server.heliumFlamingo.airSupplyDrainRate.get()*/);
				}
			}
		}
	}
}
