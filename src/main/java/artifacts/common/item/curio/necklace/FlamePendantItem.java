package artifacts.common.item.curio.necklace;

import artifacts.common.init.ModItems;
import artifacts.common.trinkets.TrinketsHelper;
import artifacts.mixin.accessors.DamageSourceAccessor;
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
		if (user != null && attacker != null && TrinketsHelper.isEquipped(ModItems.FLAME_PENDANT, user) && random.nextFloat() < 0.4f) {
			attacker.setSecondsOnFire(10);
			DamageSource setFireSource = ((DamageSourceAccessor) new EntityDamageSource("onFire", user)).callSetIsFire();
			attacker.hurt(setFireSource, 2);
		}
	}
}
