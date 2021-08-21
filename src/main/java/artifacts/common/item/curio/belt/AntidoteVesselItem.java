package artifacts.common.item.curio.belt;

import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AntidoteVesselItem extends CurioItem {

    @Override
    public ICurio.SoundInfo getEquipSound(SlotContext slotContext, ItemStack stack) {
        return new ICurio.SoundInfo(SoundEvents.BOTTLE_FILL, 1, 1);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity entity, ItemStack stack) {
        if (!ModConfig.server.isCosmetic(this)) {
            Map<MobEffect, MobEffectInstance> effects = new HashMap<>();

            int maxEffectDuration = ModConfig.server.antidoteVessel.maxEffectDuration.get();

            entity.getActiveEffectsMap().forEach((effect, instance) -> {
                Set<MobEffect> negativeEffects = ModConfig.server.antidoteVessel.negativeEffects;
                if (negativeEffects.contains(effect) && instance.getDuration() > maxEffectDuration) {
                    effects.put(effect, instance);
                }
            });

            effects.forEach((effect, instance) -> {
                damageStack(identifier, index, entity, stack);
                entity.removeEffectNoUpdate(effect);
                if (maxEffectDuration > 0) {
                    entity.addEffect(new MobEffectInstance(effect, maxEffectDuration, instance.getAmplifier(), instance.isAmbient(), instance.isVisible(), instance.showIcon()));
                }
            });
        }
    }
}
