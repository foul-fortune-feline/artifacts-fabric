package artifacts.extensions;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public interface AnimalExtensions {

	default boolean artifacts$isBreedingItemWithCooldown(ItemStack stack, Player player) {
		throw new IllegalStateException();
	}
}
