package artifacts.client.render;

import artifacts.Artifacts;
import artifacts.client.render.model.entity.MimicModel;
import artifacts.entity.MimicEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderDispatcher;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MimicRenderer extends MobRenderer<MimicEntity, MimicModel> {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/mimic.png");

	public MimicRenderer(EntityRenderDispatcher manager) {
		super(manager, new MimicModel(), 0.45F);
		addLayer(new MimicChestLayer(this));
	}

	@Override
	public void render(MimicEntity mimic, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource buffer, int packedLight) {
		super.render(mimic, entityYaw, partialTicks, matrixStack, buffer, packedLight);
	}

	@Override
	public ResourceLocation getTextureLocation(MimicEntity entity) {
		return TEXTURE;
	}
}
