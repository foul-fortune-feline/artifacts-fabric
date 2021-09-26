package artifacts.mixin.mixins.item.heliumflamingo.client;

import artifacts.Artifacts;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Gui.class)
public abstract class GuiMixin {

	@Shadow @Final private Minecraft minecraft;
	@Unique private final ResourceLocation FLAMINGO_ICONS_TEXTURE = Artifacts.id("textures/gui/icons.png");

	@Redirect(method = "renderPlayerHealth", allow = 2, expect = 2, require = 0,
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;blit(Lcom/mojang/blaze3d/vertex/PoseStack;IIIIII)V"),
			slice = @Slice(from = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiling/ProfilerFiller;popPush(Ljava/lang/String;)V", args = {"ldc=air"})))
	private void renderFlamingoAir(Gui inGameHud, PoseStack matrices, int x, int y, int u, int v, int width, int height) {
		// TODO: only when equipped
		matrices.pushPose();
		this.minecraft.getTextureManager().bind(FLAMINGO_ICONS_TEXTURE);
		GuiComponent.blit(matrices, x, y, u - 16, v - 18, width, height, 32, 16);
		matrices.popPose();
	}
}
