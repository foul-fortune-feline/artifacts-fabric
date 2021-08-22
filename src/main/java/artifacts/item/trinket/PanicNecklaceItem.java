package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.PanicNecklaceModel;
import artifacts.events.LivingEntityHurtCallback;
import artifacts.init.Items;
import artifacts.trinkets.TrinketsHelper;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;

public class PanicNecklaceItem extends TrinketArtifactItem {

	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/panic_necklace.png");

	public PanicNecklaceItem() {
		LivingEntityHurtCallback.EVENT.register(PanicNecklaceItem::applyEffects);
	}

	private static void applyEffects(LivingEntity user, DamageSource source, float amount) {
		if (!user.level.isClientSide && amount >= 1 && TrinketsHelper.isEquipped(Items.PANIC_NECKLACE, user)) {
			user.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 160, 0, false, false));
		}
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_DIAMOND);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new PanicNecklaceModel();
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
}
