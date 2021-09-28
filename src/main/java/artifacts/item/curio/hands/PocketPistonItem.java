package artifacts.item.curio.hands;

import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class PocketPistonItem extends TrinketArtifactItem {

	public PocketPistonItem() {
		super(Slot.GLOVE_MAINHAND, Slot.GLOVE_OFFHAND);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.PISTON_EXTEND);
	}
}
