package artifacts.mixin.item.charmofsinking;

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

    @Inject(method = "hasAquaAffinity", at = @At("HEAD"), cancellable = true)
    private static void dontSlowMiningUnderwater(LivingEntity livingEntity, CallbackInfoReturnable<Boolean> info) {
        if (TrinketsHelper.isEquipped(ModItems.CHARM_OF_SINKING, livingEntity)) {
            info.setReturnValue(true);
        }
    }
}
