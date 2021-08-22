package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CrystalHeartModel;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import java.util.UUID;

public class CrystalHeartItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/crystal_heart.png");

	private static final AttributeModifier HEALTH_BONUS = new AttributeModifier(UUID.fromString("99fa0537-90b9-481a-bc76-4650987faba3"),
			"artifacts:crystal_heart_health_bonus", 10, AttributeModifier.Operation.ADDITION);

	@Override
	public void onEquip(Player player, ItemStack stack) {
		if (!player.level.isClientSide()) {
			AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
			if (health != null && !health.hasModifier(HEALTH_BONUS)) {
				health.addPermanentModifier(HEALTH_BONUS);
			}
		}

		super.onEquip(player, stack);
	}

	@Override
	public void onUnequip(Player player, ItemStack stack) {
		if (!player.level.isClientSide()) {
			AttributeInstance health = player.getAttribute(Attributes.MAX_HEALTH);
			if (health != null && health.hasModifier(HEALTH_BONUS)) {
				health.removeModifier(HEALTH_BONUS);
				if (player.getHealth() > player.getMaxHealth()) {
					player.setHealth(player.getMaxHealth());
				}
			}
		}
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new CrystalHeartModel();
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	public SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}
}
