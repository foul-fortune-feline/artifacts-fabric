package artifacts.mixin.mixins.item.firegauntlet;

import artifacts.common.init.ModItems;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	/**
	 * Give the player fire aspect II (equals 8 seconds in Forge version)
	 */
	@Inject(method = "getFireAspect", at = @At("RETURN"), cancellable = true)
	private static void giveFireAspect(LivingEntity entity, CallbackInfoReturnable<Integer> info) {
		if (info.getReturnValueI() < 2 && TrinketsHelper.isEquipped(ModItems.FIRE_GAUNTLET, entity)) {
			info.setReturnValue(2);
		}
	}
}
