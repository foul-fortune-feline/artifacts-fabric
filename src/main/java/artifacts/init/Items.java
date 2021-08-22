package artifacts.init;

import artifacts.Artifacts;
import artifacts.item.EverlastingFoodItem;
import artifacts.item.UmbrellaItem;
import artifacts.item.trinket.*;
import artifacts.item.trinket.glove.*;
import artifacts.item.trinket.pendant.FlamePendantItem;
import artifacts.item.trinket.pendant.ShockPendantItem;
import artifacts.item.trinket.pendant.ThornPendantItem;
import net.minecraft.core.Registry;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.SpawnEggItem;

@SuppressWarnings("unused")
public class Items {

	// Misc
	public static final Item MIMIC_SPAWN_EGG = register("mimic_spawn_egg", new SpawnEggItem(Entities.MIMIC, 0x805113, 0x212121, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
	public static final Item UMBRELLA = register("umbrella", new UmbrellaItem());
	public static final Item WHOOPEE_CUSHION = register("whoopee_cushion", new WhoopeeCushionItem());

	// Food
	public static final Item EVERLASTING_BEEF = register("everlasting_beef", new EverlastingFoodItem(Foods.BEEF));
	public static final Item ETERNAL_STEAK = register("eternal_steak", new EverlastingFoodItem(Foods.COOKED_BEEF));

	// Head
	public static final Item PLASTIC_DRINKING_HAT = register("plastic_drinking_hat", new DrinkingHatItem(Artifacts.id("textures/entity/trinket/plastic_drinking_hat.png")));
	public static final Item NOVELTY_DRINKING_HAT = register("novelty_drinking_hat", new DrinkingHatItem(Artifacts.id("textures/entity/trinket/novelty_drinking_hat.png")));
	public static final Item SNORKEL = register("snorkel", new SnorkelItem());
	public static final Item NIGHT_VISION_GOGGLES = register("night_vision_goggles", new NightVisionGogglesItem());
	public static final Item VILLAGER_HAT = register("villager_hat", new VillagerHatItem());
	public static final Item SUPERSTITIOUS_HAT = register("superstitious_hat", new SuperstitiousHatItem());

	// Necklace
	public static final Item LUCKY_SCARF = register("lucky_scarf", new LuckyScarfItem());
	public static final Item SCARF_OF_INVISIBILITY = register("scarf_of_invisibility", new ScarfOfInvisibilityItem());
	public static final Item CROSS_NECKLACE = register("cross_necklace", new CrossNecklaceItem());
	public static final Item PANIC_NECKLACE = register("panic_necklace", new PanicNecklaceItem());
	public static final Item SHOCK_PENDANT = register("shock_pendant", new ShockPendantItem());
	public static final Item FLAME_PENDANT = register("flame_pendant", new FlamePendantItem());
	public static final Item THORN_PENDANT = register("thorn_pendant", new ThornPendantItem());

	// Belt
	public static final Item OBSIDIAN_SKULL = register("obsidian_skull", new ObsidianSkullItem());
	public static final Item ANTIDOTE_VESSEL = register("antidote_vessel", new AntidoteVesselItem());
	public static final Item UNIVERSAL_ATTRACTOR = register("universal_attractor", new UniversalAttractorItem());
	public static final Item CRYSTAL_HEART = register("crystal_heart", new CrystalHeartItem());
	public static final Item CLOUD_IN_A_BOTTLE = register("cloud_in_a_bottle", new CloudInABottleItem());
	public static final Item HELIUM_FLAMINGO = register("helium_flamingo", new HeliumFlamingoItem());

	// Hands
	public static final Item DIGGING_CLAWS = register("digging_claws", new DiggingClawsItem());
	public static final Item FERAL_CLAWS = register("feral_claws", new FeralClawsItem());
	public static final Item POWER_GLOVE = register("power_glove", new PowerGloveItem());
	public static final Item FIRE_GAUNTLET = register("fire_gauntlet", new FireGauntletItem());
	public static final Item POCKET_PISTON = register("pocket_piston", new PocketPistonItem());
	public static final Item VAMPIRIC_GLOVE = register("vampiric_glove", new VampiricGloveItem());
	public static final Item GOLDEN_HOOK = register("golden_hook", new GoldenHookItem());

	// Feet
	public static final Item AQUA_DASHERS = register("aqua_dashers", new AquaDashersItem());
	public static final Item BUNNY_HOPPERS = register("bunny_hoppers", new BunnyHoppersItem());
	public static final Item KITTY_SLIPPERS = register("kitty_slippers", new KittySlippersItem());
	public static final Item RUNNING_SHOES = register("running_shoes", new RunningShoesItem());
	public static final Item STEADFAST_SPIKES = register("steadfast_spikes", new SteadfastSpikesItem());
	public static final Item FLIPPERS = register("flippers", new FlippersItem());

	private static Item register(String name, Item item) {
		return Registry.register(Registry.ITEM, Artifacts.id(name), item);
	}

	private Items() {
	}
}
