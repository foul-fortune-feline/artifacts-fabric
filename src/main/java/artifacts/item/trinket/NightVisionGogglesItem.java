package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.RenderLayer;
import artifacts.client.render.model.trinket.NightVisionGogglesModel;
import artifacts.trinkets.Slots;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.emi.trinkets.api.SlotGroups;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionGogglesItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/night_vision_goggles.png");
	private static final ResourceLocation TEXTURE_GLOW = Artifacts.id("textures/entity/trinket/night_vision_goggles_glow.png");

	@Override
	public MobEffectInstance getPermanentEffect() {
		return new MobEffectInstance(MobEffects.NIGHT_VISION, 20, 0, true, false);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new NightVisionGogglesModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public void render(String slot, PoseStack matrices, MultiBufferSource vertexConsumers, int light, PlayerModel<AbstractClientPlayer> playerModel, AbstractClientPlayer player, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		super.render(slot, matrices, vertexConsumers, light, playerModel, player, limbAngle, limbDistance, tickDelta, animationProgress, headYaw, headPitch);
		VertexConsumer buffer = ItemRenderer.getFoilBuffer(vertexConsumers, RenderLayer.unlit(TEXTURE_GLOW), false, false);
		getModel().renderToBuffer(matrices, buffer, 0xF000F0, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.HEAD) && slot.equals(Slots.HAT);
	}
}
