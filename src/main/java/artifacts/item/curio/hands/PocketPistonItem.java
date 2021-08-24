package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;

public class PocketPistonItem extends TrinketArtifactItem {

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.PISTON_EXTEND);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}
}
