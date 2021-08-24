package artifacts.item.curio;

import artifacts.Artifacts;
import artifacts.client.render.trinket.CurioRenderers;
import artifacts.events.PlayHurtSoundCallback;
import artifacts.init.Components;
import artifacts.item.ArtifactItem;
import artifacts.trinkets.TrinketsHelper;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.mojang.blaze3d.vertex.PoseStack;
import dev.emi.trinkets.api.Trinket;
import dev.emi.trinkets.api.TrinketItem;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import java.util.List;
import java.util.UUID;

public abstract class TrinketArtifactItem extends ArtifactItem implements Trinket {

	public TrinketArtifactItem() {
		DispenserBlock.registerBehavior(this, TrinketItem.TRINKET_DISPENSER_BEHAVIOR);
		PlayHurtSoundCallback.EVENT.register(this::playExtraHurtSound);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player user, InteractionHand hand) {
		// Toggle artifact effects when sneak right-clicking
		if (user.isShiftKeyDown()) {
			ItemStack stack = user.getItemInHand(hand);
			Components.ARTIFACT_ENABLED.get(stack).invert();

			if (world.isClientSide()) {
				// Show enabled/disabled message above hotbar
				ChatFormatting enabledColor = effectsEnabled(stack) ? ChatFormatting.GREEN : ChatFormatting.RED;
				Component enabledText = new TranslatableComponent(getEffectsEnabledLanguageKey(stack)).withStyle(enabledColor);
				Minecraft.getInstance().gui.setOverlayMessage(enabledText, false);
			}

			return InteractionResultHolder.success(stack);
		}

		InteractionResultHolder<ItemStack> actionResult = Trinket.equipTrinket(user, hand);

		// Play right click equip sound
		if (actionResult.getResult().consumesAction()) {
			SoundInfo sound = this.getEquipSound();
			user.playSound(sound.getSoundEvent(), sound.getVolume(), sound.getPitch());
		}

		return actionResult;
	}

	@Override
	public final void tick(Player player, ItemStack stack) {
		if (effectsEnabled(stack)) {
			effectTick(player, stack);
		}
	}

	protected void effectTick(Player player, ItemStack stack) {
	}

	@Override
	public final Multimap<Attribute, AttributeModifier> getTrinketModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		if (effectsEnabled(stack)) {
			return this.applyModifiers(group, slot, uuid, stack);
		}
		return HashMultimap.create();
	}

	protected Multimap<Attribute, AttributeModifier> applyModifiers(String group, String slot, UUID uuid, ItemStack stack) {
		return HashMultimap.create();
	}

	@Override
	public void appendHoverText(ItemStack stack, Level world, List<Component> tooltip, TooltipFlag flags) {
		super.appendHoverText(stack, world, tooltip, flags);
		MutableComponent enabled = new TranslatableComponent(getEffectsEnabledLanguageKey(stack)).withStyle(ChatFormatting.GOLD);
		Component togglekeybind = new TranslatableComponent("artifacts.trinket.togglekeybind").withStyle(ChatFormatting.GRAY);
		tooltip.add(enabled.append(" ").append(togglekeybind));
	}

	/**
	 * @return The {@link SoundEvent} to play when the artifact is right-click equipped
	 */
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.ARMOR_EQUIP_GENERIC);
	}

	/**
	 * @return An extra {@link SoundEvent} to play when an entity wearing this artifact is hurt
	 */
	protected SoundEvent getExtraHurtSound() {
		return null;
	}

	/**
	 * Used to give a Trinket a permanent status effect while wearing it.
	 * The StatusEffectInstance is applied every 15 ticks so a duration greater than that is required.
	 *
	 * @return The {@link MobEffectInstance} to be applied while wearing this artifact
	 */
	public MobEffectInstance getPermanentEffect() {
		return null;
	}

	@Environment(EnvType.CLIENT)
	public void render(String slot, PoseStack matrices, MultiBufferSource buffer, int light, AbstractClientPlayer player, float limbAngle,
					   float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, ItemStack stack) {
		// TODO: support slot index (used by belt renderer)
		CurioRenderers.getRenderer(this).render(slot, 0, matrices, buffer, light, player, limbAngle,
				limbDistance, tickDelta, animationProgress, headYaw, headPitch, stack);
	}

	private void playExtraHurtSound(LivingEntity entity, float volume, float pitch) {
		if (Artifacts.CONFIG.general.playExtraHurtSounds) {
			SoundEvent hurtSound = getExtraHurtSound();

			if (hurtSound != null && TrinketsHelper.isEquipped(this, entity, true)) {
				entity.playSound(hurtSound, volume, pitch);
			}
		}
	}

	private static boolean effectsEnabled(ItemStack stack) {
		return Components.ARTIFACT_ENABLED.get(stack).get();
	}

	public static void addModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (!instance.hasModifier(modifier)) {
			instance.addTransientModifier(modifier);
		}
	}

	public static void removeModifier(AttributeInstance instance, AttributeModifier modifier) {
		if (instance.hasModifier(modifier)) {
			instance.removeModifier(modifier);
		}
	}

	private static String getEffectsEnabledLanguageKey(ItemStack stack) {
		return effectsEnabled(stack) ? "artifacts.trinket.effectsenabled" : "artifacts.trinket.effectsdisabled";
	}

	// From Curios
	protected static final class SoundInfo {
		final SoundEvent soundEvent;
		final float volume;
		final float pitch;

		public SoundInfo(SoundEvent soundEvent) {
			this(soundEvent, 1f, 1f);
		}

		public SoundInfo(SoundEvent soundEvent, float volume, float pitch) {
			this.soundEvent = soundEvent;
			this.volume = volume;
			this.pitch = pitch;
		}

		public SoundEvent getSoundEvent() {
			return this.soundEvent;
		}

		public float getVolume() {
			return this.volume;
		}

		public float getPitch() {
			return this.pitch;
		}
	}
}
