package artifacts.common.item.curio.belt;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class ObsidianSkullItem extends CurioItem {

    public ObsidianSkullItem() {
        addListener(LivingHurtEvent.class, this::onLivingHurt);
    }

    private void onLivingHurt(LivingHurtEvent event, LivingEntity wearer) {
        if (!wearer.world.isRemote
                && event.getAmount() >= 1
                && (event.getSource() == DamageSource.ON_FIRE || event.getSource() == DamageSource.IN_FIRE || event.getSource() == DamageSource.LAVA || event.getSource() == DamageSource.HOT_FLOOR)
                && wearer instanceof PlayerEntity) {

            if (!((PlayerEntity) wearer).getCooldownTracker().hasCooldown(this)) {
                int cooldown = ModConfig.server.obsidianSkull.cooldown.get();
                int fireResistanceDuration = ModConfig.server.obsidianSkull.fireResistanceDuration.get();

                wearer.addPotionEffect(new EffectInstance(Effects.FIRE_RESISTANCE, fireResistanceDuration, 0, false, true));
                ((PlayerEntity) wearer).getCooldownTracker().setCooldown(this, cooldown);

                damageEquippedStacks(wearer);
            }
        }
    }

    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.ITEM_ARMOR_EQUIP_IRON, 1, 1);
    }
}
