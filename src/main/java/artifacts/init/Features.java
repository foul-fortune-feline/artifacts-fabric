package artifacts.init;

import artifacts.Artifacts;
import artifacts.world.CampsiteFeature;
import artifacts.world.InCaveWithChance;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

import java.util.function.Predicate;

// Biome Modifications API is experimental, remove suppress warning when stable
@SuppressWarnings("deprecation")
public class Features {

	public static final Feature<NoneFeatureConfiguration> CAMPSITE_FEATURE = Registry.register(
			Registry.FEATURE,
			Artifacts.id("campsite"),
			new CampsiteFeature()
	);
	public static final FeatureDecorator<ChanceDecoratorConfiguration> DECORATOR = Registry.register(
			Registry.DECORATOR,
			Artifacts.id("incavewithchance"),
			new InCaveWithChance(ChanceDecoratorConfiguration.CODEC)
	);
	public static final ConfiguredFeature<?, ?> CAMPSITE_CONFIGURED_FEATURE = Registry.register(
			BuiltinRegistries.CONFIGURED_FEATURE,
			Artifacts.id("campsite"),
			CAMPSITE_FEATURE.configured(FeatureConfiguration.NONE).decorated(DECORATOR.configured(
					new ChanceDecoratorConfiguration(Artifacts.CONFIG.worldgen.campsite.genChance)
			))
	);

	public static void register() {
		Predicate<BiomeSelectionContext> biomeSelector = BiomeSelectors.foundInOverworld();
		BiomeModifications.addFeature(biomeSelector, GenerationStep.Decoration.UNDERGROUND_STRUCTURES,
				BuiltinRegistries.CONFIGURED_FEATURE.getResourceKey(CAMPSITE_CONFIGURED_FEATURE)
						.orElseThrow(() -> new RuntimeException("Failed to get feature from registry")));
	}

	private Features() {
	}
}
