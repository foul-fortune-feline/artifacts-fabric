package artifacts.item.curio.necklace;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;

public class CrossNecklaceItem extends TrinketArtifactItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}

	@Override
	public SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
