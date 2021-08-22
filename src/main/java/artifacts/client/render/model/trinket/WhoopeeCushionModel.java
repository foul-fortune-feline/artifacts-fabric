package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class WhoopeeCushionModel extends HumanoidModel<LivingEntity> {

	public WhoopeeCushionModel() {
		super(0, 0, 32, 16);

		setAllVisible(false);
		head.visible = true;

		head = new ModelPart(this);
		head.addBox(-3, -10, -3, 6, 2, 6);
		head.texOffs(0, 8).addBox(-2, -9.25F, 3, 4, 0, 4);
	}
}
