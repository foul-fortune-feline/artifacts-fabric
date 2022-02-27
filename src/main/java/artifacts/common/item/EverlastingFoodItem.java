package artifacts.common.item;

import artifacts.Artifacts;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class EverlastingFoodItem extends ArtifactItem {

    public EverlastingFoodItem(FoodProperties food) {
        super(new Item.Properties().food(food));
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity entity) {
		if (!world.isClientSide && entity instanceof Player) {
			int cooldown = Artifacts.CONFIG.general.everlastingFoodCooldown;
			((Player) entity).getCooldowns().addCooldown(this, cooldown);
		}

		stack.hurtAndBreak(1, entity, damager -> {
		});

		// Stack decrement is cancelled in LivingEntity.eatFood() mixin
		return super.finishUsingItem(stack, world, entity);
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 24;
	}
}
