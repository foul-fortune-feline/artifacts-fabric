package artifacts.mixin.mixins.item.goldenhook;

import artifacts.common.init.ModComponents;
import artifacts.common.init.ModItems;
import artifacts.common.trinkets.TrinketsHelper;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin extends Entity {

	@Shadow @Nullable protected Player lastHurtByPlayer;

	public LivingEntityMixin(EntityType<?> type, Level world) {
		super(type, world);
	}

	@ModifyArg(method = "dropExperience", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/ExperienceOrb;award(Lnet/minecraft/server/level/ServerLevel;Lnet/minecraft/world/phys/Vec3;I)V"))
	private int modifyXp(int originalXp) {
		if (!TrinketsHelper.isEquipped(ModItems.GOLDEN_HOOK, this.lastHurtByPlayer)) {
			return originalXp;
		}

		double killRatio = ModComponents.ENTITY_KILL_TRACKER.maybeGet(this.lastHurtByPlayer)
				.map(comp -> comp.getKillRatio(this.getType()))
				.orElse(0D);

		// bonus decreases linearly in relation to the ratio kills of the same type in the list of tracked kills
		// no bonus if more than half of the tracked kills are of the same type
		// maximum bonus is 5 * original XP (give or take a few rounding errors)
		double multiplier = 5 * Math.max(0, 2 * ((1 - killRatio) - 1 / 2D));
		int experienceBonus = (int) (multiplier * Math.min(10, originalXp));

		return originalXp + experienceBonus;
	}

	@Inject(method = "die", at = @At("HEAD"))
	private void addToKillTracker(DamageSource source, CallbackInfo info) {
		if (source.getEntity() instanceof Player player) {
			ModComponents.ENTITY_KILL_TRACKER.maybeGet(player).ifPresent(comp -> comp.addKilledEntityType(this.getType()));
		}
	}
}
