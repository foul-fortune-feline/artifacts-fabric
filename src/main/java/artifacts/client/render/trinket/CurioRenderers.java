package artifacts.client.render.trinket;

import artifacts.client.render.trinket.model.BeltModel;
import artifacts.client.render.trinket.model.HandsModel;
import artifacts.client.render.trinket.model.HeadModel;
import artifacts.client.render.trinket.model.LegsModel;
import artifacts.client.render.trinket.model.NecklaceModel;
import artifacts.client.render.trinket.model.ScarfModel;
import artifacts.client.render.trinket.renderer.BeltCurioRenderer;
import artifacts.client.render.trinket.renderer.GloveCurioRenderer;
import artifacts.client.render.trinket.renderer.GlowingCurioRenderer;
import artifacts.client.render.trinket.renderer.GlowingGloveCurioRenderer;
import artifacts.client.render.trinket.renderer.SimpleCurioRenderer;
import artifacts.init.Items;
import dev.emi.trinkets.api.client.TrinketRendererRegistry;
import net.minecraft.client.renderer.RenderType;

public class CurioRenderers {

    public static void setupCurioRenderers() {
        // head
        TrinketRendererRegistry.registerRenderer(Items.PLASTIC_DRINKING_HAT, new SimpleCurioRenderer("drinking_hat/plastic_drinking_hat", HeadModel.drinkingHat()));
        TrinketRendererRegistry.registerRenderer(Items.NOVELTY_DRINKING_HAT, new SimpleCurioRenderer("drinking_hat/novelty_drinking_hat", HeadModel.drinkingHat()));
        TrinketRendererRegistry.registerRenderer(Items.SNORKEL, new SimpleCurioRenderer("snorkel", HeadModel.snorkel()));
        TrinketRendererRegistry.registerRenderer(Items.NIGHT_VISION_GOGGLES, new GlowingCurioRenderer("night_vision_goggles", HeadModel.nightVisionGoggles()));
        TrinketRendererRegistry.registerRenderer(Items.SUPERSTITIOUS_HAT, new SimpleCurioRenderer("superstitious_hat", HeadModel.superstitiousHat()));
        TrinketRendererRegistry.registerRenderer(Items.VILLAGER_HAT, new SimpleCurioRenderer("villager_hat", HeadModel.villagerHat()));

        // necklace
        TrinketRendererRegistry.registerRenderer(Items.LUCKY_SCARF, new SimpleCurioRenderer("scarf/lucky_scarf", ScarfModel.scarf(RenderType::entityCutoutNoCull)));
        TrinketRendererRegistry.registerRenderer(Items.SCARF_OF_INVISIBILITY, new SimpleCurioRenderer("scarf/scarf_of_invisibility", ScarfModel.scarf(RenderType::entityTranslucent)));
        TrinketRendererRegistry.registerRenderer(Items.CROSS_NECKLACE, new SimpleCurioRenderer("cross_necklace", NecklaceModel.crossNecklace()));
        TrinketRendererRegistry.registerRenderer(Items.PANIC_NECKLACE, new SimpleCurioRenderer("panic_necklace", NecklaceModel.panicNecklace()));
        TrinketRendererRegistry.registerRenderer(Items.SHOCK_PENDANT, new SimpleCurioRenderer("pendant/shock_pendant", NecklaceModel.pendant()));
        TrinketRendererRegistry.registerRenderer(Items.FLAME_PENDANT, new SimpleCurioRenderer("pendant/flame_pendant", NecklaceModel.pendant()));
        TrinketRendererRegistry.registerRenderer(Items.THORN_PENDANT, new SimpleCurioRenderer("pendant/thorn_pendant", NecklaceModel.pendant()));
        TrinketRendererRegistry.registerRenderer(Items.CHARM_OF_SINKING, new SimpleCurioRenderer("charm_of_sinking", NecklaceModel.charmOfSinking()));

        // belt
        TrinketRendererRegistry.registerRenderer(Items.CLOUD_IN_A_BOTTLE, new BeltCurioRenderer("cloud_in_a_bottle", BeltModel.cloudInABottle()));
        TrinketRendererRegistry.registerRenderer(Items.OBSIDIAN_SKULL, new BeltCurioRenderer("obsidian_skull", BeltModel.obsidianSkull()));
        TrinketRendererRegistry.registerRenderer(Items.ANTIDOTE_VESSEL, new BeltCurioRenderer("antidote_vessel", BeltModel.antidoteVessel()));
        TrinketRendererRegistry.registerRenderer(Items.UNIVERSAL_ATTRACTOR, new BeltCurioRenderer("universal_attractor", BeltModel.universalAttractor()));
        TrinketRendererRegistry.registerRenderer(Items.CRYSTAL_HEART, new BeltCurioRenderer("crystal_heart", BeltModel.crystalHeart()));
        TrinketRendererRegistry.registerRenderer(Items.HELIUM_FLAMINGO, new SimpleCurioRenderer("helium_flamingo", BeltModel.heliumFlamingo()));

        // hands
        TrinketRendererRegistry.registerRenderer(Items.DIGGING_CLAWS, new GloveCurioRenderer("claws/digging_claws", "claws/digging_claws", HandsModel::claws));
        TrinketRendererRegistry.registerRenderer(Items.FERAL_CLAWS, new GloveCurioRenderer("claws/feral_claws", "claws/feral_claws", HandsModel::claws));
        TrinketRendererRegistry.registerRenderer(Items.POWER_GLOVE, new GloveCurioRenderer("power_glove"));
        TrinketRendererRegistry.registerRenderer(Items.FIRE_GAUNTLET, new GlowingGloveCurioRenderer("fire_gauntlet"));
        TrinketRendererRegistry.registerRenderer(Items.POCKET_PISTON, new GloveCurioRenderer("pocket_piston"));
        TrinketRendererRegistry.registerRenderer(Items.VAMPIRIC_GLOVE, new GloveCurioRenderer("vampiric_glove"));
        TrinketRendererRegistry.registerRenderer(Items.GOLDEN_HOOK, new GloveCurioRenderer("golden_hook", HandsModel::goldenHook));

        // feet
        TrinketRendererRegistry.registerRenderer(Items.AQUA_DASHERS, new SimpleCurioRenderer("aqua_dashers", LegsModel.aquaDashers(1.25F)));
        TrinketRendererRegistry.registerRenderer(Items.BUNNY_HOPPERS, new SimpleCurioRenderer("bunny_hoppers", LegsModel.bunnyHoppers()));
        TrinketRendererRegistry.registerRenderer(Items.KITTY_SLIPPERS, new SimpleCurioRenderer("kitty_slippers", LegsModel.kittySlippers()));
        TrinketRendererRegistry.registerRenderer(Items.RUNNING_SHOES, new SimpleCurioRenderer("running_shoes", LegsModel.shoes(0.5F)));
        TrinketRendererRegistry.registerRenderer(Items.STEADFAST_SPIKES, new SimpleCurioRenderer("steadfast_spikes", LegsModel.steadfastSpikes()));
        TrinketRendererRegistry.registerRenderer(Items.FLIPPERS, new SimpleCurioRenderer("flippers", LegsModel.flippers()));

        // curio
        TrinketRendererRegistry.registerRenderer(Items.WHOOPEE_CUSHION, new SimpleCurioRenderer("whoopee_cushion", HeadModel.whoopeeCushion()));
    }
}
