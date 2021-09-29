package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import net.minecraft.sounds.SoundEvents;

public class FireGauntletItem extends TrinketArtifactItem {

    @Override
	protected SoundInfo getEquipSoundInfo() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}
}
