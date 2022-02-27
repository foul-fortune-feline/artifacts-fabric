package artifacts.common.item.curio.belt;

import artifacts.common.events.LivingEntityHurtCallback;
import artifacts.common.init.ModItems;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ObsidianSkullItem extends CurioItem {

    public ObsidianSkullItem() {
		LivingEntityHurtCallback.EVENT.register(ObsidianSkullItem::onLivingHurt);
    }

	private static void onLivingHurt(LivingEntity wearer, DamageSource source, float amount) {
		if (
				!wearer.level.isClientSide
				&& amount >= 1
				&& source.isFire()
				&& wearer instanceof Player player
				&& TrinketsHelper.isEquipped(ModItems.OBSIDIAN_SKULL, wearer)
				&& !player.getCooldowns().isOnCooldown(ModItems.OBSIDIAN_SKULL)) {
			wearer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0, false, true));
			player.getCooldowns().addCooldown(ModItems.OBSIDIAN_SKULL, 1200);
		}
	}

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
