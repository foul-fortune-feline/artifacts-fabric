package artifacts.item.curio.feet;

import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class KittySlippersItem extends TrinketArtifactItem {

    @Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.CAT_HURT;
	}

	@Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.CAT_AMBIENT);
	}
}
