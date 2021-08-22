package artifacts.item.trinket.pendant;

import artifacts.client.render.model.trinket.PendantModel;
import artifacts.events.LivingEntityAttackedCallback;
import artifacts.item.trinket.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public abstract class PendantItem extends TrinketArtifactItem {

	private final ResourceLocation texture;

	public PendantItem(ResourceLocation texture, LivingEntityAttackedCallback callback) {
		this.texture = texture;
		LivingEntityAttackedCallback.EVENT.register(callback);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new PendantModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return texture;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}
}
