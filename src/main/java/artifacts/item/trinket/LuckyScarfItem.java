package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ScarfModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class LuckyScarfItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/lucky_scarf.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new ScarfModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}
}
