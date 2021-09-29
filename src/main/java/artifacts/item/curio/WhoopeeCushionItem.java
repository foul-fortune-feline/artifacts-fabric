package artifacts.item.curio;

import artifacts.init.SoundEvents;

public class WhoopeeCushionItem extends TrinketArtifactItem {

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.FART);
	}
}
