package artifacts.world;

import artifacts.Artifacts;
import com.mojang.serialization.Codec;
import java.util.Random;
import java.util.stream.Stream;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.levelgen.placement.ChanceDecoratorConfiguration;
import net.minecraft.world.level.levelgen.placement.DecorationContext;
import net.minecraft.world.level.levelgen.placement.FeatureDecorator;

public class InCaveWithChance extends FeatureDecorator<ChanceDecoratorConfiguration> {

	public InCaveWithChance(Codec<ChanceDecoratorConfiguration> codec) {
		super(codec);
	}

	@Override
	public Stream<BlockPos> getPositions(DecorationContext context, Random random, ChanceDecoratorConfiguration config, BlockPos pos) {
		if (random.nextInt(100) < config.chance) {
			int x = random.nextInt(16);
			int z = random.nextInt(16);
			pos = new BlockPos(pos.getX() + x, Artifacts.CONFIG.worldgen.campsite.minY, pos.getZ() + z);
			while (pos.getY() <= Artifacts.CONFIG.worldgen.campsite.maxY) {
				if (context.getBlockState(pos).isAir() && context.getBlockState(pos.below()).getMaterial().blocksMotion()) {
					return Stream.of(pos);
				}
				pos = pos.above();
			}
		}
		return Stream.empty();
	}
}
