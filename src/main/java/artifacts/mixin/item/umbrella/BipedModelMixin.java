package artifacts.mixin.item.umbrella;

import artifacts.common.item.UmbrellaItem;
import net.minecraft.client.renderer.entity.model.BipedModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.Hand;
import net.minecraft.util.HandSide;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BipedModel.class)
public abstract class BipedModelMixin<T extends LivingEntity> {

    @Shadow
    public ModelRenderer bipedRightArm;

    @Shadow
    public ModelRenderer bipedLeftArm;

    // see https://github.com/florensie/artifacts-fabric/blob/HEAD/src/main/java/artifacts/mixin/mixins/item/umbrella/client/BipedEntityModelMixin.java
    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "setRotationAngles", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/LivingEntity;getPrimaryHand()Lnet/minecraft/util/HandSide;"))
    private void reduceHandSwing(T entity, float f, float g, float h, float i, float j, CallbackInfo info) {
        boolean isHoldingOffHand = UmbrellaItem.isHoldingUmbrellaUpright(entity, Hand.OFF_HAND);
        boolean isHoldingMainHand = UmbrellaItem.isHoldingUmbrellaUpright(entity, Hand.MAIN_HAND);
        boolean isRightHanded = entity.getPrimaryHand() == HandSide.RIGHT;

        if ((isHoldingMainHand && isRightHanded) || (isHoldingOffHand && !isRightHanded)) {
            this.bipedRightArm.rotateAngleX /= 8;
        }
        if ((isHoldingMainHand && !isRightHanded) || (isHoldingOffHand && isRightHanded)) {
            this.bipedLeftArm.rotateAngleX /= 8;
        }
    }
}
