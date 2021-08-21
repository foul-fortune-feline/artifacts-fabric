package artifacts.common.item.curio;

import artifacts.common.config.ModConfig;
import artifacts.common.init.ModSoundEvents;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.SoundCategory;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class WhoopeeCushionItem extends CurioItem {

    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        if (!entity.world.isRemote()) {
            CompoundNBT tag = stack.getOrCreateTag();
            if (tag.getBoolean("HasFarted") && !entity.isSneaking()) {
                tag.putBoolean("HasFarted", false);
            } else if (!tag.getBoolean("HasFarted") && entity.isSneaking()) {
                tag.putBoolean("HasFarted", true);
                double fartChance = ModConfig.server.whoopeeCushion.flatulence.get();
                if (entity.getRNG().nextFloat() < fartChance) {
                    entity.world.playSound(null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), ModSoundEvents.FART.get(), SoundCategory.PLAYERS, 1, 0.9F + entity.getRNG().nextFloat() * 0.2F);
                    damageStack(identifier, index, entity, stack);
                }
            }
        }
    }

    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(ModSoundEvents.FART.get(), 1, 1);
    }
}
