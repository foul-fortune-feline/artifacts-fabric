package artifacts.item.curio.belt;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slot;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class CrystalHeartItem extends TrinketArtifactItem {

	private static final AttributeModifier HEALTH_BONUS = new AttributeModifier(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"),
			"artifacts:crystal_heart_health_bonus", 10, AttributeModifier.Operation.ADDITION);

    public CrystalHeartItem() {
        super(Slot.BELT);
    }

    @Override
	public void onEquip(Player player, ItemStack stack) {
		if (!player.level.isClientSide() && TrinketsHelper.areEffectsEnabled(stack)) {
			AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
			if (health != null && !health.hasModifier(HEALTH_BONUS)) {
				health.addPermanentModifier(HEALTH_BONUS);
			}
		}

		super.onEquip(player, stack);
	}

	@Override
	public void onUnequip(Player player, ItemStack stack) {
		if (!player.level.isClientSide()) {
			AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
			if (health != null && health.hasModifier(HEALTH_BONUS)) {
				health.removeModifier(HEALTH_BONUS);
				if (player.getHealth() > player.getMaxHealth()) {
					player.setHealth(player.getMaxHealth());
				}
			}
		}
	}

	@Override
	public SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
