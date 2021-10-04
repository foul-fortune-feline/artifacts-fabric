package artifacts.init;

import artifacts.Artifacts;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;

public class ModelLayers {

    public static final ModelLayerLocation MIMIC = createLocation("mimic");
    public static final ModelLayerLocation DRINKING_HAT = createLocation("drinking_hat");
    public static final ModelLayerLocation SNORKEL = createLocation("snorkel");
    public static final ModelLayerLocation NIGHT_VISION_GOGGLES = createLocation("night_vision_goggles");
    public static final ModelLayerLocation SUPERSTITIOUS_HAT = createLocation("superstitious_hat");
    public static final ModelLayerLocation VILLAGER_HAT = createLocation("villager_hat");
    public static final ModelLayerLocation GLOVE = createLocation("glove");
    public static final ModelLayerLocation CLAWS = createLocation("claws");
    public static final ModelLayerLocation GOLDEN_HOOK = createLocation("golden_hook");
    public static final ModelLayerLocation WHOOPEE_CUSHION = createLocation("golden_hook");

    private static ModelLayerLocation createLocation(String model) {
        return createLocation(model, "main");
    }

    private static ModelLayerLocation createLocation(String model, String layer) {
        return new ModelLayerLocation(new ResourceLocation(Artifacts.MOD_ID, model), layer);
    }
}
