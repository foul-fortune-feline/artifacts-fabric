package artifacts.common.item.curio.necklace;

import artifacts.common.capability.swimhandler.SwimHandlerCapability;
import artifacts.common.config.ModConfig;
import artifacts.common.item.curio.CurioItem;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import top.theillusivec4.curios.api.SlotContext;

public class CharmOfSinkingItem extends CurioItem {

    public CharmOfSinkingItem() {
        addListener(EventPriority.HIGH, PlayerEvent.BreakSpeed.class, this::onBreakSpeed);
    }

    @Override
    public void curioTick(String identifier, int index, LivingEntity livingEntity, ItemStack stack) {
        if (livingEntity.tickCount % 20 == 0 && livingEntity.isEyeInFluid(FluidTags.WATER)) {
            damageStack(identifier, index, livingEntity, stack);
        }
    }

    public void onBreakSpeed(PlayerEvent.BreakSpeed event, LivingEntity wearer) {
        if (wearer.isEyeInFluid(FluidTags.WATER) && !EnchantmentHelper.hasAquaAffinity(wearer)) {
            event.setNewSpeed(event.getNewSpeed() * 5);
        }
    }

    @Override
    public void onEquip(SlotContext slotContext, ItemStack originalStack, ItemStack newStack) {
        if (!ModConfig.server.isCosmetic(this) && slotContext.getWearer() instanceof ServerPlayer) {
            slotContext.getWearer().getCapability(SwimHandlerCapability.INSTANCE).ifPresent(
                    handler -> {
                        handler.setSinking(true);
                        handler.syncSinking((ServerPlayer) slotContext.getWearer());
                    }
            );
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack originalStack, ItemStack newStack) {
        if (!ModConfig.server.isCosmetic(this) && slotContext.getWearer() instanceof ServerPlayer) {
            slotContext.getWearer().getCapability(SwimHandlerCapability.INSTANCE).ifPresent(
                    handler -> {
                        handler.setSinking(false);
                        handler.syncSinking((ServerPlayer) slotContext.getWearer());
                    }
            );
        }
    }
}
