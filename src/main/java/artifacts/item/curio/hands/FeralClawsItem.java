package artifacts.item.curio.hands;

import artifacts.Artifacts;
import artifacts.item.curio.TrinketArtifactItem;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class FeralClawsItem extends TrinketArtifactItem {

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> result = super.applyModifiers(group, slot, uuid, stack);
		AttributeModifier modifier = new AttributeModifier(uuid,
				Artifacts.id("feral_claws_attack_speed").toString(),
				1, AttributeModifier.Operation.MULTIPLY_TOTAL);
		result.put(Attributes.ATTACK_SPEED, modifier);
		return result;
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}
}
