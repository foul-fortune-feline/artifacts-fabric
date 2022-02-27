package artifacts.common.init;

import artifacts.Artifacts;
import artifacts.common.item.EverlastingFoodItem;
import artifacts.common.item.UmbrellaItem;
import artifacts.common.item.curio.CurioItem;
import artifacts.common.item.curio.WhoopeeCushionItem;
import artifacts.common.item.curio.belt.AntidoteVesselItem;
import artifacts.common.item.curio.belt.CloudInABottleItem;
import artifacts.common.item.curio.belt.CrystalHeartItem;
import artifacts.common.item.curio.belt.HeliumFlamingoItem;
import artifacts.common.item.curio.belt.ObsidianSkullItem;
import artifacts.common.item.curio.belt.UniversalAttractorItem;
import artifacts.common.item.curio.feet.AquaDashersItem;
import artifacts.common.item.curio.feet.BunnyHoppersItem;
import artifacts.common.item.curio.feet.KittySlippersItem;
import artifacts.common.item.curio.feet.RunningShoesItem;
import artifacts.common.item.curio.feet.SteadfastSpikesItem;
import artifacts.common.item.curio.hands.DiggingClawsItem;
import artifacts.common.item.curio.hands.FeralClawsItem;
import artifacts.common.item.curio.hands.FireGauntletItem;
import artifacts.common.item.curio.hands.PocketPistonItem;
import artifacts.common.item.curio.hands.PowerGloveItem;
import artifacts.common.item.curio.hands.VampiricGloveItem;
import artifacts.common.item.curio.head.DrinkingHatItem;
import artifacts.common.item.curio.head.NightVisionGogglesItem;
import artifacts.common.item.curio.head.SnorkelItem;
import artifacts.common.item.curio.necklace.CharmOfSinkingItem;
import artifacts.common.item.curio.necklace.CrossNecklaceItem;
import artifacts.common.item.curio.necklace.FlamePendantItem;
import artifacts.common.item.curio.necklace.PanicNecklaceItem;
import artifacts.common.item.curio.necklace.ScarfOfInvisibilityItem;
import artifacts.common.item.curio.necklace.ShockPendantItem;
import artifacts.common.item.curio.necklace.ThornPendantItem;
import net.minecraft.core.Registry;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

public class ModItems {

	public static final Item MIMIC_SPAWN_EGG = register("mimic_spawn_egg", new SpawnEggItem(ModEntityTypes.MIMIC, 0x805113, 0x212121, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());
	public static final Item EVERLASTING_BEEF = register("everlasting_beef", new EverlastingFoodItem(Foods.BEEF));
	public static final Item ETERNAL_STEAK = register("eternal_steak", new EverlastingFoodItem(Foods.COOKED_BEEF));

	// head
	public static final Item PLASTIC_DRINKING_HAT = register("plastic_drinking_hat", new DrinkingHatItem());
	public static final Item NOVELTY_DRINKING_HAT = register("novelty_drinking_hat", new DrinkingHatItem());
	public static final Item SNORKEL = register("snorkel", new SnorkelItem());
	public static final Item NIGHT_VISION_GOGGLES = register("night_vision_goggles", new NightVisionGogglesItem());
	public static final Item VILLAGER_HAT = register("villager_hat", new CurioItem());
	public static final Item SUPERSTITIOUS_HAT = register("superstitious_hat", new CurioItem());

	// necklace
	public static final Item LUCKY_SCARF = register("lucky_scarf", new CurioItem());
	public static final Item SCARF_OF_INVISIBILITY = register("scarf_of_invisibility", new ScarfOfInvisibilityItem());
	public static final Item CROSS_NECKLACE = register("cross_necklace", new CrossNecklaceItem());
	public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());
	public static final Item SHOCK_PENDANT = register("shock_pendant", new ShockPendantItem());
	public static final Item FLAME_PENDANT = register("flame_pendant", new FlamePendantItem());
	public static final Item THORN_PENDANT = register("thorn_pendant", new ThornPendantItem());
	public static final Item CHARM_OF_SINKING = register("charm_of_sinking", new CharmOfSinkingItem());

	// belt
	public static final Item CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle", new CloudInABottleItem());
	public static final Item OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullItem());
	public static final Item ANTIDOTE_VESSEL = register("antidote_vessel", new AntidoteVesselItem());
	public static final Item UNIVERSAL_ATTRACTOR = register("universal_attractor", new UniversalAttractorItem());
	public static final Item CRYSTAL_HEART = register("crystal_heart", new CrystalHeartItem());
	public static final Item HELIUM_FLAMINGO = register("helium_flamingo", new HeliumFlamingoItem());

	// hands
	public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
	public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
	public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
	public static final Item POCKET_PISTON = register("pocket_piston", new PocketPistonItem());
	public static final Item VAMPIRIC_GLOVE = register("vampiric_glove", new VampiricGloveItem());
	public static final Item GOLDEN_HOOK = register("golden_hook", new CurioItem());

	// feet
	public static final Item AQUA_DASHERS = register("aqua_dashers", new AquaDashersItem());
	public static final Item BUNNY_HOPPERS = register("bunny_hoppers", new BunnyHoppersItem());
	public static final Item KITTY_SLIPPERS = register("kitty_slippers", new KittySlippersItem());
	public static final Item RUNNING_SHOES = register("running_shoes", new RunningShoesItem());
	public static final Item STEADFAST_SPIKES = register("steadfast_spikes", new SteadfastSpikesItem());
	public static final Item FLIPPERS = register("flippers", new CurioItem());

	// curio
	public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionItem());

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, Artifacts.id(name), item);
	}
}
