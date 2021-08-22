package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.FlippersModel;
import artifacts.trinkets.Slots;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class FlippersItem extends TrinketArtifactItem {

	public static final int SWIM_SPEED_MULTIPLIER = 2;
	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/flippers.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new FlippersModel();
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
