package artifacts.item.trinket.pendant;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.mixin.mixins.accessors.DamageSourceAccessor;
import artifacts.trinkets.TrinketsHelper;
import java.util.Random;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.EntityDamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class FlamePendantItem extends PendantItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/flame_pendant.png");

	public FlamePendantItem() {
		super(TEXTURE, FlamePendantItem::applyEffects);
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
