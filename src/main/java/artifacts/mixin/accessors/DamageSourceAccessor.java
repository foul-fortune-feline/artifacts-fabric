package artifacts.mixin.accessors;

import net.minecraft.world.damagesource.DamageSource;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(DamageSource.class)
public interface DamageSourceAccessor {

	DamageSource callSetIsFire();
}
