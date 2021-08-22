package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.WhoopeeCushionModel;
import artifacts.init.SoundEvents;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

public class WhoopeeCushionItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/whoopee_cushion.png");

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.FART);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new WhoopeeCushionModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		// Wear in any slot
		return true;
	}
}
