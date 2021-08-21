package artifacts.common.item.curio.necklace;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;

public class ScarfOfInvisibilityItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this) && !entity.world.isRemote && entity.ticksExisted % 15 == 0) {
            entity.addPotionEffect(new EffectInstance(Effects.INVISIBILITY, 39, 0, true, false));
        }
        if (entity.ticksExisted % 20 == 0) {
            damageStack(identifier, index, entity, stack);
        }
    }
}
