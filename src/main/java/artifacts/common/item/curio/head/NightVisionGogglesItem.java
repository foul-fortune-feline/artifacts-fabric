package artifacts.common.item.curio.head;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class NightVisionGogglesItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this) && !livingEntity.level.isClientSide && livingEntity.tickCount % 15 == 0) {
            livingEntity.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 319, 0, true, false));
        }
        if (livingEntity.tickCount % 20 == 0) {
            damageStack(identifier, index, livingEntity, stack);
        }
    }
}
