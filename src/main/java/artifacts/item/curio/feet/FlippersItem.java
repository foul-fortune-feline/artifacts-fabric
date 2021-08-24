package artifacts.item.curio.feet;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;

public class FlippersItem extends TrinketArtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
