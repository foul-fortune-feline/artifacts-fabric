package artifacts.mixin.mixins.item.flippers;

import artifacts.init.Items;
import artifacts.item.curio.feet.FlippersItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.entity.LivingEntity;
import artifacts.mixin.extensions.LivingEntityExtensions;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin implements LivingEntityExtensions {

	@ModifyArg(method = "jumpInLiquid", index = 1, at = @At(value = "INVOKE", target = "Lnet/minecraft/world/phys/Vec3;add(DDD)Lnet/minecraft/world/phys/Vec3;"))
	private double increaseSwimUpSpeed(double y) {
		return artifacts$getIncreasedSwimSpeed(y);
	}

	// This is a big method, so I feel more comfortable with a slice than an ordinal
	// big method, big annotation, big fun
	@ModifyArg(method = "travel", index = 0, allow = 1,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;moveRelative(FLnet/minecraft/world/phys/Vec3;)V"),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInWater()Z"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isInLava()Z")
			)
	)
	private float increaseSwimSpeed(float speed) {
		return (float) artifacts$getIncreasedSwimSpeed(speed);
	}

	@Unique
	@Override
	public double artifacts$getIncreasedSwimSpeed(double speed) {
		return TrinketsHelper.isEquipped(Items.FLIPPERS, (LivingEntity) (Object) this)
				? speed * FlippersItem.SWIM_SPEED_MULTIPLIER : speed;
	}
}
