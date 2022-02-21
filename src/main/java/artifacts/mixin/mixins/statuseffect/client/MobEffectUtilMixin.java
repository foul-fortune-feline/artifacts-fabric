package artifacts.mixin.mixins.statuseffect.client;

import artifacts.common.item.curio.TrinketArtifactItem;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(MobEffectUtil.class)
public abstract class MobEffectUtilMixin {

	@Inject(method = "formatDuration", at = @At("HEAD"), cancellable = true)
	private static void setFromArtifactString(MobEffectInstance effect, float multiplier, CallbackInfoReturnable<String> info) {
		LocalPlayer player = Minecraft.getInstance().player;

		if (player != null && effect.isNoCounter()) {
			TrinketsHelper.getAllEquipped(player).forEach(stack -> {
				MobEffectInstance trinketEffect = ((TrinketArtifactItem) stack.getItem()).getPermanentEffect();

				if (trinketEffect != null && trinketEffect.getEffect() == effect.getEffect()) {
					info.setReturnValue(stack.getHoverName().getString());
				}
			});
		}
	}
}
