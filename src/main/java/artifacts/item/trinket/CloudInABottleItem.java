package artifacts.item.trinket;

import artifacts.Artifacts;
import artifacts.client.render.model.trinket.CloudInABottleModel;
import artifacts.mixin.extensions.LivingEntityExtensions;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.ServerGamePacketListenerImpl;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.LivingEntity;

public class CloudInABottleItem extends TrinketArtifactItem {

	public static final ResourceLocation C2S_DOUBLE_JUMPED_ID = Artifacts.id("c2s_double_jumped");
	private static final ResourceLocation TEXTURE = Artifacts.id("textures/entity/trinket/cloud_in_a_bottle.png");

	public CloudInABottleItem() {
		ServerPlayNetworking.registerGlobalReceiver(C2S_DOUBLE_JUMPED_ID, CloudInABottleItem::handleDoubleJumpPacket);
	}

	private static void handleDoubleJumpPacket(MinecraftServer server, ServerPlayer player, ServerGamePacketListenerImpl handler, FriendlyByteBuf buf, PacketSender responseSender) {
		server.execute(() -> {
			((LivingEntityExtensions) player).artifacts$doubleJump();

			// Spawn particles
			for (int i = 0; i < 20; ++i) {
				double motionX = player.getRandom().nextGaussian() * 0.02;
				double motionY = player.getRandom().nextGaussian() * 0.02 + 0.20;
				double motionZ = player.getRandom().nextGaussian() * 0.02;
				player.getLevel().sendParticles(ParticleTypes.POOF, player.getX(), player.getY(), player.getZ(), 1, motionX, motionY, motionZ, 0.15);
			}
		});
	}

	@Override
	protected SoundInfo getEquipSound() {
		return new SoundInfo(SoundEvents.BOTTLE_FILL_DRAGONBREATH);
	}

	@Override
	@Environment(EnvType.CLIENT)
	protected HumanoidModel<LivingEntity> createModel() {
		return new CloudInABottleModel();
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
}
