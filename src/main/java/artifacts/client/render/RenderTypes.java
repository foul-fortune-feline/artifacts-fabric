package artifacts.client.render;

import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.VertexFormat;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;


@Environment(EnvType.CLIENT)
public abstract class RenderTypes extends RenderType {

	public RenderTypes(String string, VertexFormat vertexFormat, VertexFormat.Mode mode, int i, boolean bl, boolean bl2, Runnable runnable, Runnable runnable2) {
		super(string, vertexFormat, mode, i, bl, bl2, runnable, runnable2);
		throw new IllegalStateException("This class must not be instantiated");
	}

	// See ForgeRenderTypes#getUnlitTranslucent
	public static RenderType unlit(ResourceLocation textureLocation) {
		CompositeState renderState = CompositeState.builder()
				// FIXME CRASH: .setShaderState(RENDERTYPE_ENTITY_TRANSLUCENT_UNLIT_SHADER)
				.setTextureState(new TextureStateShard(textureLocation, false, false))
				.setTransparencyState(TRANSLUCENT_TRANSPARENCY)
				.setCullState(NO_CULL)
				.setLightmapState(LIGHTMAP)
				.setOverlayState(OVERLAY)
				.createCompositeState(true);
		return create("artifacts_entity_unlit", DefaultVertexFormat.NEW_ENTITY, VertexFormat.Mode.QUADS, 256, true, true, renderState);
	}
}
