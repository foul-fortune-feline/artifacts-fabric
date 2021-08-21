package artifacts.common.item.curio.head;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.tags.FluidTags;

public class SnorkelItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this) && !entity.world.isRemote && entity.ticksExisted % 15 == 0) {
            entity.addPotionEffect(new EffectInstance(Effects.WATER_BREATHING, 39, 0, true, false));
        }
        if (entity.ticksExisted % 20 == 0 && entity.areEyesInFluid(FluidTags.WATER)) {
            damageStack(identifier, index, entity, stack);
        }
    }
}
