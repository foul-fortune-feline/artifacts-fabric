package artifacts.item.curio.necklace;

import artifacts.init.Items;
import artifacts.mixin.mixins.accessors.DamageSourceAccessor;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

import java.util.Random;

public class FlamePendantItem extends PendantItem {

	public FlamePendantItem() {
		super(FlamePendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (user != null && attacker != null && TrinketsHelper.isEquipped(Items.FLAME_PENDANT, user) && random.nextFloat() < 0.4f) {
			attacker.setSecondsOnFire(8);
			//noinspection ConstantConditions
			DamageSource setFireSource = ((DamageSourceAccessor) (new EntityDamageSource("onFire", user))).artifacts$callSetIsFire();
			attacker.hurt(setFireSource, 2);
		}
	}
}
