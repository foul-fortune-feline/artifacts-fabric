package artifacts.common.item.curio.head;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class NightVisionGogglesItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this) && !livingEntity.world.isRemote && livingEntity.ticksExisted % 15 == 0) {
            livingEntity.addPotionEffect(new EffectInstance(Effects.NIGHT_VISION, 319, 0, true, false));
        }
        if (livingEntity.ticksExisted % 20 == 0) {
            damageStack(identifier, index, livingEntity, stack);
        }
    }
}
