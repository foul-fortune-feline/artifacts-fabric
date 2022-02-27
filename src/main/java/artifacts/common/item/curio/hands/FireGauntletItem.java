package artifacts.common.item.curio.hands;

import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;

public class FireGauntletItem extends CurioItem {

    @Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
