package artifacts;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricMaterialBuilder;
import net.fabricmc.fabric.api.tool.attribute.v1.FabricToolTags;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.TieredItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.MaterialColor;

public class ArtifactsTest implements ModInitializer {

	public static final Block LVL2_BLOCK = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.WOOD).burnable().build()).requiresTool().breakByTool(FabricToolTags.AXES, 2).strength(3));
	public static final Block LVL3_BLOCK = new Block(FabricBlockSettings.of(new FabricMaterialBuilder(MaterialColor.WOOD).burnable().build()).requiresTool().breakByTool(FabricToolTags.AXES, 3).strength(3));
	private static final String MOD_ID = "artifacts-testmod";
	public static TieredItem LVL1_PICKAXE = new CustomPickaxeItem(Tiers.STONE, 1, -2.8F, new Item.Properties().tab(Artifacts.ITEM_GROUP));

	@Override
	public void onInitialize() {
		Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "lvl2_block"), LVL2_BLOCK);
		Registry.register(Registry.BLOCK, new ResourceLocation(MOD_ID, "lvl3_block"), LVL3_BLOCK);

		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lvl2_block"), new BlockItem(LVL2_BLOCK, new Item.Properties().tab(Artifacts.ITEM_GROUP)));
		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lvl3_block"), new BlockItem(LVL3_BLOCK, new Item.Properties().tab(Artifacts.ITEM_GROUP)));

		Registry.register(Registry.ITEM, new ResourceLocation(MOD_ID, "lvl1_pickaxe"), LVL1_PICKAXE);
	}

	// PickaxeItem has a protected constructor
	static class CustomPickaxeItem extends PickaxeItem {

		public CustomPickaxeItem(Tiers material, int attackDamage, float attackSpeed, Properties settings) {
			super(material, attackDamage, attackSpeed, settings);
		}
	}
}
