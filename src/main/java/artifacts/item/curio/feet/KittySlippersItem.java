package artifacts.item.curio.feet;

import artifacts.Artifacts;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;

public class KittySlippersItem extends TrinketArtifactItem {

	@Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.CAT_HURT;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.CAT_AMBIENT);
	}
}
