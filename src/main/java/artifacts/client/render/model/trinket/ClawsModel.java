package artifacts.client.render.model.trinket;

import net.minecraft.client.model.geom.ModelPart;

public class ClawsModel extends GloveModel {

	public ClawsModel(boolean smallArms) {
		super(smallArms);

		int smallArmsOffset = smallArms ? 1 : 0;

		ModelPart clawLeftUpper1 = new ModelPart(this, 0, 6);
		ModelPart clawRightUpper1 = new ModelPart(this, 0, 38);
		ModelPart clawLeftUpper2 = new ModelPart(this, 8, 6);
		ModelPart clawRightUpper2 = new ModelPart(this, 8, 38);
		ModelPart clawLeftLower1 = new ModelPart(this, 0, 0);
		ModelPart clawRightLower1 = new ModelPart(this, 0, 32);
		ModelPart clawLeftLower2 = new ModelPart(this, 8, 0);
		ModelPart clawRightLower2 = new ModelPart(this, 8, 32);
		clawLeftUpper1.addBox(3 - smallArmsOffset, 10, -1.5F, 1, 4, 1);
		clawRightUpper1.addBox(-4 + smallArmsOffset, 10, -1.5F, 1, 4, 1);
		clawLeftUpper2.addBox(3 - smallArmsOffset, 10, 0.5F, 1, 4, 1);
		clawRightUpper2.addBox(-4 + smallArmsOffset, 10, 0.5F, 1, 4, 1);
		clawLeftLower1.addBox(-smallArmsOffset, 10, -1.5F, 3, 5, 1);
		clawRightLower1.addBox(-3 + smallArmsOffset, 10, -1.5F, 3, 5, 1);
		clawLeftLower2.addBox(-smallArmsOffset, 10, 0.5F, 3, 5, 1);
		clawRightLower2.addBox(-3 + smallArmsOffset, 10, 0.5F, 3, 5, 1);
		leftArm.addChild(clawLeftUpper1);
		rightArm.addChild(clawRightUpper1);
		leftArm.addChild(clawLeftUpper2);
		rightArm.addChild(clawRightUpper2);
		leftArm.addChild(clawLeftLower1);
		rightArm.addChild(clawRightLower1);
		leftArm.addChild(clawLeftLower2);
		rightArm.addChild(clawRightLower2);
	}
}
