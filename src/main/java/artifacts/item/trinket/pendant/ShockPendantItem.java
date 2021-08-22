package artifacts.item.trinket.pendant;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import java.util.Random;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.Vec3;

public class ShockPendantItem extends PendantItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/shock_pendant.png");

	public ShockPendantItem() {
		super(TEXTURE, ShockPendantItem::applyEffect);
	}

	private static void applyEffect(LivingEntity user, Entity attacker, Random random) {
		if (user != null && attacker != null && TrinketsHelper.isEquipped(Items.SHOCK_PENDANT, user)
				&& attacker.level.canSeeSky(attacker.blockPosition()) && random.nextFloat() < 0.25f) {
			LightningBolt lightning = EntityType.LIGHTNING_BOLT.create(attacker.level);

			if (lightning != null) {
				lightning.moveTo(Vec3.atBottomCenterOf(attacker.blockPosition()));

				if (attacker instanceof ServerPlayer) {
					lightning.setCause((ServerPlayer) attacker);
				}

				attacker.level.addFreshEntity(lightning);
			}
		}
	}
}
