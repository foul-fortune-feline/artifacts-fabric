package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;

public class PocketPistonItem extends GloveItem {

	private static final ResourceLocation TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/pocket_piston_default.png");
	private static final ResourceLocation TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/pocket_piston_slim.png");

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

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.PISTON_EXTEND);
	}
}
