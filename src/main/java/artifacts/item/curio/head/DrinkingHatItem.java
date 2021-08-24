package artifacts.item.curio.head;

import artifacts.Artifacts;
import artifacts.init.Items;
import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class DrinkingHatItem extends TrinketArtifactItem {

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
		if (Artifacts.CONFIG.general.showTooltips) {
			if (this == Items.NOVELTY_DRINKING_HAT) {
				// Novelty drinking hat description is the same as plastic, but with an extra line appended
				appendTooltipDescription(tooltip, Items.PLASTIC_DRINKING_HAT.getDescriptionId() + ".tooltip");
			}
		}
		super.appendHoverText(stack, world, tooltip, flags);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.BOTTLE_FILL);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
