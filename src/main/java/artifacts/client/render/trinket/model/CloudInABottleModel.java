package artifacts.client.render.trinket.model;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;

public class CloudInABottleModel extends BeltModel {

    protected final ModelPart cloud;

    protected CloudInABottleModel() {
        super(RenderType::entityTranslucent, 4, -3, -0.5F);

        // jar
        charm.texOffs(0, 16);
        charm.addBox(-2, 0, -2, 4, 5, 4);

        // lid
        charm.texOffs(0, 25);
        charm.addBox(-1, -1, -1, 2, 1, 2);

        // cloud
        cloud = new ModelPart(this).texOffs(8, 25);
        cloud.addBox(-1, 1.5F, -1, 2, 2, 2);
        charm.addChild(cloud);
    }

    @Override
    public void setupAnim(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setupAnim(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        cloud.yRot = (ageInTicks) / 50;
        cloud.y = Mth.cos((ageInTicks) / 30) / 2;
    }

    public static CloudInABottleModel cloudInABottle() {
        return new CloudInABottleModel();
    }
}
