package artifacts.common.config;

import artifacts.Artifacts;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = Artifacts.MODID)
@Config.Gui.Background("minecraft:textures/block/mossy_cobblestone.png")
public final class ModConfig extends PartitioningSerializer.GlobalData {
	@ConfigEntry.Category("general")
	@ConfigEntry.Gui.TransitiveObject
	public General general = new General();

	@ConfigEntry.Category("worldgen")
	@ConfigEntry.Gui.TransitiveObject
	public WorldGen worldgen = new WorldGen();

	private ModConfig() {
	}

	@Config(name = "general")
	public static final class General implements ConfigData {
		@SuppressWarnings("unused")
		@ConfigEntry.Gui.Excluded
		public int configVersion = Artifacts.CONFIG_VERSION;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public int everlastingFoodCooldown = 300;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean playExtraHurtSounds = true;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean showFirstPersonGloves = true;
		@ConfigEntry.Gui.Tooltip(count = 2)
		public boolean showTooltips = true;

		private General() {
		}
	}

	@Config(name = "worldgen")
	public static final class WorldGen implements ConfigData {
		@ConfigEntry.Gui.Tooltip(count = 6)
		public float artifactRarity = 1;
		@ConfigEntry.Gui.CollapsibleObject(startExpanded = true)
		public Campsite campsite = new Campsite();

		private WorldGen() {
		}

		public static final class Campsite {
			@ConfigEntry.Gui.RequiresRestart
			@ConfigEntry.Gui.Tooltip(count = 2)
			@ConfigEntry.BoundedDiscrete(max = 10_000, min = 1)
			public int campsiteRarity = 5;

			@ConfigEntry.Gui.Tooltip(count = 2)
			public double mimicChance = 0.3;

			private Campsite() {
			}
		}
	}
}
