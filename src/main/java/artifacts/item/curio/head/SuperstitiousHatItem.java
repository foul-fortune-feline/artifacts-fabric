package artifacts.item.curio.head;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;

public class SuperstitiousHatItem extends TrinketArtifactItem {

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
