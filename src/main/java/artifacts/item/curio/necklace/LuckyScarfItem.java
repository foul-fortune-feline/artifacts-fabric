package artifacts.item.curio.necklace;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;

public class LuckyScarfItem extends TrinketArtifactItem {

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}
}
