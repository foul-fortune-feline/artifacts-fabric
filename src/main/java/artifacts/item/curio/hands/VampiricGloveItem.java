package artifacts.item.curio.hands;

import artifacts.events.LivingEntityDamagedCallback;
import artifacts.init.Items;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class VampiricGloveItem extends TrinketArtifactItem {

	public VampiricGloveItem() {
        super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
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
}
