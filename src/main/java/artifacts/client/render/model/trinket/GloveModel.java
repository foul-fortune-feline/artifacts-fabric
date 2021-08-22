package artifacts.client.render.model.trinket;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class GloveModel extends PlayerModel<LivingEntity> {

	public GloveModel(boolean smallArms) {
		super(0.5F, smallArms);

		setAllVisible(false);
	}

	public void renderHand(boolean mainHand, PoseStack matrices, VertexConsumer vertexConsumers, int light, int overlay, float red, float green, float blue, float alpha) {
		boolean isRightArm = (Minecraft.getInstance().options.mainHand == HumanoidArm.LEFT) != mainHand;
		rightSleeve.copyFrom(rightArm);
		leftSleeve.copyFrom(leftArm);
		leftArm.visible = !isRightArm;
		leftSleeve.visible = !isRightArm;
		rightArm.visible = isRightArm;
		rightSleeve.visible = isRightArm;
		renderToBuffer(matrices, vertexConsumers, light, overlay, red, green, blue, alpha);
	}
}
