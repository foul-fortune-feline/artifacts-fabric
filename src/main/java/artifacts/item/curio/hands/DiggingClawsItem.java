package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class DiggingClawsItem extends TrinketArtifactItem {

	public static final float MINING_SPEED_INCREASE = 3.2f;

    @Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}
}
