package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class AntidoteVesselModel extends HumanoidModel<LivingEntity> {

	public AntidoteVesselModel() {
		super(0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart jar = new ModelPart(this, 0, 16);
		ModelPart lid = new ModelPart(this, 0, 26);

		body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

		jar.addBox(-2, 0, -2, 4, 6, 4);
		lid.addBox(-1, -1, -1, 2, 1, 2);
		jar.setPos(4, 9, -3);
		jar.yRot = -0.5F;

		jar.addChild(lid);
		body.addChild(jar);

		setAllVisible(false);
		body.visible = true;
	}
}
