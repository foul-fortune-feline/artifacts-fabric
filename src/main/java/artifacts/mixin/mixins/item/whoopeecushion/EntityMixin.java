package artifacts.mixin.mixins.item.whoopeecushion;

import artifacts.init.Items;
import artifacts.init.SoundEvents;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "setShiftKeyDown", at = @At("RETURN"))
	private void playFartSound(boolean sneaking, CallbackInfo info) {
		Entity entity = (Entity) (Object) this;

		//noinspection ConstantConditions
		if (sneaking && !entity.level.isClientSide() && entity instanceof LivingEntity
				&& TrinketsHelper.isEquipped(Items.WHOOPEE_CUSHION, (LivingEntity) entity)
				&& ((LivingEntity) entity).getRandom().nextInt(8) == 0) {
			entity.level.playSound(null, entity.getX(), entity.getY(), entity.getZ(),
					SoundEvents.FART, SoundSource.PLAYERS, 1,
					0.9F + ((LivingEntity) entity).getRandom().nextFloat() * 0.2F);
		}
	}
}
