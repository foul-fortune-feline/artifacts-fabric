package artifacts.client.render.curio.model;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;

import java.util.function.Function;

public class BeltModel extends BipedModel<LivingEntity> {

    protected final ModelRenderer charm = new ModelRenderer(this);

    private final float xOffset;
    private final float zOffset;
    private final float rotation;

    protected BeltModel(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        super(renderType, 0.5F, 0, 32, 32);
        this.xOffset = xOffset;
        this.zOffset = zOffset;
        this.rotation = rotation;

        setVisible(false);

        bipedBody = new ModelRenderer(this);

        // belt
        bipedBody.setTextureOffset(0, 0);
        bipedBody.addBox(-4, 0, -2, 8, 12, 4, 0.5F);

        // charm
        bipedBody.addChild(charm);
    }

    public void setCharmPosition(int slot) {
        float xOffset = slot % 2 == 0 ? this.xOffset : -this.xOffset;
        float zOffset = slot % 4 < 2 ? this.zOffset : -this.zOffset;
        charm.setRotationPoint(xOffset, 9, zOffset);

        float rotation = slot % 4 < 2 ? 0 : (float) -Math.PI;
        rotation += slot % 2 == 0 ^ slot % 4 >= 2 ? this.rotation : -this.rotation;
        charm.rotateAngleY = rotation;
    }

    private static BeltModel belt(float xOffset, float zOffset, float rotation) {
        return belt(RenderType::getEntityCutoutNoCull, xOffset, zOffset, rotation);
    }

    private static BeltModel belt(Function<ResourceLocation, RenderType> renderType, float xOffset, float zOffset, float rotation) {
        return new BeltModel(renderType, xOffset, zOffset, rotation);
    }

    public static BeltModel antidoteVessel() {
        BeltModel model = belt(4, -3, -0.5F);

        // jar
        model.charm.setTextureOffset(0, 16);
        model.charm.addBox(-2, 0, -2, 4, 6, 4);

        // lid
        model.charm.setTextureOffset(0, 26);
        model.charm.addBox(-1, -1, -1, 2, 1, 2);

        return model;
    }

    public static BeltModel crystalHeart() {
        BeltModel model = belt(RenderType::getEntityTranslucent, 2.5F, -3.01F, 0);

        // heart parts
        model.charm.setTextureOffset(0, 16);
        model.charm.addBox(-2.5F, 0, 0, 2, 3, 1);
        model.charm.setTextureOffset(6, 16);
        model.charm.addBox(0.5F, 0, 0, 2, 3, 1);
        model.charm.setTextureOffset(0, 20);
        model.charm.addBox(-0.5F, 1, 0, 1, 4, 1);
        model.charm.setTextureOffset(4, 20);
        model.charm.addBox(-1.5F, 3, 0, 1, 1, 1);
        model.charm.setTextureOffset(8, 20);
        model.charm.addBox(0.5F, 3, 0, 1, 1, 1);

        return model;
    }

    public static BipedModel<LivingEntity> heliumFlamingo() {
        BipedModel<LivingEntity> model = new BipedModel<>(RenderType::getEntityCutoutNoCull, 0, 0, 64, 64);
        model.setVisible(false);

        model.bipedBody = new ModelRenderer(model);
        model.bipedBody.setTextureOffset(16, 36).addBox(-1, 1, -14, 2, 3, 5);
        model.bipedBody.setTextureOffset(0, 18).addBox(4, 9, -7, 4, 4, 14);
        model.bipedBody.setTextureOffset(0, 0).addBox(-8, 9, -7, 4, 4, 14);
        model.bipedBody.setTextureOffset(36, 0).addBox(-4, 9, 3, 8, 4, 4);
        model.bipedBody.setTextureOffset(36, 8).addBox(-4, 9, -7, 8, 4, 4);
        model.bipedBody.setTextureOffset(0, 36).addBox(-2, 1, -9, 4, 11, 4);

        return model;
    }

    public static BeltModel obsidianSkull() {
        BeltModel model = belt(4.5F, -4F, -0.5F);

        // skull
        model.charm.setTextureOffset(0, 16);
        model.charm.addBox(-2.5F, 0, 0, 5, 3, 4);

        // teeth
        model.charm.setTextureOffset(18, 16);
        model.charm.addBox(-1.5F, 3, 0, 1, 1, 2);
        model.charm.setTextureOffset(18, 19);
        model.charm.addBox(0.5F, 3, 0, 1, 1, 2);

        return model;
    }

    public static BeltModel universalAttractor() {
        BeltModel model = belt(2.5F, -3, 0);

        // magnet
        model.charm.setTextureOffset(0, 16);
        model.charm.addBox(-2.5F, 0, 0, 5, 2, 1);
        model.charm.setTextureOffset(0, 19);
        model.charm.addBox(-2.5F, 2, 0, 2, 4, 1);
        model.charm.setTextureOffset(6, 19);
        model.charm.addBox(0.5F, 2, 0, 2, 4, 1);

        return model;
    }

}
