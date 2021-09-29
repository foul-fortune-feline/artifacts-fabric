package artifacts.world;

import artifacts.Artifacts;
import artifacts.entity.MimicEntity;
import artifacts.init.Entities;
import artifacts.init.LootTables;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BarrelBlock;
import net.minecraft.world.level.block.BlastFurnaceBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CampfireBlock;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.EndRodBlock;
import net.minecraft.world.level.block.FurnaceBlock;
import net.minecraft.world.level.block.Lantern;
import net.minecraft.world.level.block.SmokerBlock;
import net.minecraft.world.level.block.entity.RandomizableContainerBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class CampsiteFeature extends Feature<NoneFeatureConfiguration> {

	public static final BlockStateProvider CRAFTING_STATION_PROVIDER = new WeightedStateProvider()
			.add(Blocks.CRAFTING_TABLE.defaultBlockState(), 5)
			.add(Blocks.FURNACE.defaultBlockState().setValue(FurnaceBlock.LIT, false), 5)
			.add(Blocks.BLAST_FURNACE.defaultBlockState().setValue(BlastFurnaceBlock.LIT, false), 5)
			.add(Blocks.SMOKER.defaultBlockState().setValue(SmokerBlock.LIT, false), 5)
			.add(Blocks.SMITHING_TABLE.defaultBlockState(), 5)
			.add(Blocks.FLETCHING_TABLE.defaultBlockState(), 5)
			.add(Blocks.CARTOGRAPHY_TABLE.defaultBlockState(), 5)
			.add(Blocks.ANVIL.defaultBlockState(), 2)
			.add(Blocks.CHIPPED_ANVIL.defaultBlockState(), 2)
			.add(Blocks.DAMAGED_ANVIL.defaultBlockState(), 1);

	public static final BlockStateProvider DECORATION_PROVIDER = new WeightedStateProvider()
			.add(Blocks.LANTERN.defaultBlockState(), 5)
			.add(Blocks.TORCH.defaultBlockState(), 3)
			.add(Blocks.REDSTONE_TORCH.defaultBlockState(), 3)
			.add(Blocks.CAKE.defaultBlockState(), 1)
			.add(Blocks.BREWING_STAND.defaultBlockState(), 4);

	public static final BlockStateProvider ORE_PROVIDER = new WeightedStateProvider()
			.add(Blocks.IRON_ORE.defaultBlockState(), 6)
			.add(Blocks.REDSTONE_ORE.defaultBlockState(), 6)
			.add(Blocks.LAPIS_ORE.defaultBlockState(), 6)
			.add(Blocks.GOLD_ORE.defaultBlockState(), 4)
			.add(Blocks.DIAMOND_ORE.defaultBlockState(), 2)
			.add(Blocks.EMERALD_ORE.defaultBlockState(), 1);

	public static final BlockStateProvider CAMPFIRE_PROVIDER = new WeightedStateProvider()
			.add(Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, false), 12)
			.add(Blocks.CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, true), 3)
			.add(Blocks.SOUL_CAMPFIRE.defaultBlockState().setValue(CampfireBlock.LIT, true), 1);

	public static final BlockStateProvider LANTERN_PROVIDER = new WeightedStateProvider()
			.add(Blocks.LANTERN.defaultBlockState().setValue(Lantern.HANGING, true), 6)
			.add(Blocks.SOUL_LANTERN.defaultBlockState().setValue(Lantern.HANGING, true), 2)
			.add(Blocks.END_ROD.defaultBlockState().setValue(EndRodBlock.FACING, Direction.DOWN), 1)
			.add(Blocks.SHROOMLIGHT.defaultBlockState(), 1)
			.add(Blocks.GLOWSTONE.defaultBlockState(), 1);

	public CampsiteFeature() {
		super(NoneFeatureConfiguration.CODEC);
	}

	@Override
	public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, NoneFeatureConfiguration featureConfig) {
		List<BlockPos> positions = new ArrayList<>();
		BlockPos.betweenClosedStream(blockPos.offset(-3, 0, -3), blockPos.offset(3, 0, 3)).forEach((pos -> positions.add(pos.immutable())));
		positions.remove(blockPos);
		positions.removeIf(currentPos -> !world.isEmptyBlock(currentPos));
		positions.removeIf(currentPos -> !world.getBlockState(currentPos.below()).getMaterial().blocksMotion());
		if (positions.size() < 12) {
			return false;
		}
		Collections.shuffle(positions);

		if (random.nextInt(100) < Artifacts.CONFIG.worldgen.campsite.oreChance) {
			generateOreVein(world, blockPos.below(), random);
		}

		generateLightSource(world, blockPos, random);

		generateContainer(world, positions.remove(0), random);
		if (random.nextBoolean()) {
			generateContainer(world, positions.remove(0), random);
		}

		generateCraftingStation(world, positions.remove(0), random);
		if (random.nextBoolean()) {
			generateCraftingStation(world, positions.remove(0), random);
		}

		return true;
	}

	public void generateLightSource(WorldGenLevel world, BlockPos pos, Random random) {
		if (random.nextInt(4) != 0) {
			BlockPos currentPos = pos;
			while (currentPos.getY() - pos.getY() < 8 && world.isEmptyBlock(currentPos.above())) {
				currentPos = currentPos.above();
			}
			if (currentPos.getY() - pos.getY() > 2 && !world.isEmptyBlock(currentPos.above())) {
				this.setBlock(world, currentPos, LANTERN_PROVIDER.getState(random, currentPos));
				return;
			}
		}
		this.setBlock(world, pos, CAMPFIRE_PROVIDER.getState(random, pos));
	}

	public void generateContainer(WorldGenLevel world, BlockPos pos, Random random) {
		if (random.nextInt(100) < Artifacts.CONFIG.worldgen.campsite.mimicChance) {
			MimicEntity mimic = Entities.MIMIC.create(world.getLevel());
			if (mimic != null) {
				mimic.setDormant();
				mimic.setPos(pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
				world.addFreshEntity(mimic);
			}
		} else {
			if (random.nextBoolean()) {
				if (random.nextInt(5) == 0) {
					this.setBlock(world, pos, Blocks.TRAPPED_CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
					this.setBlock(world, pos.below(), Blocks.TNT.defaultBlockState());
				} else {
					// TODO: random wooden chest with tag
					this.setBlock(world, pos, Blocks.CHEST.defaultBlockState().setValue(ChestBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(random)));
				}
			} else {
				this.setBlock(world, pos, Blocks.BARREL.defaultBlockState().setValue(BarrelBlock.FACING, Direction.getRandom(random)));
			}
			RandomizableContainerBlockEntity.setLootTable(world, random, pos, LootTables.CAMPSITE_CHEST);
		}
	}

	public void generateCraftingStation(WorldGenLevel world, BlockPos pos, Random random) {
		BlockState state = CRAFTING_STATION_PROVIDER.getState(random, pos);
		this.setBlock(world, pos, state);
		if (random.nextBoolean() && world.isEmptyBlock(pos.above())) {
			generateDecoration(world, pos.above(), random);
		}

	}

	public void generateDecoration(WorldGenLevel world, BlockPos pos, Random random) {
		if (random.nextInt(3) == 0) {
			this.setBlock(world, pos, DECORATION_PROVIDER.getState(random, pos));
		} else {
			this.setBlock(world, pos, BlockTags.FLOWER_POTS.getRandomElement(random).defaultBlockState());
		}
	}

	public void generateOreVein(WorldGenLevel world, BlockPos pos, Random random) {
		BlockState ore = ORE_PROVIDER.getState(random, pos);
		List<BlockPos> positions = new ArrayList<>();
		positions.add(pos);
		for (int i = 4 + random.nextInt(12); i > 0; i--) {
			pos = positions.remove(0);
			this.setBlock(world, pos, ore);
			for (Direction direction : Direction.Plane.HORIZONTAL) {
				if (world.getBlockState(pos.relative(direction)).getMaterial().blocksMotion() && !world.getBlockState(pos.relative(direction).above()).getMaterial().blocksMotion()) {
					positions.add(pos.relative(direction));
				}
			}
			if (positions.size() == 0) {
				return;
			}
		}
	}
}
