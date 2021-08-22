package artifacts.item.trinket.glove;

import artifacts.client.render.TrinketRenderHelper;
import artifacts.client.render.model.trinket.GloveModel;
import artifacts.item.trinket.TrinketArtifactItem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.HumanoidArm;

public abstract class GloveItem extends TrinketArtifactItem {

	protected Object modelSlim;

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
	}

	@Environment(EnvType.CLIENT)
	protected static boolean hasSmallArms(Entity entity) {
		return entity instanceof AbstractClientPlayer && ((AbstractClientPlayer) entity).getModelName().equals("slim");
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel getModel(boolean smallArms) {
		return smallArms ? getSlimModel() : (GloveModel) getModel();
	}

	@Environment(EnvType.CLIENT)
	protected final GloveModel getSlimModel() {
		if (modelSlim == null) {
			modelSlim = createModel(true);
		}
		return (GloveModel) modelSlim;
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected final GloveModel createModel() {
		return createModel(false);
	}

	@Environment(EnvType.CLIENT)
	protected GloveModel createModel(boolean smallArms) {
		return new GloveModel(smallArms);
	}

	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture(boolean smallArms) {
		return smallArms ? getSlimTexture() : getTexture();
	}

	@Environment(EnvType.CLIENT)
	protected abstract ResourceLocation getSlimTexture();

	@Override
	@Environment(EnvType.CLIENT)
	public void render(String slot, PoseStack matrices, MultiBufferSource vertexConsumers, int light, PlayerModel<AbstractClientPlayer> playerModel, AbstractClientPlayer player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		boolean smallArms = hasSmallArms(player);
		boolean hand = slot.split(":")[0].equals(SlotGroups.HAND);
		GloveModel model = this.getModel(smallArms);

		model.setupAnim(player, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		model.prepareMobModel(player, limbAngle, limbDistance, tickDelta);
		TrinketRenderHelper.followBodyRotations(player, model);
		VertexConsumer vertexBuilder = ItemRenderer.getFoilBuffer(vertexConsumers, model.renderType(getTexture(smallArms)), false, false);
		model.renderHand(hand, matrices, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
	}

	@Environment(EnvType.CLIENT)
	public void renderArm(PoseStack matrices, MultiBufferSource vertexConsumers, int light, AbstractClientPlayer player, HumanoidArm arm, boolean glint) {
		if (!player.isSpectator()) {
			boolean smallArms = hasSmallArms(player);
			GloveModel model = getModel(smallArms);

			ModelPart armPart = arm == HumanoidArm.LEFT ? model.leftArm : model.rightArm;
			ModelPart sleevePart = arm == HumanoidArm.LEFT ? model.leftSleeve : model.rightSleeve;

			model.setAllVisible(false);
			armPart.visible = sleevePart.visible = true;

			model.crouching = false;
			model.attackTime = model.swimAmount = 0;
			model.setupAnim(player, 0, 0, 0, 0, 0);
			armPart.xRot = sleevePart.xRot = 0;

			armPart.render(matrices, ItemRenderer.getFoilBuffer(vertexConsumers, model.renderType(getTexture(smallArms)), false, glint), light, OverlayTexture.NO_OVERLAY);
			sleevePart.render(matrices, ItemRenderer.getFoilBuffer(vertexConsumers, model.renderType(getTexture(smallArms)), false, glint), light, OverlayTexture.NO_OVERLAY);
		}
	}
}
