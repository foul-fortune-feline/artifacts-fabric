package artifacts.common.item.curio.necklace;


import artifacts.common.init.ModComponents;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotReference;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CharmOfSinkingItem extends CurioItem {

    public CharmOfSinkingItem() {
        PlayerSwimCallback.EVENT.register(CharmOfSinkingItem::onPlayerSwim);
    }

    private static TriState onPlayerSwim(Player player) {
        return ModComponents.SWIM_ABILITIES.maybeGet(player)
                .filter(swimAbilityComponent -> swimAbilityComponent.isSinking() && !swimAbilityComponent.isSwimming())
                .map(swimAbilities -> TriState.FALSE)
                .orElse(TriState.DEFAULT);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof ServerPlayer && TrinketsHelper.areEffectsEnabled(stack)) {
            ModComponents.SWIM_ABILITIES.maybeGet(entity).ifPresent(comp -> {
                comp.setSinking(true);
                ModComponents.SWIM_ABILITIES.sync(entity);
            });
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof ServerPlayer) {
            ModComponents.SWIM_ABILITIES.maybeGet(entity).ifPresent(comp -> {
                comp.setSinking(false);
                ModComponents.SWIM_ABILITIES.sync(entity);
            });
        }
    }
}
