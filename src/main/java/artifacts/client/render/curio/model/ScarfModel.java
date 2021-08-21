package artifacts.client.render.curio.model;

import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;

import java.util.function.Function;

public class ScarfModel extends BipedModel<LivingEntity> {

    private final ModelRenderer cloak;

    protected ScarfModel(Function<ResourceLocation, RenderType> renderType) {
        super(renderType, 0.5F, 0, 64, 32);
        setVisible(false);

        bipedHead.showModel = true;
        bipedBody = new ModelRenderer(this);
        cloak = new ModelRenderer(this);
        cloak.setRotationPoint(0, 0, 1.99F);
        bipedBody.addChild(cloak);

        // scarf
        bipedBody.setTextureOffset(0, 16);
        bipedBody.addBox(-6.01F, -2, -4, 12, 6, 8);

        // dangly bit
        cloak.setTextureOffset(32, 0);
        cloak.addBox(-5, 0, 0, 5, 12, 2);
    }

    @Override
    public void setLivingAnimations(LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (entity instanceof AbstractClientPlayerEntity) {
            AbstractClientPlayerEntity player = (AbstractClientPlayerEntity) entity;
            double x = MathHelper.lerp(partialTicks, player.prevChasingPosX, player.chasingPosX) - MathHelper.lerp(partialTicks, player.prevPosX, player.getPosX());
            double y = MathHelper.lerp(partialTicks, player.prevChasingPosY, player.chasingPosY) - MathHelper.lerp(partialTicks, player.prevPosY, player.getPosY());
            double z = MathHelper.lerp(partialTicks, player.prevChasingPosZ, player.chasingPosZ) - MathHelper.lerp(partialTicks, player.prevPosZ, player.getPosZ());
            float f = player.prevRenderYawOffset + (player.renderYawOffset - player.prevRenderYawOffset);
            double d3 = MathHelper.sin(f * ((float) Math.PI / 180));
            double d4 = -MathHelper.cos(f * ((float) Math.PI / 180));
            float f1 = (float) y * 10;
            f1 = MathHelper.clamp(f1, -6, 32);
            float f2 = (float) (x * d3 + z * d4) * 100;
            f2 = MathHelper.clamp(f2, 0, 150);
            if (f2 < 0) {
                f2 = 0;
            }

            float f4 = MathHelper.lerp(partialTicks, player.prevCameraYaw, player.cameraYaw);
            f1 = f1 + MathHelper.sin(MathHelper.lerp(partialTicks, player.prevDistanceWalkedModified, player.distanceWalkedModified) * 6) * 32 * f4;

            cloak.rotateAngleX = bipedBody.rotateAngleX + (6 + f2 / 2 + f1) / 180 * (float) Math.PI;
        }
    }

    public static ScarfModel scarf(Function<ResourceLocation, RenderType> renderType) {
        return new ScarfModel(renderType);
    }
}
