package artifacts.components;

import artifacts.trinkets.TrinketsHelper;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

public class ArtifactEnabledComponent extends SyncedBooleanComponent {

	private final ItemStack stack;

	public ArtifactEnabledComponent(ItemStack stack) {
		super("isEnabled");
		this.stack = stack;
		this.set(true);
	}

	@Override
	public boolean shouldSyncWith(ServerPlayer player) {
		// Only sync if stack is in inventory or trinkets inventory
		return player.inventory.contains(stack)
				|| TrinketsHelper.isEquipped((ItemStack equippedStack) -> equippedStack.equals(stack), player);
	}
}
