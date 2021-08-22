package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class NightVisionGogglesModel extends HumanoidModel<LivingEntity> {

	public NightVisionGogglesModel() {
		super(0.5F, 0, 64, 64);

		setAllVisible(false);
		head.visible = true;
		hat.visible = true;

		ModelPart goggles = new ModelPart(this, 0, 37);
		ModelPart eyeLeft = new ModelPart(this, 0, 32);
		ModelPart eyeRight = new ModelPart(this, 10, 32);

		goggles.addBox(-4, -6, -5 + 0.05F, 8, 4, 1);
		eyeLeft.addBox(1.5F, -5, -8 + 0.05F, 2, 2, 3);
		eyeRight.addBox(-3.5F, -5, -8 + 0.05F, 2, 2, 3);

		head.addChild(goggles);
		head.addChild(eyeLeft);
		head.addChild(eyeRight);
	}
}
