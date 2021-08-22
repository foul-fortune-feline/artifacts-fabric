package artifacts.item.trinket.pendant;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import java.util.Random;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class ThornPendantItem extends PendantItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/thorn_pendant.png");

	public ThornPendantItem() {
		super(TEXTURE, ThornPendantItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, Entity attacker, Random random) {
		if (user != null && attacker != null && TrinketsHelper.isEquipped(Items.THORN_PENDANT, user) && random.nextFloat() < 0.5f) {
			attacker.hurt(DamageSource.thorns(user), 2 + random.nextInt(5));
		}
	}
}
