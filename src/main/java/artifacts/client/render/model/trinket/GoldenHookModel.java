package artifacts.client.render.model.trinket;

import net.minecraft.client.model.geom.ModelPart;

public class GoldenHookModel extends GloveModel {
	
	public GoldenHookModel(boolean smallArms) {
		super(smallArms);

		ModelPart hookRight = new ModelPart(this, 0, 0);
		ModelPart hookLeft = new ModelPart(this, 0, 9);
		ModelPart hookBaseRight = new ModelPart(this, 0, 6);
		ModelPart hookBaseLeft = new ModelPart(this, 0, 15);

		hookRight.addBox(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);
		hookLeft.addBox(smallArms ? -2 : -1.5F, 12, -0.5F, 5, 5, 1);
		hookBaseRight.addBox(smallArms ? -1 : -1.5F, 10, -0.5F, 1, 2, 1);
		hookBaseLeft.addBox(smallArms ? 0 : 0.5F, 10, -0.5F, 1, 2, 1);

		rightArm.addChild(hookRight);
		leftArm.addChild(hookLeft);
		rightArm.addChild(hookBaseRight);
		leftArm.addChild(hookBaseLeft);
	}
}
