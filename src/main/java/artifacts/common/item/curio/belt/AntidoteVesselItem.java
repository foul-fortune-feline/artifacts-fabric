package artifacts.common.item.curio.belt;

import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class AntidoteVesselItem extends CurioItem {

    @Override
	protected void curioTick(LivingEntity livingEntity, ItemStack stack) {
		// Reduce duration of all negative status effects to 80
		livingEntity.getActiveEffectsMap().forEach((effect, instance) -> {
			if (!effect.isInstantenous() && effect.getCategory() != MobEffectCategory.BENEFICIAL && instance.getDuration() > 80) {
				instance.artifacts$setDuration(80);
			}
		});
	}

	@Override
	public SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.BOTTLE_FILL);
	}
}
