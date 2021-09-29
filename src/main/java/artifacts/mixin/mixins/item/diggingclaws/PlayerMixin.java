package artifacts.mixin.mixins.item.diggingclaws;

import artifacts.init.Items;
import artifacts.item.curio.hands.DiggingClawsItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Player.class)
public abstract class PlayerMixin extends LivingEntity {

	@Shadow
	@Final
	public Inventory inventory;

	protected PlayerMixin(EntityType<? extends LivingEntity> entityType, Level world) {
		super(entityType, world);
	}

	/**
	 * Sets the holder on the itemstack to check
	 */
	@Inject(method = "hasCorrectToolForDrops", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;isCorrectToolForDrops(Lnet/minecraft/world/level/block/state/BlockState;)Z"))
	private void setItemStackHolder(BlockState block, CallbackInfoReturnable<Boolean> info) {
		this.inventory.getSelected().setEntityRepresentation(this);
	}

	/**
	 * This is identical to the forge version but might not be ideal
	 * It adds the speed after the vanilla method does all the calculations on the base modifier such as haste and underwater
	 */
	// TODO: identical artifacts-forge behaviour but could do this on the speed mutliplier instead of end result
	@Inject(method = "getDestroySpeed", at = @At("RETURN"), cancellable = true)
	private void increaseMiningSpeed(BlockState block, CallbackInfoReturnable<Float> info) {
		if (TrinketsHelper.isEquipped(Items.DIGGING_CLAWS, this)) {
			info.setReturnValue(info.getReturnValueF() + DiggingClawsItem.MINING_SPEED_INCREASE);
		}
	}
}
