package artifacts.common.item.curio.hands;

import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;

public class DiggingClawsItem extends CurioItem {

	public static final int NEW_BASE_MINING_LEVEL = 1;
	public static final float MINING_SPEED_INCREASE = 3.2f;

    @Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}
}
