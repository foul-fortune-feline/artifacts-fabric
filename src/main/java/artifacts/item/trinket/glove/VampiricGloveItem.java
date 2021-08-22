package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.events.LivingEntityDamagedCallback;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class VampiricGloveItem extends GloveItem {

	private static final ResourceLocation TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/vampiric_glove_default.png");
	private static final ResourceLocation TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/vampiric_glove_slim.png");

	public VampiricGloveItem() {
		LivingEntityDamagedCallback.EVENT.register(VampiricGloveItem::onLivingDamage);
	}

	private static void onLivingDamage(LivingEntity entity, DamageSource source, float amount) {
		if (source.getEntity() instanceof LivingEntity) {
			Entity src = source.getDirectEntity();
			LivingEntity attacker = (LivingEntity) source.getEntity();
			float damageDealt = Math.min(amount, entity.getHealth());
			if (src == attacker && damageDealt > 4 && TrinketsHelper.isEquipped(Items.VAMPIRIC_GLOVE, attacker)) {
				attacker.heal(Math.min(2, amount / 4));
			}
		}
	}

	@Override
	protected ResourceLocation getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	protected ResourceLocation getSlimTexture() {
		return TEXTURE_SLIM;
	}
}
