package artifacts.item.curio.necklace;

import artifacts.events.LivingEntityAttackedCallback;
import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;

public abstract class PendantItem extends TrinketArtifactItem {

	public PendantItem(LivingEntityAttackedCallback callback) {
		LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}
}
