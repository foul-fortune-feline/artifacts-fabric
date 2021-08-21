package artifacts.client.render.curio.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.MathHelper;

public class CloudInABottleModel extends BeltModel {

    protected final ModelRenderer cloud;

    protected CloudInABottleModel() {
        super(RenderType::getEntityTranslucent, 4, -3, -0.5F);

        // jar
        charm.setTextureOffset(0, 16);
        charm.addBox(-2, 0, -2, 4, 5, 4);

        // lid
        charm.setTextureOffset(0, 25);
        charm.addBox(-1, -1, -1, 2, 1, 2);

        // cloud
        cloud = new ModelRenderer(this).setTextureOffset(8, 25);
        cloud.addBox(-1, 1.5F, -1, 2, 2, 2);
        charm.addChild(cloud);
    }

    @Override
    public void setRotationAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        super.setRotationAngles(entity, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
        cloud.rotateAngleY = (ageInTicks) / 50;
        cloud.rotationPointY = MathHelper.cos((ageInTicks) / 30) / 2;
    }

    public static CloudInABottleModel cloudInABottle() {
        return new CloudInABottleModel();
    }
}
