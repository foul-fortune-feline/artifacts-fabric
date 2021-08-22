package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class CloudInABottleModel extends HumanoidModel<LivingEntity> {

	protected final ModelPart cloud;

	public CloudInABottleModel() {
		super(RenderType::entityTranslucent, 0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart jar = new ModelPart(this, 0, 16);
		ModelPart lid = new ModelPart(this, 0, 25);
		cloud = new ModelPart(this).texOffs(8, 25);

		body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

		jar.addBox(-2, 0, -2, 4, 5, 4);
		lid.addBox(-1, -1, -1, 2, 1, 2);
		cloud.addBox(-1, 1.5F, -1, 2, 2, 2);

		jar.setPos(4, 9, -3);
		jar.yRot = -0.5F;

		jar.addChild(lid);
		jar.addChild(cloud);
		body.addChild(jar);

		setAllVisible(false);
		body.visible = true;
	}

	@Override
	public void setupAnim(LivingEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		super.setupAnim(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
		cloud.yRot = (animationProgress) / 50;
		cloud.y = Mth.cos((animationProgress) / 30) / 2;
	}
}
