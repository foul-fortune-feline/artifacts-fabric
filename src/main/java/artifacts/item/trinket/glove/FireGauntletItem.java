package artifacts.item.trinket.glove;

import artifacts.Artifacts;
import artifacts.client.render.RenderLayer;
import artifacts.client.render.model.trinket.GloveModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.HumanoidArm;

public class FireGauntletItem extends GloveItem {

	private static final ResourceLocation TEXTURE_DEFAULT = Artifacts.id("textures/entity/trinket/fire_gauntlet_default.png");
	private static final ResourceLocation TEXTURE_SLIM = Artifacts.id("textures/entity/trinket/fire_gauntlet_slim.png");
	private static final ResourceLocation TEXTURE_DEFAULT_GLOW = Artifacts.id("textures/entity/trinket/fire_gauntlet_default_glow.png");
	private static final ResourceLocation TEXTURE_SLIM_GLOW = Artifacts.id("textures/entity/trinket/fire_gauntlet_slim_glow.png");

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_IRON);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void render(String slot, PoseStack matrices, MultiBufferSource vertexConsumers, int light, PlayerModel<AbstractClientPlayer> playerModel, AbstractClientPlayer player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		super.render(slot, matrices, vertexConsumers, light, playerModel, player, limbAngle, limbDistance, tickDelta,animationProgress, headYaw, headPitch);

		boolean smallArms = hasSmallArms(player);
		boolean hand = slot.split(":")[0].equals(SlotGroups.HAND);
		GloveModel model = this.getModel(smallArms);

		// The glow effect is achieved by rendering the glow texture unlit
		VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers, RenderLayer.unlit(getGlowTexture(smallArms)), false, false);
		model.renderHand(hand, matrices, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
	}

	@Override
	@Environment(EnvType.CLIENT)
	public void renderArm(PoseStack matrices, MultiBufferSource vertexConsumers, int light, AbstractClientPlayer player, HumanoidArm arm, boolean glint) {
		if (!player.isSpectator()) {
			super.renderArm(matrices, vertexConsumers, light, player, arm, glint);

			boolean smallArms = hasSmallArms(player);
			GloveModel model = getModel(smallArms);

			ModelPart armPart = arm == HumanoidArm.LEFT ? model.leftArm : model.rightArm;
			ModelPart sleevePart = arm == HumanoidArm.LEFT ? model.leftSleeve : model.rightSleeve;

			// Also render the glowy bit
			VertexConsumer vertexConsumer = ItemRenderer.getFoilBuffer(vertexConsumers, RenderLayer.unlit(getGlowTexture(smallArms)), false, false);
			armPart.render(matrices, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
			sleevePart.render(matrices, vertexConsumer, 0xF000F0, OverlayTexture.NO_OVERLAY);
		}
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

	@Environment(EnvType.CLIENT)
	protected ResourceLocation getGlowTexture(boolean smallArms) {
		return smallArms ? TEXTURE_SLIM_GLOW : TEXTURE_DEFAULT_GLOW;
	}
}
