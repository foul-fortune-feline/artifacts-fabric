package artifacts.common.item.curio.hands;

import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;

public class PocketPistonItem extends CurioItem {

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.PISTON_EXTEND);
	}
}
