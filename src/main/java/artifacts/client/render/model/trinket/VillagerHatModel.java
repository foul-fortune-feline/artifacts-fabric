package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class VillagerHatModel extends HumanoidModel<LivingEntity> {

	public VillagerHatModel() {
		super(0.5F, 0, 32, 32);
		ModelPart brim = new ModelPart(this, 0, 16);
		brim.addBox(-8, -5.125F, -8, 16, 0, 16);
		head.addChild(brim);
		setAllVisible(false);
		head.visible = true;
	}
}
