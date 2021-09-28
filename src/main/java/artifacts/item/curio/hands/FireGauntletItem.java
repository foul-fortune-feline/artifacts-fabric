package artifacts.item.curio.hands;

import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class FireGauntletItem extends TrinketArtifactItem {

    public FireGauntletItem() {
        super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
    }

    @Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
