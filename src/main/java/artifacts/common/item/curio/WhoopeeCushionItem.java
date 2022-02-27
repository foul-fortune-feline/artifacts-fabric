package artifacts.common.item.curio;

import artifacts.common.init.ModSoundEvents;

public class WhoopeeCushionItem extends CurioItem {

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(ModSoundEvents.FART);
	}
}
