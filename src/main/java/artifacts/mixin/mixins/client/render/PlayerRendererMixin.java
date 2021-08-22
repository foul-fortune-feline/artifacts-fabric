package artifacts.mixin.mixins.client.render;

import artifacts.Artifacts;
import artifacts.item.trinket.glove.GloveItem;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.HumanoidArm;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {

	@Inject(method = "renderLeftHand", at = @At("TAIL"))
	private void renderLeftGlove(PoseStack matrices, MultiBufferSource vertexConsumers, int light, AbstractClientPlayer player, CallbackInfo info) {
		GloveItem equippedGlove = getGloveToRenderForHand(player, InteractionHand.OFF_HAND);
		if (equippedGlove != null) {
			equippedGlove.renderArm(matrices, vertexConsumers, light, player, HumanoidArm.LEFT, false);
		}
	}

	@Inject(method = "renderRightHand", at = @At("TAIL"))
	private void renderRightGlove(PoseStack matrices, MultiBufferSource vertexConsumers, int light, AbstractClientPlayer player, CallbackInfo info) {
		GloveItem equippedGlove = getGloveToRenderForHand(player, InteractionHand.MAIN_HAND);
		if (equippedGlove != null) {
			equippedGlove.renderArm(matrices, vertexConsumers, light, player, HumanoidArm.RIGHT, false);
		}
	}

	@Unique
	private static GloveItem getGloveToRenderForHand(Player player, InteractionHand hand) {
		if (!Artifacts.CONFIG.general.showFirstPersonGloves) {
			return null;
		}

		String slotGroup = hand == InteractionHand.MAIN_HAND ? SlotGroups.HAND : SlotGroups.OFFHAND;
		ItemStack stack = TrinketsApi.getTrinketComponent(player).getStack(slotGroup, Slots.GLOVES);

		if (stack.getItem() instanceof GloveItem) {
			return (GloveItem) stack.getItem();
		}

		return null;
	}
}
