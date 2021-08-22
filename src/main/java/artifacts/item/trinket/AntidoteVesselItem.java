package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.AntidoteVesselModel;
import artifacts.mixin.extensions.MobEffectInstanceExtensions;
import artifacts.mixin.mixins.accessors.MobEffectAccessor;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

public class AntidoteVesselItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/antidote_vessel.png");

	@Override
	protected void effectTick(Player player, ItemStack stack) {
		// Reduce duration of all negative status effects to 80
		player.getActiveEffectsMap().forEach((effect, instance) -> {
			if (!effect.isInstantenous() && ((MobEffectAccessor) effect).getCategory() != MobEffectCategory.BENEFICIAL && instance.getDuration() > 80) {
				((MobEffectInstanceExtensions) instance).artifacts$setDuration(80);
			}
		});
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new AntidoteVesselModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.BOTTLE_FILL);
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}
}
