package artifacts.toolhandler;

import artifacts.init.Items;
import artifacts.mixin.mixins.accessors.ToolManagerImplEntryImplAccessor;
import artifacts.trinkets.TrinketsHelper;
import net.fabricmc.fabric.impl.tool.attribute.ToolManagerImpl;
import net.minecraft.tags.Tag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.List;

/**
 * For mining vanilla/modded blocks with non-tools
 */
@SuppressWarnings("UnstableApiUsage")
public class DiggingClawsToolHandler implements ToolManagerImpl.ToolHandler {

	// Because these are mining level 1, used to check no/vanilla tools against vanilla blocks
	private static final int NEW_BASE_MINING_LEVEL = 1;
	private static final List<Item> VANILLA_ITEMS = Arrays.asList(
			net.minecraft.world.item.Items.STONE_AXE,
			net.minecraft.world.item.Items.STONE_HOE,
			net.minecraft.world.item.Items.STONE_PICKAXE,
			net.minecraft.world.item.Items.STONE_SHOVEL,
			net.minecraft.world.item.Items.STONE_SWORD,
			net.minecraft.world.item.Items.SHEARS
	);

	@Override
	public @NotNull InteractionResult isEffectiveOn(Tag<Item> tag, BlockState state, ItemStack stack, LivingEntity user) {
		if (!TrinketsHelper.isEquipped(Items.DIGGING_CLAWS, user)) {
			return InteractionResult.PASS;
		}

		// Modded block
		ToolManagerImpl.Entry entry = ToolManagerImpl.entryNullable(state.getBlock());
		if (entry != null) {
			// Problem: modded blocks can have different mining levels for different tools
			// Solution: get the lowest miningLevel
			int requiredMiningLevel = Arrays.stream(((ToolManagerImplEntryImplAccessor) entry).getTagLevels()).min().orElse(-1);
			return requiredMiningLevel >= 0 && requiredMiningLevel <= DiggingClawsToolHandler.NEW_BASE_MINING_LEVEL ? InteractionResult.SUCCESS : InteractionResult.PASS;
		}

		// Vanilla block
		for (Item tool : VANILLA_ITEMS) {
			// Success if any of the stone tools pass
			if (new ItemStack(tool).isCorrectToolForDrops(state)) {
				return InteractionResult.SUCCESS;
			}
		}

		return InteractionResult.PASS;
	}
}
