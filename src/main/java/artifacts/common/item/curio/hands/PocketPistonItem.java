package artifacts.common.item.curio.hands;

import artifacts.common.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class PocketPistonItem extends TrinketArtifactItem {

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.PISTON_EXTEND);
	}
}
