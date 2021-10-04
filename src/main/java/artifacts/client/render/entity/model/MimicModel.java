package artifacts.client.render.entity.model;

import artifacts.entity.MimicEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;

public class MimicModel extends EntityModel<MimicEntity> {

    protected final ModelPart upperTeeth;
    protected final ModelPart lowerTeeth;
    protected final ModelPart upperMouthOverlay;
    protected final ModelPart lowerMouthOverlay;

    public MimicModel(ModelPart modelPart) {
        upperTeeth = modelPart.getChild("upper_teeth");
        lowerTeeth = modelPart.getChild("lower_teeth");
        upperMouthOverlay = modelPart.getChild("upper_mouth_overlay");
        lowerMouthOverlay = modelPart.getChild("lower_mouth_overlay");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition mesh = new MeshDefinition();
        PartDefinition root = mesh.getRoot();
        root.addOrReplaceChild("upper_teeth", CubeListBuilder.create().addBox(-6, 0, -13, 12, 3, 12), PartPose.offset(0, 15, 7));
        root.addOrReplaceChild("lower_teeth", CubeListBuilder.create().texOffs(0, 15).addBox(-6, -4, -13, 12, 3, 12), PartPose.offset(0, 15, 7));
        root.addOrReplaceChild("upper_mouth_overlay", CubeListBuilder.create().texOffs(24, 0).addBox(-6, 0, -13, 12, 0, 12, new CubeDeformation(0.02f)), PartPose.offset(0, 15, 7));
        root.addOrReplaceChild("lower_mouth_overlay", CubeListBuilder.create().texOffs(36, 15).addBox(-6, -1, -13, 12, 0, 12, new CubeDeformation(0.02f)), PartPose.offset(0, 15, 7));
        return LayerDefinition.create(mesh, 64, 32);
    }

    @Override
    public void setupAnim(MimicEntity mimic, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {

    }

    @Override
    public void prepareMobModel(MimicEntity mimic, float limbSwing, float limbSwingAmount, float partialTicks) {
        if (mimic.ticksInAir > 0) {
            upperTeeth.xRot = upperMouthOverlay.xRot = Math.max(-60, (mimic.ticksInAir - 1 + partialTicks) * -6) * 0.0174533F;
            lowerTeeth.xRot = lowerMouthOverlay.xRot = Math.min(30, (mimic.ticksInAir - 1 + partialTicks) * 3) * 0.0174533F;
        } else {
            upperTeeth.xRot = upperMouthOverlay.xRot = 0;
            lowerTeeth.xRot = lowerMouthOverlay.xRot = 0;
        }
    }

    @Override
    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        upperTeeth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        lowerTeeth.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        upperMouthOverlay.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
        lowerMouthOverlay.render(matrixStack, buffer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
