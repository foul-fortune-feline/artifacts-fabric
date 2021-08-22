package artifacts.client.render.model.trinket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import java.util.function.Function;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class ScarfModel extends HumanoidModel<LivingEntity> {

	private final ModelPart bipedCape;

	public ScarfModel() {
		this(RenderType::entityCutoutNoCull);
	}

	public ScarfModel(Function<ResourceLocation, RenderType> renderType) {
		super(renderType, 0.5F, 0, 64, 32);
		bipedCape = new ModelPart(this, 32, 0);
		bipedCape.addBox(-5, 0, 0, 5, 12, 2);
		body = new ModelPart(this, 0, 16);
		body.addBox(-6.01F, -2, -4, 12, 6, 8);
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		bipedCape.copyFrom(body);
		bipedCape.z += 1.99F;
	}

	@Override
	public void prepareMobModel(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks) {
		if (entity instanceof AbstractClientPlayer) {
			AbstractClientPlayer player = (AbstractClientPlayer) entity;
			double x = Mth.lerp(partialTicks, player.xCloakO, player.xCloak) - Mth.lerp(partialTicks, player.xo, player.getX());
			double y = Mth.lerp(partialTicks, player.yCloakO, player.yCloak) - Mth.lerp(partialTicks, player.yo, player.getY());
			double z = Mth.lerp(partialTicks, player.zCloakO, player.zCloak) - Mth.lerp(partialTicks, player.zo, player.getZ());
			float f = player.yBodyRotO + (player.yBodyRot - player.yBodyRotO);
			double d3 = Mth.sin(f * ((float) Math.PI / 180));
			double d4 = -Mth.cos(f * ((float) Math.PI / 180));
			float f1 = (float) y * 10;
			f1 = Mth.clamp(f1, -6, 32);
			float f2 = (float) (x * d3 + z * d4) * 100;
			f2 = Mth.clamp(f2, 0, 150);
			if (f2 < 0) {
				f2 = 0;
			}

			float f4 = Mth.lerp(partialTicks, player.oBob, player.bob);
			f1 = f1 + Mth.sin(Mth.lerp(partialTicks, player.walkDistO, player.walkDist) * 6) * 32 * f4;

			bipedCape.xRot += (6 + f2 / 2 + f1) / 180 * (float) Math.PI;
		}
	}

	@Override
	public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		bipedCape.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		body.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
		head.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
	}
}
