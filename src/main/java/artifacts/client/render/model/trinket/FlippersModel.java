package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class FlippersModel extends HumanoidModel<LivingEntity> {

	public FlippersModel() {
		super(0.5F, 0, 64, 96);

		setAllVisible(false);
		leftLeg.visible = true;
		rightLeg.visible = true;

		ModelPart flipperLeft = new ModelPart(this, 0, 32);
		ModelPart flipperRight = new ModelPart(this, 0, 52);
		flipperLeft.addBox(-2, 11.5F, -16, 9, 1, 20);
		flipperRight.addBox(-7, 11.5F, -16, 9, 1, 20);
		leftLeg.addChild(flipperLeft);
		rightLeg.addChild(flipperRight);
	}
}
