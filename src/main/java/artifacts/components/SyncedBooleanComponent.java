package artifacts.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class SyncedBooleanComponent implements AutoSyncedComponent {

	private final String name;
	private boolean bool;

	public SyncedBooleanComponent(String name) {
		this.name = name;
	}

	public boolean get() {
		return bool;
	}

	public void set(boolean bool) {
		this.bool = bool;
	}

	public void invert() {
		this.bool = !this.bool;
	}

	@Override
	public void writeSyncPacket(FriendlyByteBuf buf, ServerPlayer recipient) {
		buf.writeBoolean(this.bool);
	}

	@Override
	public void applySyncPacket(FriendlyByteBuf buf) {
		this.bool = buf.readBoolean();
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		if (tag.contains(this.name)) {
			this.bool = tag.getBoolean(this.name);
		} else {
			this.bool = false;
		}
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean(this.name, this.bool);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof SyncedBooleanComponent) {
			return this.get() == ((SyncedBooleanComponent) obj).get();
		}
		return false;
	}
}
