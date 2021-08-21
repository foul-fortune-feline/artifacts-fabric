package artifacts.client.render.curio.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;

public class LegsModel {

    private static BipedModel<LivingEntity> legs(float delta, int textureWidth, int textureHeight) {
        BipedModel<LivingEntity> model = new BipedModel<>(RenderType::getEntityCutoutNoCull, 0, 0, textureWidth, textureHeight);
        model.setVisible(false);

        model.bipedLeftLeg = new ModelRenderer(model);
        model.bipedRightLeg = new ModelRenderer(model);

        // legs
        model.bipedLeftLeg.setTextureOffset(0, 0);
        model.bipedLeftLeg.addBox(-2, 0, -2, 4, 12, 4, delta);
        model.bipedRightLeg.setTextureOffset(16, 0);
        model.bipedRightLeg.addBox(-2, 0, -2, 4, 12, 4, delta);

        return model;
    }

    private static BipedModel<LivingEntity> sleevedLegs(float delta, int textureWidth, int textureHeight) {
        BipedModel<LivingEntity> model = legs(delta, textureWidth, textureHeight);

        // pants sleeves
        model.bipedLeftLeg.setTextureOffset(0, 16);
        model.bipedLeftLeg.addBox(-2, 0, -2, 4, 12, 4, delta + 0.25F);
        model.bipedRightLeg.setTextureOffset(16, 16);
        model.bipedRightLeg.addBox(-2, 0, -2, 4, 12, 4, delta + 0.25F);

        return model;
    }

    public static BipedModel<LivingEntity> shoes(float delta) {
        BipedModel<LivingEntity> model = legs(delta, 32, 32);

        // shoe tip
        model.bipedLeftLeg.setTextureOffset(0, 16);
        model.bipedLeftLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);
        model.bipedRightLeg.setTextureOffset(16, 16);
        model.bipedRightLeg.addBox(-2, 12 - 3 + delta * 3 / 4, -3F - delta * 5 / 4, 4, 3, 1, delta, delta / 4, delta / 4);

        return model;
    }

    private static BipedModel<LivingEntity> slippers() {
        BipedModel<LivingEntity> model = sleevedLegs(0.51F, 64, 32);

        // heads
        model.bipedLeftLeg.setTextureOffset(32, 0);
        model.bipedLeftLeg.addBox(-2.5F, 8.51F, -7.01F, 5, 4, 5);
        model.bipedRightLeg.setTextureOffset(32, 16);
        model.bipedRightLeg.addBox(-2.5F, 8.51F, -7, 5, 4, 5);

        return model;
    }

    public static BipedModel<LivingEntity> aquaDashers(float delta) {
        BipedModel<LivingEntity> model = shoes(delta);

        // wings
        model.bipedLeftLeg.setTextureOffset(0, 16);
        model.bipedLeftLeg.addBox(2 + delta, 0, -2 + 3 + delta * 3 / 2, 0, 12, 4, 0, delta, delta);
        model.bipedRightLeg.setTextureOffset(16, 16);
        model.bipedRightLeg.addBox(-2 - delta, 0, -2 + 3 + delta * 3 / 2, 0, 12, 4, 0, delta, delta);

        return model;
    }

    public static BipedModel<LivingEntity> bunnyHoppers() {
        BipedModel<LivingEntity> model = slippers();

        ModelRenderer ear1Left = new ModelRenderer(model, 52, 0);
        ModelRenderer ear1Right = new ModelRenderer(model, 52, 16);
        ModelRenderer ear2Left = new ModelRenderer(model, 58, 0);
        ModelRenderer ear2Right = new ModelRenderer(model, 58, 16);
        ear1Left.rotateAngleY = -0.2617994F;
        ear1Right.rotateAngleY = -0.2617994F;
        ear2Left.rotateAngleY = 0.2617994F;
        ear2Right.rotateAngleY = 0.2617994F;
        model.bipedLeftLeg.addChild(ear1Left);
        model.bipedRightLeg.addChild(ear1Right);
        model.bipedLeftLeg.addChild(ear2Left);
        model.bipedRightLeg.addChild(ear2Right);

        ear1Left.addBox(-3.15F, 3.51F, -3.01F, 2, 5, 1);
        ear1Right.addBox(-3.15F, 3.51F, -3, 2, 5, 1);
        ear2Left.addBox(1.15F, 3.51F, -3.01F, 2, 5, 1);
        ear2Right.addBox(1.15F, 3.51F, -3, 2, 5, 1);

        // nose
        model.bipedLeftLeg.setTextureOffset(32, 9);
        model.bipedLeftLeg.addBox(-0.5F, 10, -7.5F, 1, 1, 1);
        model.bipedRightLeg.setTextureOffset(32, 25);
        model.bipedRightLeg.addBox(-0.5F, 10, -7.5F, 1, 1, 1);

        // tail
        model.bipedLeftLeg.setTextureOffset(52, 6);
        model.bipedLeftLeg.addBox(-1, 9, 2, 2, 2, 2);
        model.bipedRightLeg.setTextureOffset(52, 22);
        model.bipedRightLeg.addBox(-1, 9, 2, 2, 2, 2);

        return model;
    }

    public static BipedModel<LivingEntity> flippers() {
        BipedModel<LivingEntity> model = legs(0.5F, 64, 64);

        // flippers
        model.bipedLeftLeg.setTextureOffset(0, 16);
        model.bipedLeftLeg.addBox(-2, 11.5F, -16, 9, 0, 20);
        model.bipedRightLeg.setTextureOffset(0, 36);
        model.bipedRightLeg.addBox(-7, 11.5F, -16, 9, 0, 20);

        return model;
    }

    public static BipedModel<LivingEntity> kittySlippers() {
        BipedModel<LivingEntity> model = slippers();

        // ears
        model.bipedLeftLeg.setTextureOffset(32, 9);
        model.bipedLeftLeg.addBox(-2, 7.51F, -4, 1, 1, 2);
        model.bipedRightLeg.setTextureOffset(32, 25);
        model.bipedRightLeg.addBox(-2, 7.51F, -4, 1, 1, 2);
        model.bipedLeftLeg.setTextureOffset(38, 9);
        model.bipedLeftLeg.addBox(1, 7.51F, -4, 1, 1, 2);
        model.bipedRightLeg.setTextureOffset(38, 25);
        model.bipedRightLeg.addBox(1, 7.51F, -4, 1, 1, 2);

        // nose
        model.bipedLeftLeg.setTextureOffset(44, 9);
        model.bipedLeftLeg.addBox(-1.5F, 10.51F, -8, 3, 2, 1);
        model.bipedRightLeg.setTextureOffset(44, 25);
        model.bipedRightLeg.addBox(-1.5F, 10.51F, -8, 3, 2, 1);

        return model;
    }

    public static BipedModel<LivingEntity> steadfastSpikes() {
        BipedModel<LivingEntity> model = sleevedLegs(0.5F, 64, 32);

        // claws
        model.bipedLeftLeg.setTextureOffset(32, 0);
        model.bipedLeftLeg.addBox(-1.5F, 9, -7, 1, 3, 5);
        model.bipedRightLeg.setTextureOffset(43, 0);
        model.bipedRightLeg.addBox(-1.5F, 9, -7, 1, 3, 5);
        model.bipedLeftLeg.setTextureOffset(32, 8);
        model.bipedLeftLeg.addBox(0.5F, 9, -7, 1, 3, 5);
        model.bipedRightLeg.setTextureOffset(43, 8);
        model.bipedRightLeg.addBox(0.5F, 9, -7, 1, 3, 5);

        return model;
    }
}
