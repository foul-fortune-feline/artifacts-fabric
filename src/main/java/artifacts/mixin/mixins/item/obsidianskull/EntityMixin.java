package artifacts.mixin.mixins.item.obsidianskull;

import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

	@Inject(method = "setSecondsOnFire", at = @At("HEAD"))
	private void giveFireResistance(int seconds, CallbackInfo info) {
		//noinspection ConstantConditions
		if ((Entity) (Object) this instanceof Player) {
			Player player = (Player) (Object) this;

			if (TrinketsHelper.isEquipped(Items.OBSIDIAN_SKULL, player) && !player.getCooldowns().isOnCooldown(Items.OBSIDIAN_SKULL)) {
				player.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 600, 0, false, true));
				player.getCooldowns().addCooldown(Items.OBSIDIAN_SKULL, 1200);
			}
		}
	}
}
