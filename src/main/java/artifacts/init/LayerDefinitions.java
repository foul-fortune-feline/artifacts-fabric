package artifacts.init;

import artifacts.client.render.entity.model.MimicModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;

public class LayerDefinitions {

    public static void registerAll() {
        register(ModelLayers.MIMIC, MimicModel::createBodyLayer);
    }

    private static void register(ModelLayerLocation location, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayerRegistry.registerModelLayer(location, provider);
    }
}
