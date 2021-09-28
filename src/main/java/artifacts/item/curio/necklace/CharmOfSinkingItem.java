package artifacts.item.curio.necklace;


import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class CharmOfSinkingItem extends TrinketArtifactItem {

    public CharmOfSinkingItem() {
        super();
        PlayerSwimCallback.EVENT.register(CharmOfSinkingItem::onPlayerSwim);
    }

    private static InteractionResult onPlayerSwim(Player player) {
        return Components.SWIM_ABILITIES.maybeGet(player)
                .filter(SwimAbilityComponent::isSinking)
                .map(swimAbilities -> InteractionResult.FAIL)
                .orElse(InteractionResult.PASS);
    }

    @Override
    public void onEquip(Player player, ItemStack stack) {
        if (player instanceof ServerPlayer && TrinketsHelper.areEffectsEnabled(stack)) {
            Components.SWIM_ABILITIES.maybeGet(player).ifPresent(comp -> {
                comp.setSinking(true);
                Components.SWIM_ABILITIES.sync(player);
            });
        }
    }

    @Override
    public void onUnequip(Player player, ItemStack stack) {
        if (player instanceof ServerPlayer) {
            Components.SWIM_ABILITIES.maybeGet(player).ifPresent(comp -> {
                comp.setSinking(false);
                Components.SWIM_ABILITIES.sync(player);
            });
        }
    }

    @Override
    public boolean canWearInSlot(String group, String slot) {
        return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
    }
}
