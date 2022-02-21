package artifacts.common.item.curio.necklace;

import artifacts.common.events.LivingEntityAttackedCallback;
import artifacts.common.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public abstract class PendantItem extends TrinketArtifactItem {

	public PendantItem(LivingEntityAttackedCallback callback) {
        LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
