package artifacts.client.render.model.trinket;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.entity.LivingEntity;

public class SnorkelModel extends HumanoidModel<LivingEntity> {

	public SnorkelModel() {
		super(RenderType::entityTranslucent, 0.5F, 0, 64, 64);

		setAllVisible(false);
		head.visible = true;
		hat.visible = true;

		ModelPart snorkelMouthPiece = new ModelPart(this, 0, 46);
		ModelPart snorkelTube = new ModelPart(this, 0, 32);

		snorkelMouthPiece.addBox(-2, -1.5F, -6, 8, 2, 2);
		snorkelTube.addBox(4.01F, -5, -3, 2, 2, 12);

		head.addChild(snorkelMouthPiece);
		head.addChild(snorkelTube);

		snorkelTube.xRot = 0.7853F;
	}
}
