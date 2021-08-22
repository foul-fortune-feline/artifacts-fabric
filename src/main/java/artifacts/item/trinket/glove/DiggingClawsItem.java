package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.ClawsModel;
import artifacts.client.render.model.trinket.GloveModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class DiggingClawsItem extends GloveItem {

	public static final float MINING_SPEED_INCREASE = 3.2f;

	private static final ResourceLocation TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/digging_claws_default.png");
	private static final ResourceLocation TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/digging_claws_slim.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return new ClawsModel(smallArms);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE_DEFAULT;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_NETHERITE);
	}
}
