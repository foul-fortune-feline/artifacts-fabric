package artifacts.components;

import dev.onyxstudios.cca.api.v3.component.sync.AutoSyncedComponent;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;

public class SyncedBooleanComponent extends BooleanComponent implements AutoSyncedComponent {

	public SyncedBooleanComponent(String name) {
		super(name);
	}

	@Override
	public void writeSyncPacket(FriendlyByteBuf buf, ServerPlayer recipient) {
		buf.writeBoolean(this.bool);
	}

	@Override
	public void applySyncPacket(FriendlyByteBuf buf) {
		this.bool = buf.readBoolean();
	}
}
