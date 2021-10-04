package artifacts.client.render.trinket.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.LivingEntity;

public class HandsModel extends HumanoidModel<LivingEntity> {

    protected HandsModel(ModelPart modelPart) {
        super(modelPart);
        setAllVisible(false);
    }

    public void renderHand(HumanoidArm handSide, PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        leftArm.visible = handSide == HumanoidArm.LEFT;
        rightArm.visible = !leftArm.visible;
        renderToBuffer(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }

    private static HandsModel bake(MeshDefinition mesh, int textureWidth, int textureHeight) {
        return new HandsModel(LayerDefinition.create(mesh, textureWidth, textureHeight).bakeRoot());
    }

    public static HandsModel claws(boolean smallArms) {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);

        int smallArmsOffset = smallArms ? 1 : 0;

        CubeListBuilder leftArm = CubeListBuilder.create();
        CubeListBuilder rightArm = CubeListBuilder.create();

        // claw 1 lower
        leftArm.texOffs(0, 0);
        leftArm.addBox(-smallArmsOffset, 10, -1.5F, 3, 5, 1);
        rightArm.texOffs(8, 0);
        rightArm.addBox(-3 + smallArmsOffset, 10, -1.5F, 3, 5, 1);

        // claw 2 lower
        leftArm.texOffs(0, 6);
        leftArm.addBox(-smallArmsOffset, 10, 0.5F, 3, 5, 1);
        rightArm.texOffs(8, 6);
        rightArm.addBox(-3 + smallArmsOffset, 10, 0.5F, 3, 5, 1);

        // claw 1 upper
        leftArm.texOffs(16, 0);
        leftArm.addBox(3 - smallArmsOffset, 10, -1.5F, 1, 4, 1);
        rightArm.texOffs(20, 0);
        rightArm.addBox(-4 + smallArmsOffset, 10, -1.5F, 1, 4, 1);

        // claw 2 upper
        leftArm.texOffs(16, 6);
        leftArm.addBox(3 - smallArmsOffset, 10, 0.5F, 1, 4, 1);
        rightArm.texOffs(20, 6);
        rightArm.addBox(-4 + smallArmsOffset, 10, 0.5F, 1, 4, 1);

        mesh.getRoot().addOrReplaceChild("left_arm", leftArm, PartPose.ZERO);
        mesh.getRoot().addOrReplaceChild("right_arm", rightArm, PartPose.ZERO);

        return bake(mesh, 32, 16);
    }

    public static HandsModel glove(boolean smallArms) {
        return glove(smallArms, 32, 32);
    }

    public static HandsModel glove(boolean smallArms, int textureWidth, int textureHeight) {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0f);
        
        CubeListBuilder leftArm = CubeListBuilder.create();
        CubeListBuilder rightArm = CubeListBuilder.create();

        // arms
        leftArm.texOffs(0, 0);
        leftArm.addBox(-1, -2, -2, smallArms ? 3 : 4, 12, 4, new CubeDeformation(0.5F));
        rightArm.texOffs(16, 0);
        rightArm.addBox(smallArms ? -2 : -3, -2, -2, smallArms ? 3 : 4, 12, 4, new CubeDeformation(0.5F));

        // sleeves
        leftArm.texOffs(0, 16);
        leftArm.addBox(-1, -2, -2, smallArms ? 3 : 4, 12, 4, new CubeDeformation(0.5F + 0.25F));
        rightArm.texOffs(16, 16);
        rightArm.addBox(smallArms ? -2 : -3, -2, -2, smallArms ? 3 : 4, 12, 4, new CubeDeformation(0.5F + 0.25F));

        mesh.getRoot().addOrReplaceChild("left_arm", leftArm, PartPose.offset(5, smallArms ? 2.5F : 2, 0));
        mesh.getRoot().addOrReplaceChild("left_arm", leftArm, PartPose.offset(-5, smallArms ? 2.5F : 2, 0));

        return bake(mesh, textureWidth, textureHeight);
    }

    public static HandsModel goldenHook(boolean smallArms) {
        HandsModel model = glove(smallArms, 64, 32);

        // hook
        model.leftArm.texOffs(32, 0);
        model.leftArm.addBox(smallArms ? -2 : -1.5F, 12, -0.5F, 5, 5, 1);
        model.rightArm.texOffs(48, 0);
        model.rightArm.addBox(smallArms ? -3 : -3.5F, 12, -0.5F, 5, 5, 1);

        // hook base
        model.leftArm.texOffs(32, 6);
        model.leftArm.addBox(smallArms ? 0 : 0.5F, 10, -0.5F, 1, 2, 1);
        model.rightArm.texOffs(48, 6);
        model.rightArm.addBox(smallArms ? -1 : -1.5F, 10, -0.5F, 1, 2, 1);

        return model;
    }
}
