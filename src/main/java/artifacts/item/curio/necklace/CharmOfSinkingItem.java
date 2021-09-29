package artifacts.item.curio.necklace;


import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotReference;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CharmOfSinkingItem extends TrinketArtifactItem {

    public CharmOfSinkingItem() {
        PlayerSwimCallback.EVENT.register(CharmOfSinkingItem::onPlayerSwim);
    }

    private static InteractionResult onPlayerSwim(Player player) {
        return Components.SWIM_ABILITIES.maybeGet(player)
                .filter(SwimAbilityComponent::isSinking)
                .map(swimAbilities -> InteractionResult.FAIL)
                .orElse(InteractionResult.PASS);
    }

    @Override
    public void onEquip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof ServerPlayer && TrinketsHelper.areEffectsEnabled(stack)) {
            Components.SWIM_ABILITIES.maybeGet(entity).ifPresent(comp -> {
                comp.setSinking(true);
                Components.SWIM_ABILITIES.sync(entity);
            });
        }
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        if (entity instanceof ServerPlayer) {
            Components.SWIM_ABILITIES.maybeGet(entity).ifPresent(comp -> {
                comp.setSinking(false);
                Components.SWIM_ABILITIES.sync(entity);
            });
        }
    }
}
