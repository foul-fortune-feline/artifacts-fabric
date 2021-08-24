package artifacts.item.curio.feet;

import artifacts.item.curio.TrinketArtifactItem;
import artifacts.trinkets.Slots;
import dev.emi.stepheightentityattribute.StepHeightEntityAttributeMain;
import dev.emi.trinkets.api.SlotGroups;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class RunningShoesItem extends TrinketArtifactItem {

	public static final AttributeModifier SPEED_BOOST_MODIFIER = new AttributeModifier(UUID.fromString("ac7ab816-2b08-46b6-879d-e5dea34ff305"),
			"artifacts:running_shoes_movement_speed", 0.4, AttributeModifier.Operation.MULTIPLY_TOTAL);
	public static final AttributeModifier STEP_HEIGHT_MODIFIER = new AttributeModifier(UUID.fromString("7e97cede-a343-411f-b465-14cdf6df3666"),
			"artifacts:running_shoes_step_height", .5, AttributeModifier.Operation.ADDITION);

	@Override
	@SuppressWarnings("ConstantConditions")
	public void onUnequip(Player player, ItemStack stack) {
		AttributeInstance movementSpeed = player.getAttribute(Attributes.MOVEMENT_SPEED);
		AttributeInstance stepHeight = player.getAttribute(StepHeightEntityAttributeMain.STEP_HEIGHT);

		removeModifier(movementSpeed, SPEED_BOOST_MODIFIER);
		removeModifier(stepHeight, STEP_HEIGHT_MODIFIER);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
