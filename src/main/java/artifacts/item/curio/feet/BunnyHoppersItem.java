package artifacts.item.curio.feet;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class BunnyHoppersItem extends TrinketArtifactItem {

	@Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.JUMP, 20, 1, true, false);
	}

	@Override
	protected SoundEvent getExtraHurtSound() {
		return SoundEvents.RABBIT_HURT;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
