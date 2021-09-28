package artifacts.item.curio.necklace;

import artifacts.events.LivingEntityAttackedCallback;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public abstract class PendantItem extends TrinketArtifactItem {

	public PendantItem(LivingEntityAttackedCallback callback) {
        super(Slot.NECKLACE);
        LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
