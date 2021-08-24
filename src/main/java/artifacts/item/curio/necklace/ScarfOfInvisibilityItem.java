package artifacts.item.curio.necklace;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class ScarfOfInvisibilityItem extends TrinketArtifactItem {

	@Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.INVISIBILITY, 20, 0, true, false);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}
}
