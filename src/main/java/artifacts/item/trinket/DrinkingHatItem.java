package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.DrinkingHatModel;
import artifacts.init.Items;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import java.util.List;

public class DrinkingHatItem extends TrinketArtifactItem {

	private final ResourceLocation texture;

	public DrinkingHatItem(ResourceLocation texture) {
		this.texture = texture;
	}

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
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new DrinkingHatModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
