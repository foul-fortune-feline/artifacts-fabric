package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrossNecklaceModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public class CrossNecklaceItem extends TrinketArtifactItem {

	public static final double HURT_RESISTANCE_MULTIPLIER = 3; // Hurt invuln is multiplied by this factor
	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/cross_necklace.png");

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new CrossNecklaceModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.CHEST) && slot.equals(Slots.NECKLACE);
	}

	@Override
	public SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
