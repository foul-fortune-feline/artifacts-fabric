package artifacts.item.curio.feet;

import artifacts.Artifacts;
import artifacts.init.Slot;
import artifacts.item.curio.TrinketArtifactItem;
import com.google.common.collect.Multimap;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;

import java.util.UUID;

public class SteadfastSpikesItem extends TrinketArtifactItem {

    public SteadfastSpikesItem() {
        super(Slot.SHOES);
    }

    @Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = super.applyModifiers(group, slot, uuid, stack);
		AttributeModifier modifier = new AttributeModifier(uuid,
				Artifacts.id("steadfast_spikes_knockback_resistance").toString(),
				1, AttributeModifier.Operation.ADDITION);
		modifiers.put(Attributes.KNOCKBACK_RESISTANCE, modifier);
		return modifiers;
	}
}
