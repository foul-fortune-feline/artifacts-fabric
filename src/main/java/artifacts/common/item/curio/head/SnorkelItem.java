package artifacts.common.item.curio.head;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;

public class SnorkelItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this) && !entity.level.isClientSide && entity.tickCount % 15 == 0) {
            entity.addEffect(new MobEffectInstance(MobEffects.WATER_BREATHING, 39, 0, true, false));
        }
        if (entity.tickCount % 20 == 0 && entity.isEyeInFluid(FluidTags.WATER)) {
            damageStack(identifier, index, entity, stack);
        }
    }
}
