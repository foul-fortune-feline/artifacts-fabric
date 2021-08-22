package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.HeliumFlamingoModel;
import artifacts.components.SwimAbilityComponent;
import artifacts.init.Components;
import artifacts.init.SoundEvents;
import be.florens.expandability.api.fabric.PlayerSwimCallback;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.locale.Language;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;


public class HeliumFlamingoItem extends TrinketArtifactItem {

	public static final ResourceLocation C2S_AIR_SWIMMING_ID = Artifacts.id("c2s_air_swimming");
	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/helium_flamingo.png");

	public HeliumFlamingoItem() {
		super();
		PlayerSwimCallback.EVENT.register(HeliumFlamingoItem::onPlayerSwim);
		ServerPlayNetworking.registerGlobalReceiver(C2S_AIR_SWIMMING_ID, HeliumFlamingoItem::handleAirSwimmingPacket);
	}

	private static InteractionResult onPlayerSwim(Player player) {
		SwimAbilityComponent swimAbilities = Components.SWIM_ABILITIES.get(player);

		if (swimAbilities.isSwimming()) {
			return InteractionResult.SUCCESS;
		}

		return InteractionResult.PASS;
	}

	private static void handleAirSwimmingPacket(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		boolean shouldSwim = buf.readBoolean();
		server.execute(() -> Components.SWIM_ABILITIES.get(player).setSwimming(shouldSwim));
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new HeliumFlamingoModel();
	}

	@Override
	protected ResourceLocation getTexture() {
		return TEXTURE;
	}

	@Override
	public boolean canWearInSlot(String group, String slot) {
		return group.equals(SlotGroups.LEGS) && slot.equals(Slots.BELT);
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.POP, 1f, 0.7f);
	}

	@Override
	protected Object[] getTooltipDescriptionArguments() {
		String translationKey = Minecraft.getInstance().options.keySprint.saveString();
		return new Object[] {Language.getInstance().getOrDefault(translationKey)};
	}

/*	public static boolean isFlying(LivingEntity entity) {
		return TrinketsHelper.isEquipped(Items.HELIUM_FLAMINGO, entity)
				&& entity instanceof PlayerEntity
				&& Components.SWIM_ABILITIES.get((PlayerEntity) entity).isAirSwimming();
	}*/
}
