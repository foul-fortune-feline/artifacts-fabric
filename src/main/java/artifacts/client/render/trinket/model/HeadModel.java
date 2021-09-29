package artifacts.client.render.trinket.model;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;

import java.util.function.Function;

public class HeadModel {

    private static HumanoidModel<LivingEntity> bake(MeshDefinition mesh) {
        return bake(mesh, RenderType::entityCutoutNoCull, 32, 32);
    }

    private static HumanoidModel<LivingEntity> bake(MeshDefinition mesh, Function<ResourceLocation, RenderType> renderType, int textureWidth, int textureHeight) {
        HumanoidModel<LivingEntity> model = new HumanoidModel<>(LayerDefinition.create(mesh, textureWidth, textureHeight).bakeRoot(), renderType);
        model.setAllVisible(false);
        model.head.visible = true;
        return model;
    }

    private static MeshDefinition headMesh() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        mesh.getRoot().addOrReplaceChild("head", CubeListBuilder.create().addBox(-4, -8, -4, 8, 8, 8, new CubeDeformation(0.5f)), PartPose.ZERO);
        return mesh;
    }

    public static HumanoidModel<LivingEntity> drinkingHat() {
        MeshDefinition mesh = headMesh();
        PartDefinition head = mesh.getRoot().getChild("head");

        // hat shade
        CubeListBuilder cubes = CubeListBuilder.create();
        cubes.texOffs(32, 11);
        cubes.addBox(-4, -6, -8, 8, 1, 4);

        // cans
        cubes.texOffs(32, 0);
        cubes.addBox(4, -11, -1, 3, 6, 3);
        cubes.texOffs(44, 0);
        cubes.addBox(-7, -11, -1, 3, 6, 3);

        // straws
        CubeListBuilder straws = CubeListBuilder.create();
        straws.texOffs(0, 16);
        straws.addBox(5, -4, -3, 1, 1, 8);
        straws.texOffs(18, 16);
        straws.addBox(-6, -4, -3, 1, 1, 8);

        // straw middle
        cubes.texOffs(32, 9);
        cubes.addBox(-6, -1, -5, 12, 1, 1);

        PartDefinition drinking_hat = head.addOrReplaceChild("drinking_hat", cubes, PartPose.ZERO);
        drinking_hat.addOrReplaceChild("straws", straws, PartPose.rotation(45 * (float) Math.PI / 180, 0, 0));

        return bake(mesh, RenderType::entityTranslucent, 64, 32);
    }

    public static HumanoidModel<LivingEntity> nightVisionGoggles() {
        MeshDefinition mesh = headMesh();
        PartDefinition head = mesh.getRoot().getChild("head");

        CubeListBuilder cubes = CubeListBuilder.create();

        // plate
        cubes.texOffs(0, 21);
        cubes.addBox(-4, -6, -5 + 0.05F, 8, 4, 1);

        // eyeholes
        cubes.texOffs(0, 16);
        cubes.addBox(1.5F, -5, -8 + 0.05F, 2, 2, 3);
        cubes.texOffs(10, 16);
        cubes.addBox(-3.5F, -5, -8 + 0.05F, 2, 2, 3);

        head.addOrReplaceChild("night_vision_goggles", cubes, PartPose.ZERO);

        return bake(mesh);
    }

    public static HumanoidModel<LivingEntity> snorkel() {
        MeshDefinition mesh = headMesh();
        PartDefinition head = mesh.getRoot().getChild("head");

        // horizontal tube
        CubeListBuilder cubes = CubeListBuilder.create();
        cubes.texOffs(32, 0);
        cubes.addBox(-2, -1.5F, -6, 8, 2, 2);

        // diagonal tube
        CubeListBuilder tube = CubeListBuilder.create();
        tube.texOffs(0, 16);
        tube.addBox(4.01F, -5, -3, 2, 2, 12);

        PartDefinition snorkel = head.addOrReplaceChild("snorkel", cubes, PartPose.ZERO);
        snorkel.addOrReplaceChild("tube", tube, PartPose.rotation(45 * (float) Math.PI / 180, 0, 0));

        return bake(mesh, RenderType::entityTranslucent, 64, 32);
    }

    public static HumanoidModel<LivingEntity> superstitiousHat() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        CubeListBuilder cubes = CubeListBuilder.create();

        // hat
        cubes.texOffs(0, 0);
        cubes.addBox(-4, -16, -4, 8, 8, 8);

        // brim
        cubes.texOffs(0, 16);
        cubes.addBox(-5, -9, -5, 10, 1, 10);

        return bake(mesh, RenderType::entityCutoutNoCull,64, 32);
    }

    public static HumanoidModel<LivingEntity> villagerHat() {
        MeshDefinition mesh = headMesh();
        PartDefinition head = mesh.getRoot().getChild("head");

        // brim
        CubeListBuilder cubes = CubeListBuilder.create();
        cubes.texOffs(0, 16);
        cubes.addBox(-8, -5.125F, -8, 16, 0, 16);

        head.addOrReplaceChild("villager_hat", cubes, PartPose.ZERO);
        return bake(mesh);
    }

    public static HumanoidModel<LivingEntity> whoopeeCushion() {
        MeshDefinition mesh = HumanoidModel.createMesh(CubeDeformation.NONE, 0);
        CubeListBuilder cubes = CubeListBuilder.create();

        // cushion
        cubes.texOffs(0, 0);
        cubes.addBox(-3, -10, -3, 6, 2, 6);

        // flap
        cubes.texOffs(0, 8);
        cubes.addBox(-2, -9.25F, 3, 4, 0, 4);

        mesh.getRoot().addOrReplaceChild("head", cubes, PartPose.ZERO);

        return bake(mesh, RenderType::entityCutoutNoCull, 32, 16);
    }
}
