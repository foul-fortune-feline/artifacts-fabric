package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.client.render.model.trinket.GoldenHookModel;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;

public class GoldenHookItem extends GloveItem {

	private static final ResourceLocation TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/golden_hook_default.png");
	private static final ResourceLocation TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/golden_hook_slim.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return new GoldenHookModel(smallArms);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getSlimTexture() {
		return TEXTURE_SLIM;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE_DEFAULT;
	}
}
