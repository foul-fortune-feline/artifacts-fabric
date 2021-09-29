package artifacts.mixin.mixins.item.luckyscarf;

import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import artifacts.mixin.mixins.accessors.ItemStackAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(EnchantmentHelper.class)
public abstract class EnchantmentHelperMixin {

	@Inject(method = "getItemEnchantmentLevel", at = @At("RETURN"), cancellable = true)
	private static void increaseFortune(Enchantment enchantment, ItemStack stack, CallbackInfoReturnable<Integer> info) {
		//noinspection ConstantConditions
		LivingEntity holder = ((ItemStackAccessor) (Object) stack).getEntityRepresentation() instanceof LivingEntity ? (LivingEntity) ((ItemStackAccessor) (Object) stack).getEntityRepresentation() : null;
		if (enchantment == Enchantments.BLOCK_FORTUNE && TrinketsHelper.isEquipped(Items.LUCKY_SCARF, holder)) {
			info.setReturnValue(info.getReturnValueI() + 1);
		}
	}
}
