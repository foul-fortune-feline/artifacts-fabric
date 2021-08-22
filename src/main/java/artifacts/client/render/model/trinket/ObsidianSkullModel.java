package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class ObsidianSkullModel extends HumanoidModel<LivingEntity> {

	public ObsidianSkullModel() {
		super(0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart skull = new ModelPart(this, 0, 16);

		ModelPart tooth1 = new ModelPart(this, 18, 16);
		ModelPart tooth2 = new ModelPart(this, 18, 19);

		body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

		skull.addBox(-2.5F, 0, 0, 5, 3, 4);
		tooth1.addBox(-1.5F, 3, 0, 1, 1, 2);
		tooth2.addBox(0.5F, 3, 0, 1, 1, 2);
		skull.setPos(4.5F, 9, -4);

		skull.yRot = -0.5F;

		skull.addChild(tooth1);
		skull.addChild(tooth2);

		body.addChild(skull);

		setAllVisible(false);
		body.visible = true;
	}
}
