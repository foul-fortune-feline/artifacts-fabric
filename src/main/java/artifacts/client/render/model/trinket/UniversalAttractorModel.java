package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class UniversalAttractorModel extends HumanoidModel<LivingEntity> {

	public UniversalAttractorModel() {
		super(0.5F, 0, 32, 32);

		body = new ModelPart(this, 0, 0);
		ModelPart magnet = new ModelPart(this, 0, 16);

		ModelPart magnet1 = new ModelPart(this, 0, 19);
		ModelPart magnet2 = new ModelPart(this, 6, 19);

		body.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

		magnet.addBox(0, 9, -3, 5, 2, 1);
		magnet1.addBox(0, 11, -3, 2, 4, 1);
		magnet2.addBox(3, 11, -3, 2, 4, 1);

		magnet.addChild(magnet1);
		magnet.addChild(magnet2);
		body.addChild(magnet);

		setAllVisible(false);
		body.visible = true;
	}
}
