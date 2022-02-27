package artifacts.common.item.curio.necklace;

import artifacts.common.events.LivingEntityAttackedCallback;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;

public abstract class PendantItem extends CurioItem {

	public PendantItem(LivingEntityAttackedCallback callback) {
        LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
