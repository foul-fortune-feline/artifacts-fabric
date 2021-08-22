package artifacts.client.render.model.trinket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class CrossNecklaceModel extends HumanoidModel<LivingEntity> {

	public CrossNecklaceModel() {
		super(0, 0, 64, 64);

		setAllVisible(false);

		body = new ModelPart(this, 0, 0);
		ModelPart cross1 = new ModelPart(this, 52, 0);
		ModelPart cross2 = new ModelPart(this, 56, 0);
		ModelPart cross3 = new ModelPart(this, 60, 0);

		body.addBox(-(2 * 8 + 1) / 2F, -1 / 2F, -(2 * 4 + 1) / 2F, 2 * 8 + 1, 2 * 12 + 1, 2 * 4 + 1);
		cross1.addBox(-0.5F, 4.5F, -5, 1, 4, 1);
		cross2.addBox(-1.5F, 5.5F, -5, 1, 1, 1);
		cross3.addBox(0.5F, 5.5F, -5, 1, 1, 1);

		body.addChild(cross1);
		body.addChild(cross2);
		body.addChild(cross3);
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int light, int overlay, float red, float green, float blue, float alpha) {
		matrixStack.scale(0.5F, 0.5F, 0.5F);
		body.render(matrixStack, buffer, light, overlay, red, green, blue, alpha);
	}
}
