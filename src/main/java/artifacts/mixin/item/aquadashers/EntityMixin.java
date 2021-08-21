package artifacts.mixin.item.aquadashers;

import artifacts.common.init.ModItems;
import artifacts.common.init.ModSoundEvents;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntitySize;
import net.minecraft.entity.LivingEntity;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Random;

@Mixin(Entity.class)
public abstract class EntityMixin {

    @Shadow public World world;

    @Shadow @Final protected Random rand;

    @Shadow private EntitySize size;

    @Shadow public abstract double getPosX();

    @Shadow public abstract double getPosY();

    @Shadow public abstract double getPosZ();

    @Shadow public abstract Vector3d getMotion();

    @Inject(method = "playStepSound", at = @At("HEAD"))
    private void playWaterStepSound(BlockPos pos, BlockState blockState, CallbackInfo callbackInfo) {
        if (blockState.getMaterial().isLiquid() && isRunningWithAquaDashers()) {
            ((LivingEntity) (Object) this).playSound(ModSoundEvents.WATER_STEP.get(), 0.15F, 1);
        }
    }

    @Inject(method = "handleRunningEffect", at = @At("HEAD"))
    private void spawnWaterSprintParticle(CallbackInfo callbackInfo) {
        if (isRunningWithAquaDashers()) {
            BlockPos pos = new BlockPos(MathHelper.floor(getPosX()), MathHelper.floor(getPosY() - 0.2), MathHelper.floor(getPosZ()));
            BlockState blockstate = world.getBlockState(pos);
            if (blockstate.getRenderType() == BlockRenderType.INVISIBLE) {
                IParticleData particle;
                Vector3d motion = getMotion().mul(-4, 0, -4);
                if (blockstate.getFluidState().isTagged(FluidTags.LAVA)) {
                    motion = motion.add(0, 1, 0);
                    if (rand.nextInt(3) == 0) {
                        particle = ParticleTypes.LAVA;
                    } else {
                        particle = ParticleTypes.FLAME;
                        motion = motion.mul(0.2, 0.03, 0.2);
                    }
                } else if (!blockstate.getFluidState().isEmpty()) {
                    if (rand.nextInt(3) == 0) {
                        particle = ParticleTypes.BUBBLE;
                    } else {
                        particle = ParticleTypes.SPLASH;
                        motion = motion.add(0, 1.5, 0);
                    }
                } else {
                    return;
                }
                world.addParticle(particle, getPosX() + (rand.nextDouble() - 0.5) * size.width, getPosY(), getPosZ() + (rand.nextDouble() - 0.5) * size.width, motion.x, motion.y, motion.z);
            }
        }
    }

    @Unique
    private boolean isRunningWithAquaDashers() {
        // noinspection ConstantConditions
        return (Object) this instanceof LivingEntity && ModItems.AQUA_DASHERS.get().isSprinting((LivingEntity) (Object) this);
    }
}
