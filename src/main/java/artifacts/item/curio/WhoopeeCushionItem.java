package artifacts.item.curio;

import artifacts.init.SoundEvents;

public class WhoopeeCushionItem extends TrinketArtifactItem {

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.FART);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		// Wear in any slot
		return true;
	}
}
