package artifacts.mixin.mixins.item.everlastingfood;

import artifacts.Artifacts;
import artifacts.item.EverlastingFoodItem;
import artifacts.mixin.extensions.AnimalExtensions;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Wolf.class)
public abstract class WolfMixin {

	@Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Wolf;isFood(Lnet/minecraft/world/item/ItemStack;)Z"))
	private boolean cooldownBreedingItem(Wolf wolfEntity, ItemStack stack, Player player) {
		return ((AnimalExtensions) this).artifacts$isBreedingItemWithCooldown(stack, player);
	}

	@Redirect(method = "mobInteract", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;shrink(I)V"),
			slice = @Slice(to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/animal/Wolf;heal(F)V")))
	private void cancelDecrement(ItemStack stack, int amount, Player player) {
		if (stack.getItem() instanceof EverlastingFoodItem) {
			player.getCooldowns().addCooldown(stack.getItem(), Artifacts.CONFIG.general.everlastingFoodCooldown);
		} else {
			stack.shrink(amount);
		}
	}
}
