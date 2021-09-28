package artifacts.item.curio.feet;

import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class KittySlippersItem extends TrinketArtifactItem {

    public KittySlippersItem() {
        super(Slot.SHOES);
    }

    @Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.CAT_HURT;
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.CAT_AMBIENT);
	}
}
