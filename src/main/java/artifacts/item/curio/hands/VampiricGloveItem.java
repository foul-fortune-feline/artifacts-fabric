package artifacts.item.curio.hands;

import artifacts.events.LivingEntityDamagedCallback;
import artifacts.init.Items;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;

public class VampiricGloveItem extends TrinketArtifactItem {

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
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}
}
