package artifacts.client.render.curio.renderer;

import artifacts.Artifacts;
import artifacts.client.render.curio.model.BeltModel;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;
import top.theillusivec4.curios.api.type.capability.ICurio;

public class BeltCurioRenderer implements CurioRenderer {

    private final ResourceLocation texture;
    private final BeltModel model;

    public BeltCurioRenderer(String texturePath, BeltModel model) {
        this(new ResourceLocation(Artifacts.MODID, String.format("textures/entity/curio/%s.png", texturePath)), model);
    }

    public BeltCurioRenderer(ResourceLocation texture, BeltModel model) {
        this.texture = texture;
        this.model = model;
    }

    protected ResourceLocation getTexture() {
        return texture;
    }

    protected BeltModel getModel() {
        return model;
    }

    @Override
    public final void render(String identifier, int index, MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, LivingEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ticks, float headYaw, float headPitch, ItemStack stack) {
        BeltModel model = getModel();

        model.setRotationAngles(entity, limbSwing, limbSwingAmount, ticks, headYaw, headPitch);
        model.setLivingAnimations(entity, limbSwing, limbSwingAmount, partialTicks);
        model.setCharmPosition(index);
        ICurio.RenderHelper.followBodyRotations(entity, model);
        render(matrixStack, buffer, light, stack.hasEffect());
    }

    protected void render(MatrixStack matrixStack, IRenderTypeBuffer buffer, int light, boolean hasFoil) {
        RenderType renderType = model.getRenderType(getTexture());
        IVertexBuilder vertexBuilder = ItemRenderer.getBuffer(buffer, renderType, false, hasFoil);
        model.render(matrixStack, vertexBuilder, light, OverlayTexture.NO_OVERLAY, 1, 1, 1, 1);
    }
}
