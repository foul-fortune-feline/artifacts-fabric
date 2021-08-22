package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class CrystalHeartModel extends HumanoidModel<LivingEntity> {

	public CrystalHeartModel() {
		super(RenderType::entityTranslucent, 0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart heart1 = new ModelPart(this, 0, 16);
		ModelPart heart2 = new ModelPart(this, 6, 16);
		ModelPart heart3 = new ModelPart(this, 0, 20);
		ModelPart heart4 = new ModelPart(this, 4, 20);
		ModelPart heart5 = new ModelPart(this, 8, 20);

		body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

		heart1.addBox(-2.5F, 0, 0, 2, 3, 1);
		heart2.addBox(0.5F, 0, 0, 2, 3, 1);
		heart3.addBox(-0.5F, 1, 0, 1, 4, 1);
		heart4.addBox(-1.5F, 3, 0, 1, 1, 1);
		heart5.addBox(0.5F, 3, 0, 1, 1, 1);

		heart1.setPos(2.5F, 9, -3);

		heart1.addChild(heart2);
		heart1.addChild(heart3);
		heart1.addChild(heart4);
		heart1.addChild(heart5);
		body.addChild(heart1);

		setAllVisible(false);
		body.visible = true;
	}
}
