package artifacts.item.curio.belt;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slot;
import net.minecraft.sounds.SoundEvents;

public class ObsidianSkullItem extends TrinketArtifactItem {

    public ObsidianSkullItem() {
        super(Slot.BELT);
    }

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
