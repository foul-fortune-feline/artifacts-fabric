package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.world.entity.LivingEntity;

public class HeliumFlamingoModel extends HumanoidModel<LivingEntity> {

	public HeliumFlamingoModel() {
		super(0, 0, 64, 64);
		body = new ModelPart(this);

		ModelPart bone = new ModelPart(this);
		bone.setPos(0, 0, 0);
		bone.texOffs(16, 36).addBox(-1, 1, -14, 2, 3, 5);
		bone.texOffs(0, 18).addBox(4, 9, -7, 4, 4, 14);
		bone.texOffs(0, 0).addBox(-8, 9, -7, 4, 4, 14);
		bone.texOffs(36, 0).addBox(-4, 9, 3, 8, 4, 4);
		bone.texOffs(36, 8).addBox(-4, 9, -7, 8, 4, 4);
		bone.texOffs(0, 36).addBox(-2, 1, -9, 4, 11, 4);

		body.addChild(bone);

		setAllVisible(false);
		body.visible = true;
	}
}
