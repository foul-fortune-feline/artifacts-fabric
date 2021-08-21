package artifacts.mixin.item.villagerhat;

import artifacts.common.init.ModItems;
import net.minecraft.entity.merchant.villager.AbstractVillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(AbstractVillagerEntity.class)
public abstract class AbstractVillagerEntityMixin {

    @Shadow
    @Nullable
    private PlayerEntity customer;

    @Inject(method = "onTrade", at = @At("HEAD"))
    private void damageVillagerHat(CallbackInfo callbackInfo) {
        if (ModItems.VILLAGER_HAT.get().isEquippedBy(customer)) {
            ModItems.VILLAGER_HAT.get().damageEquippedStacks(customer);
        }
    }
}
