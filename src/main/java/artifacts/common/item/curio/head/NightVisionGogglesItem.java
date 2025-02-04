package artifacts.common.item.curio.head;

import artifacts.common.item.curio.CurioItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;

public class NightVisionGogglesItem extends CurioItem {

    @Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, true, false);
	}
}
