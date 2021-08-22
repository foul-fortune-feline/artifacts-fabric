package artifacts.client.render;

import artifacts.client.render.model.entity.MimicChestLayerModel;
import artifacts.client.render.model.entity.MimicModel;
import artifacts.entity.MimicEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Vector3f;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.resources.model.Material;
import net.minecraft.world.level.block.state.properties.ChestType;
import java.util.Calendar;

@Environment(EnvType.CLIENT)
public class MimicChestLayer extends RenderLayer<MimicEntity, MimicModel> {

	private final MimicChestLayerModel model;
	private boolean isChristmas;

	public MimicChestLayer(RenderLayerParent<MimicEntity, MimicModel> entityRenderer) {
		super(entityRenderer);

		model = new MimicChestLayerModel();

		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) + 1 == 12 && calendar.get(Calendar.DATE) >= 24 && calendar.get(Calendar.DATE) <= 26) {
			isChristmas = true;
		}
	}

	@Override
	public void render(PoseStack matrices, MultiBufferSource vertexConsumers, int light, MimicEntity mimic, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch) {
		if (!mimic.isInvisible()) {
			matrices.pushPose();

			matrices.mulPose(Vector3f.XP.rotationDegrees(180));
			matrices.translate(-0.5, -1.5, -0.5);

			getParentModel().copyPropertiesTo(model);
			model.prepareMobModel(mimic, limbAngle, limbDistance, tickDelta);
			model.setupAnim(mimic, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
			VertexConsumer builder = getChestMaterial().buffer(vertexConsumers, RenderType::entityCutout);
			model.renderToBuffer(matrices, builder, light, LivingEntityRenderer.getOverlayCoords(mimic, 0), 1, 1, 1, 1);

			matrices.popPose();
		}
	}

	private Material getChestMaterial() {
		return Sheets.chooseMaterial(null, ChestType.SINGLE, isChristmas);
	}
}
