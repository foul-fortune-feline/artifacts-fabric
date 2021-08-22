package artifacts.mixin.mixins.item.aquadashers;

import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public class PlayerMixin {

	@Inject(method = "tick", at = @At("HEAD"))
	private void updateWet(CallbackInfo info) {
		Player self = (Player) (Object) this;
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(self);

		if (self.isInWater()) {
			swimAbilities.setWet(true);
		} else if (self.isOnGround()) {
			swimAbilities.setWet(false);
		}
	}
}
