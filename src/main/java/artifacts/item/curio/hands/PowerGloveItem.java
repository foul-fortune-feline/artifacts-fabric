package artifacts.item.curio.hands;

import artifacts.Artifacts;
import artifacts.item.curio.TrinketArtifactItem;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class PowerGloveItem extends TrinketArtifactItem {

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> result = super.applyModifiers(group, slot, uuid, stack);
		AttributeModifier modifier = new AttributeModifier(uuid,
				Artifacts.id("power_glove_attack_damage").toString(),
				4, AttributeModifier.Operation.ADDITION);
		result.put(Attributes.ATTACK_DAMAGE, modifier);
		return result;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}
}
