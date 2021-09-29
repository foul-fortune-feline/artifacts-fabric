package artifacts.client.render.trinket;

import artifacts.client.render.trinket.model.BeltModel;
import artifacts.client.render.trinket.model.HandsModel;
import artifacts.client.render.trinket.model.HeadModel;
import artifacts.client.render.trinket.model.LegsModel;
import artifacts.client.render.trinket.model.NecklaceModel;
import artifacts.client.render.trinket.model.ScarfModel;
import artifacts.client.render.trinket.renderer.BeltCurioRenderer;
import artifacts.client.render.trinket.renderer.CurioRenderer;
import artifacts.client.render.trinket.renderer.GloveCurioRenderer;
import artifacts.client.render.trinket.renderer.GlowingCurioRenderer;
import artifacts.client.render.trinket.renderer.GlowingGloveCurioRenderer;
import artifacts.client.render.trinket.renderer.SimpleCurioRenderer;
import artifacts.init.Items;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CurioRenderers {

    private static final Map<Item, CurioRenderer> renderers = new HashMap<>();

    public static CurioRenderer getRenderer(Item curio) {
        return renderers.get(curio);
    }

    public static Optional<GloveCurioRenderer> getGloveRenderer(ItemStack stack) {
        if (!stack.isEmpty()) {
            CurioRenderer renderer = getRenderer(stack.getItem());
            if (renderer instanceof GloveCurioRenderer) {
                return Optional.of((GloveCurioRenderer) renderer);
            }
        }
        return Optional.empty();
    }

    public static void setupCurioRenderers() {
        // head
        renderers.put(Items.PLASTIC_DRINKING_HAT, new SimpleCurioRenderer("drinking_hat/plastic_drinking_hat", HeadModel.drinkingHat()));
        renderers.put(Items.NOVELTY_DRINKING_HAT, new SimpleCurioRenderer("drinking_hat/novelty_drinking_hat", HeadModel.drinkingHat()));
        renderers.put(Items.SNORKEL, new SimpleCurioRenderer("snorkel", HeadModel.snorkel()));
        renderers.put(Items.NIGHT_VISION_GOGGLES, new GlowingCurioRenderer("night_vision_goggles", HeadModel.nightVisionGoggles()));
        renderers.put(Items.SUPERSTITIOUS_HAT, new SimpleCurioRenderer("superstitious_hat", HeadModel.superstitiousHat()));
        renderers.put(Items.VILLAGER_HAT, new SimpleCurioRenderer("villager_hat", HeadModel.villagerHat()));

        // necklace
        renderers.put(Items.LUCKY_SCARF, new SimpleCurioRenderer("scarf/lucky_scarf", ScarfModel.scarf(RenderType::entityCutoutNoCull)));
        renderers.put(Items.SCARF_OF_INVISIBILITY, new SimpleCurioRenderer("scarf/scarf_of_invisibility", ScarfModel.scarf(RenderType::entityTranslucent)));
        renderers.put(Items.CROSS_NECKLACE, new SimpleCurioRenderer("cross_necklace", NecklaceModel.crossNecklace()));
        renderers.put(Items.PANIC_NECKLACE, new SimpleCurioRenderer("panic_necklace", NecklaceModel.panicNecklace()));
        renderers.put(Items.SHOCK_PENDANT, new SimpleCurioRenderer("pendant/shock_pendant", NecklaceModel.pendant()));
        renderers.put(Items.FLAME_PENDANT, new SimpleCurioRenderer("pendant/flame_pendant", NecklaceModel.pendant()));
        renderers.put(Items.THORN_PENDANT, new SimpleCurioRenderer("pendant/thorn_pendant", NecklaceModel.pendant()));
        renderers.put(Items.CHARM_OF_SINKING, new SimpleCurioRenderer("charm_of_sinking", NecklaceModel.charmOfSinking()));

        // belt
        renderers.put(Items.CLOUD_IN_A_BOTTLE, new BeltCurioRenderer("cloud_in_a_bottle", BeltModel.cloudInABottle()));
        renderers.put(Items.OBSIDIAN_SKULL, new BeltCurioRenderer("obsidian_skull", BeltModel.obsidianSkull()));
        renderers.put(Items.ANTIDOTE_VESSEL, new BeltCurioRenderer("antidote_vessel", BeltModel.antidoteVessel()));
        renderers.put(Items.UNIVERSAL_ATTRACTOR, new BeltCurioRenderer("universal_attractor", BeltModel.universalAttractor()));
        renderers.put(Items.CRYSTAL_HEART, new BeltCurioRenderer("crystal_heart", BeltModel.crystalHeart()));
        renderers.put(Items.HELIUM_FLAMINGO, new SimpleCurioRenderer("helium_flamingo", BeltModel.heliumFlamingo()));

        // hands
        renderers.put(Items.DIGGING_CLAWS, new GloveCurioRenderer("claws/digging_claws", "claws/digging_claws", HandsModel::claws));
        renderers.put(Items.FERAL_CLAWS, new GloveCurioRenderer("claws/feral_claws", "claws/feral_claws", HandsModel::claws));
        renderers.put(Items.POWER_GLOVE, new GloveCurioRenderer("power_glove"));
        renderers.put(Items.FIRE_GAUNTLET, new GlowingGloveCurioRenderer("fire_gauntlet"));
        renderers.put(Items.POCKET_PISTON, new GloveCurioRenderer("pocket_piston"));
        renderers.put(Items.VAMPIRIC_GLOVE, new GloveCurioRenderer("vampiric_glove"));
        renderers.put(Items.GOLDEN_HOOK, new GloveCurioRenderer("golden_hook", HandsModel::goldenHook));

        // feet
        renderers.put(Items.AQUA_DASHERS, new SimpleCurioRenderer("aqua_dashers", LegsModel.aquaDashers(1.25F)));
        renderers.put(Items.BUNNY_HOPPERS, new SimpleCurioRenderer("bunny_hoppers", LegsModel.bunnyHoppers()));
        renderers.put(Items.KITTY_SLIPPERS, new SimpleCurioRenderer("kitty_slippers", LegsModel.kittySlippers()));
        renderers.put(Items.RUNNING_SHOES, new SimpleCurioRenderer("running_shoes", LegsModel.shoes(0.5F)));
        renderers.put(Items.STEADFAST_SPIKES, new SimpleCurioRenderer("steadfast_spikes", LegsModel.steadfastSpikes()));
        renderers.put(Items.FLIPPERS, new SimpleCurioRenderer("flippers", LegsModel.flippers()));

        // curio
        renderers.put(Items.WHOOPEE_CUSHION, new SimpleCurioRenderer("whoopee_cushion", HeadModel.whoopeeCushion()));
    }
}
