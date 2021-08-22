package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class ShoesModel extends HumanoidModel<LivingEntity> {

	public ShoesModel(float delta) {
		super(0, 0, 32, 32);

		leftLeg = new ModelPart(this, 0, 0);
		rightLeg = new ModelPart(this, 16, 0);

		leftLeg.addBox(-2, 0, -2, 4, 12, 4, delta);
		rightLeg.addBox(-2, 0, -2, 4, 12, 4, delta);
		leftLeg.texOffs(0, 16);
		rightLeg.texOffs(16, 16);
		leftLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);
		rightLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);

		setAllVisible(false);
		leftLeg.visible = rightLeg.visible = true;
	}
}
