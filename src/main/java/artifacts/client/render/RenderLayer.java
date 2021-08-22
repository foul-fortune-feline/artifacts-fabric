package artifacts.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.resources.ResourceLocation;
import org.lwjgl.opengl.GL11;


@Environment(EnvType.CLIENT)
public abstract class RenderLayer extends net.minecraft.client.renderer.RenderType {

	private RenderLayer(String name, VertexFormat fmt, int glMode, int size, boolean doCrumbling, boolean depthSorting, Runnable onEnable, Runnable onDisable) {
		super(name, fmt, glMode, size, doCrumbling, depthSorting, onEnable, onDisable);
		throw new IllegalStateException();
	}

	public static net.minecraft.client.renderer.RenderType unlit(ResourceLocation textureLocation) {
		CompositeState renderState = CompositeState.builder()
				.setTextureState(new TextureStateShard(textureLocation, false, false))
				.setTransparencyState(NO_TRANSPARENCY)
				.setAlphaState(DEFAULT_ALPHA)
				.setCullState(NO_CULL)
				.setLightmapState(LIGHTMAP)
				.setOverlayState(OVERLAY)
				.createCompositeState(true);
		return create("artifacts_entity_unlit", DefaultVertexFormat.NEW_ENTITY, GL11.GL_QUADS, 256, true, false, renderState);
	}
}
