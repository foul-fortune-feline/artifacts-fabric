package artifacts.common.init;

import artifacts.client.render.curio.CurioLayers;
import artifacts.client.render.curio.model.ArmsModel;
import artifacts.client.render.curio.model.BeltModel;
import artifacts.client.render.curio.model.HeadModel;
import artifacts.client.render.curio.model.LegsModel;
import artifacts.client.render.curio.model.NecklaceModel;
import artifacts.client.render.curio.model.ScarfModel;
import artifacts.client.render.entity.model.MimicChestLayerModel;
import artifacts.client.render.entity.model.MimicModel;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;

public class ModLayerDefinitions {

    public static void registerAll() {
        register(CurioLayers.MIMIC, MimicModel::createLayer);
        register(CurioLayers.MIMIC_OVERLAY, MimicChestLayerModel::createLayer);

        register(CurioLayers.DRINKING_HAT, layer(HeadModel.createDrinkingHat(), 64, 32));
        register(CurioLayers.SNORKEL, layer(HeadModel.createSnorkel(), 64, 32));
        register(CurioLayers.NIGHT_VISION_GOGGLES, layer(HeadModel.createNightVisionGoggles(), 32, 32));
        register(CurioLayers.SUPERSTITIOUS_HAT, layer(HeadModel.createSuperstitiousHat(), 64, 32));
        register(CurioLayers.VILLAGER_HAT, layer(HeadModel.createVillagerHat(), 32, 32));

        register(CurioLayers.SCARF, layer(ScarfModel.createScarf(), 64, 32));
        register(CurioLayers.CROSS_NECKLACE, layer(NecklaceModel.createCrossNecklace(), 64, 48));
        register(CurioLayers.PANIC_NECKLACE, layer(NecklaceModel.createPanicNecklace(), 64, 48));
        register(CurioLayers.PENDANT, layer(NecklaceModel.createPendant(), 64, 48));
        register(CurioLayers.CHARM_OF_SINKING, layer(NecklaceModel.createCharmOfSinking(), 64, 48));

        register(CurioLayers.CLOUD_IN_A_BOTTLE, layer(BeltModel.createCloudInABottle(), 32, 32));
        register(CurioLayers.OBSIDIAN_SKULL, layer(BeltModel.createObsidianSkull(), 32, 32));
        register(CurioLayers.ANTIDOTE_VESSEL, layer(BeltModel.createAntidoteVessel(), 32, 32));
        register(CurioLayers.UNIVERSAL_ATTRACTOR, layer(BeltModel.createUniversalAttractor(), 32, 32));
        register(CurioLayers.CRYSTAL_HEART, layer(BeltModel.createCrystalHeart(), 32, 32));
        register(CurioLayers.HELIUM_FLAMINGO, layer(BeltModel.createHeliumFlamingo(), 64, 64));

        register(CurioLayers.CLAWS, layer(ArmsModel.createClaws(false), 32, 16));
        register(CurioLayers.SLIM_CLAWS, layer(ArmsModel.createClaws(true), 32, 16));
        register(CurioLayers.GLOVE, layer(ArmsModel.createSleevedArms(false), 32, 32));
        register(CurioLayers.SLIM_GLOVE, layer(ArmsModel.createSleevedArms(true), 32, 32));
        register(CurioLayers.GOLDEN_HOOK, layer(ArmsModel.createGoldenHook(false), 64, 32));
        register(CurioLayers.SLIM_GOLDEN_HOOK, layer(ArmsModel.createGoldenHook(true), 64, 32));

        register(CurioLayers.AQUA_DASHERS, layer(LegsModel.createAquaDashers(), 32, 32));
        register(CurioLayers.BUNNY_HOPPERS, layer(LegsModel.createBunnyHoppers(), 64, 32));
        register(CurioLayers.KITTY_SLIPPERS, layer(LegsModel.createKittySlippers(), 64, 32));
        register(CurioLayers.RUNNING_SHOES, layer(LegsModel.createRunningShoes(), 32, 32));
        register(CurioLayers.STEADFAST_SPIKES, layer(LegsModel.createSteadfastSpikes(), 64, 32));
        register(CurioLayers.FLIPPERS, layer(LegsModel.createFlippers(), 64, 64));

        register(CurioLayers.WHOOPEE_CUSHION, layer(HeadModel.createWhoopeeCushion(), 32, 16));
    }

    private static EntityModelLayerRegistry.TexturedModelDataProvider layer(MeshDefinition mesh, int textureWidth, int textureHeight) {
        return () -> LayerDefinition.create(mesh, textureWidth, textureHeight);
    }

    private static void register(ModelLayerLocation location, EntityModelLayerRegistry.TexturedModelDataProvider provider) {
        EntityModelLayerRegistry.registerModelLayer(location, provider);
    }
}
