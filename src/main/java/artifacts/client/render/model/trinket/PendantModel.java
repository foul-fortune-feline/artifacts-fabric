package artifacts.client.render.model.trinket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class PendantModel extends HumanoidModel<LivingEntity> {

	public PendantModel() {
		super(RenderType::entityTranslucent, 0, 0, 64, 64);

		setAllVisible(false);

		body = new ModelPart(this, 0, 0);
		ModelPart gem = new ModelPart(this, 50, 0);

		body.addBox(-(2 * 8) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8, 2 * 12 + 1, 2 * 4 + 1);
		gem.addBox(-1, 4.5F, -5, 2, 2, 1);

		body.addChild(gem);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
