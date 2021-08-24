package artifacts.item.curio.head;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class VillagerHatItem extends TrinketArtifactItem {

	@Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.HERO_OF_THE_VILLAGE, 20, 1, true, false);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
