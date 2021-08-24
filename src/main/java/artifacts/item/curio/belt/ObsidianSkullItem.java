package artifacts.item.curio.belt;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;

public class ObsidianSkullItem extends TrinketArtifactItem {

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
