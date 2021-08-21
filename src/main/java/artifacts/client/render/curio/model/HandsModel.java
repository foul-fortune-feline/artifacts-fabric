package artifacts.client.render.curio.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.HandSide;

public class HandsModel extends BipedModel<LivingEntity> {

    protected HandsModel(int textureWidth, int textureHeight) {
        super(0, 0, textureWidth, textureHeight);
        setVisible(false);

        bipedLeftArm = new ModelRenderer(this);
        bipedRightArm = new ModelRenderer(this);
    }

    public void renderHand(HandSide handSide, MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        bipedLeftArm.showModel = handSide == HandSide.LEFT;
        bipedRightArm.showModel = !bipedLeftArm.showModel;
        render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private static HandsModel hands(int textureWidth, int textureHeight) {
        return new HandsModel(textureWidth, textureHeight);
    }

    public static HandsModel claws(boolean smallArms) {
        HandsModel model = hands(32, 16);

        int smallArmsOffset = smallArms ? 1 : 0;

        // claw 1 lower
        model.bipedLeftArm.setTextureOffset(0, 0);
        model.bipedLeftArm.addBox(-smallArmsOffset, 10, -1.5F, 3, 5, 1);
        model.bipedRightArm.setTextureOffset(8, 0);
        model.bipedRightArm.addBox(-3 + smallArmsOffset, 10, -1.5F, 3, 5, 1);

        // claw 2 lower
        model.bipedLeftArm.setTextureOffset(0, 6);
        model.bipedLeftArm.addBox(-smallArmsOffset, 10, 0.5F, 3, 5, 1);
        model.bipedRightArm.setTextureOffset(8, 6);
        model.bipedRightArm.addBox(-3 + smallArmsOffset, 10, 0.5F, 3, 5, 1);

        // claw 1 upper
        model.bipedLeftArm.setTextureOffset(16, 0);
        model.bipedLeftArm.addBox(3 - smallArmsOffset, 10, -1.5F, 1, 4, 1);
        model.bipedRightArm.setTextureOffset(20, 0);
        model.bipedRightArm.addBox(-4 + smallArmsOffset, 10, -1.5F, 1, 4, 1);

        // claw 2 upper
        model.bipedLeftArm.setTextureOffset(16, 6);
        model.bipedLeftArm.addBox(3 - smallArmsOffset, 10, 0.5F, 1, 4, 1);
        model.bipedRightArm.setTextureOffset(20, 6);
        model.bipedRightArm.addBox(-4 + smallArmsOffset, 10, 0.5F, 1, 4, 1);

        return model;
    }

    public static HandsModel glove(boolean smallArms) {
        return glove(smallArms, 32, 32);
    }

    public static HandsModel glove(boolean smallArms, int textureWidth, int textureHeight) {
        HandsModel model = hands(textureWidth, textureHeight);

        model.bipedLeftArm.setRotationPoint(5, smallArms ? 2.5F : 2, 0);
        model.bipedRightArm.setRotationPoint(-5, smallArms ? 2.5F : 2, 0);

        // arms
        model.bipedLeftArm.setTextureOffset(0, 0);
        model.bipedLeftArm.addBox(-1, -2, -2, smallArms ? 3 : 4, 12, 4, 0.5F);
        model.bipedRightArm.setTextureOffset(16, 0);
        model.bipedRightArm.addBox(smallArms ? -2 : -3, -2, -2, smallArms ? 3 : 4, 12, 4, 0.5F);

        // sleeves
        model.bipedLeftArm.setTextureOffset(0, 16);
        model.bipedLeftArm.addBox(-1, -2, -2, smallArms ? 3 : 4, 12, 4, 0.5F + 0.25F);
        model.bipedRightArm.setTextureOffset(16, 16);
        model.bipedRightArm.addBox(smallArms ? -2 : -3, -2, -2, smallArms ? 3 : 4, 12, 4, 0.5F + 0.25F);

        return model;
    }

    public static HandsModel goldenHook(boolean smallArms) {
        HandsModel model = glove(smallArms, 64, 32);

        // hook
        model.bipedLeftArm.setTextureOffset(32, 0);
        model.bipedLeftArm.addBox(smallArms ? -2 : -1.5F, 12, -0.5F, 5, 5, 1);
        model.bipedRightArm.setTextureOffset(48, 0);
        model.bipedRightArm.addBox(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);

        // hook base
        model.bipedLeftArm.setTextureOffset(32, 6);
        model.bipedLeftArm.addBox(smallArms ? 0 : 0.5F, 10, -0.5F, 1, 2, 1);
        model.bipedRightArm.setTextureOffset(48, 6);
        model.bipedRightArm.addBox(smallArms ? -1 : -1.5F, 10, -0.5F, 1, 2, 1);

        return model;
    }
}
