package artifacts.item.curio.head;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slot;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class NightVisionGogglesItem extends TrinketArtifactItem {

    public NightVisionGogglesItem() {
        super(Slot.HAT);
    }

    @Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, true, false);
	}
}
