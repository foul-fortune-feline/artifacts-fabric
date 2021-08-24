package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;

public class DiggingClawsItem extends TrinketArtifactItem {

	public static final float MINING_SPEED_INCREASE = 3.2f;

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}
}
