package artifacts.item.curio.head;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class SnorkelItem extends TrinketArtifactItem {

	@Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.WATER_BREATHING, 20, 0, true, false);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
