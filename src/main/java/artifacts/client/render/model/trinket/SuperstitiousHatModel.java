package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class SuperstitiousHatModel extends HumanoidModel<LivingEntity> {

	public SuperstitiousHatModel() {
		super(0, 0, 64, 32);
		head = new ModelPart(this, 0, 0);
		head.addBox(-4, -16, -4, 8, 8, 8);
		ModelPart brim = new ModelPart(this, 0, 16);
		brim.addBox(-5, -9, -5, 10, 1, 10);
		head.addChild(brim);
		setAllVisible(false);
		head.visible = true;
	}
}
