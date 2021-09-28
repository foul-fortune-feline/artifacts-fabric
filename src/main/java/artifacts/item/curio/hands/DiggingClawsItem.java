package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slot;
import net.minecraft.sounds.SoundEvents;

public class DiggingClawsItem extends TrinketArtifactItem {

	public static final float MINING_SPEED_INCREASE = 3.2f;

    public DiggingClawsItem() {
        super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
    }

    @Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}
}
