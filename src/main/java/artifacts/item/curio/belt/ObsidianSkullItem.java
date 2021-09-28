package artifacts.item.curio.belt;

import artifacts.events.LivingEntityHurtCallback;
import artifacts.init.Items;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;

public class ObsidianSkullItem extends TrinketArtifactItem {

    public ObsidianSkullItem() {
        super(Slot.BELT);
		LivingEntityHurtCallback.EVENT.register(ObsidianSkullItem::onLivingHurt);
    }

	private static void onLivingHurt(LivingEntity user, DamageSource source, float amount) {
		if (!user.level.isClientSide && amount >= 1 && source.isFire() && user instanceof Player
				&& TrinketsHelper.isEquipped(Items.OBSIDIAN_SKULL, user) && !((Player) user).getCooldowns().isOnCooldown(Items.OBSIDIAN_SKULL)) {
			user.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0, false, true));
			((Player) user).getCooldowns().addCooldown(Items.OBSIDIAN_SKULL, 1200);
		}
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
