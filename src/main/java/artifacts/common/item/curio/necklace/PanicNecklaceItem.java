package artifacts.common.item.curio.necklace;

import artifacts.common.events.LivingEntityHurtCallback;
import artifacts.common.init.ModItems;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PanicNecklaceItem extends CurioItem {

	public PanicNecklaceItem() {
        LivingEntityHurtCallback.EVENT.register(PanicNecklaceItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, DamageSource source, float amount) {
		if (!user.level.isClientSide && amount >= 1 && TrinketsHelper.isEquipped(ModItems.PANIC_NECKLACE, user)) {
			user.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 160, 0, false, false));
		}
	}

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
