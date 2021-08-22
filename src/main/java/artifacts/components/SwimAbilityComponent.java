package artifacts.components;

import artifacts.item.trinket.HeliumFlamingoItem;
import dev.onyxstudios.cca.api.v3.component.Component;
import dev.onyxstudios.cca.api.v3.entity.PlayerComponent;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;

@SuppressWarnings("UnstableApiUsage")
public class SwimAbilityComponent implements PlayerComponent<Component> {

	private boolean shouldSwim;
	private boolean shouldSink;
	private boolean hasTouchedWater;

	public boolean isSwimming() {
		return shouldSwim;
	}

	public boolean isSinking() {
		return shouldSink;
	}

	public boolean isWet() {
		return hasTouchedWater;
	}

	public void setSwimming(boolean shouldSwim) {
		this.shouldSwim = shouldSwim;
	}

	public void setSinking(boolean shouldSink) {
		this.shouldSink = shouldSink;
	}

	public void setWet(boolean hasTouchedWater) {
		this.hasTouchedWater = hasTouchedWater;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		this.setSwimming(tag.getBoolean("ShouldSwim"));
		this.setSinking(tag.getBoolean("ShouldSink"));
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean("ShouldSwim", this.isSwimming());
		tag.putBoolean("ShouldSink", this.isSinking());
	}

	@Environment(EnvType.CLIENT)
	public void syncSwimming() {
		FriendlyByteBuf byteBuf = PacketByteBufs.create();
		byteBuf.writeBoolean(this.isSwimming());
		ClientPlayNetworking.send(HeliumFlamingoItem.C2S_AIR_SWIMMING_ID, byteBuf);
	}

	// TODO
	/*@Environment(EnvType.CLIENT)
	public void syncSinking() {
		PacketByteBuf byteBuf = PacketByteBufs.create();
		byteBuf.writeBoolean(this.isSinking());
		ClientPlayNetworking.send(HeliumFlamingoItem.C2S_AIR_SWIMMING_ID, byteBuf);
	}*/
}
