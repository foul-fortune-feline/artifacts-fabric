package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.SteadfastSpikesModel;
import artifacts.trinkets.Slots;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import java.util.UUID;

public class SteadfastSpikesItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/steadfast_spikes.png");

	@Override
	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		Multimap<Attribute, AttributeModifier> modifiers = super.applyModifiers(group, slot, uuid, stack);
		AttributeModifier modifier = new AttributeModifier(uuid,
				Artifacts.id("steadfast_spikes_knockback_resistance").toString(),
				1, AttributeModifier.Operation.ADDITION);
		modifiers.put(Attributes.KNOCKBACK_RESISTANCE, modifier);
		return modifiers;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new SteadfastSpikesModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.FEET) && slot.equals(Slots.SHOES);
	}
}
