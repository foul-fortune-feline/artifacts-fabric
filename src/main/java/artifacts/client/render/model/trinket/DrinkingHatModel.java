package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class DrinkingHatModel extends HumanoidModel<LivingEntity> {

	protected final ModelPart hatShade;

	public DrinkingHatModel() {
		super(0.5F, 0, 64, 64);

		setAllVisible(false);
		head.visible = true;
		hat.visible = true;

		hatShade = new ModelPart(this, 0, 52);
		ModelPart straw = new ModelPart(this, 0, 50);
		ModelPart canLeft = new ModelPart(this, 0, 41);
		ModelPart canRight = new ModelPart(this, 12, 41);
		ModelPart strawLeft = new ModelPart(this, 0, 32);
		ModelPart strawRight = new ModelPart(this, 17, 32);

		hatShade.addBox(-4, -6, -8, 8, 1, 4);
		straw.addBox(-6, -1, -5, 12, 1, 1);
		canLeft.addBox(4, -11, -1, 3, 6, 3);
		canRight.addBox(-7, -11, -1, 3, 6, 3);
		strawLeft.addBox(5, -4, -3, 1, 1, 8);
		strawRight.addBox(-6, -4, -3, 1, 1, 8);

		head.addChild(hatShade);
		head.addChild(straw);
		head.addChild(canLeft);
		head.addChild(canRight);
		head.addChild(strawLeft);
		head.addChild(strawRight);

		strawLeft.xRot = 0.7853F;
		strawRight.xRot = 0.7853F;
	}
}
