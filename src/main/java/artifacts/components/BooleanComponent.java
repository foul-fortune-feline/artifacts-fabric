package artifacts.components;

import dev.onyxstudios.cca.api.v3.component.Component;
import net.minecraft.nbt.CompoundTag;

public class BooleanComponent implements Component {

	private final String name;
	protected boolean bool;

	public BooleanComponent(String name) {
		this.name = name;
	}

	public BooleanComponent(String name, boolean defaultValue) {
		this.name = name;
		this.bool = defaultValue;
	}

	public boolean get() {
		return bool;
	}

	public void set(boolean bool) {
		this.bool = bool;
	}

	public boolean invert() {
		this.bool = !this.bool;
		return this.bool;
	}

	@Override
	public void readFromNbt(CompoundTag tag) {
		this.bool = tag.contains(this.name) && tag.getBoolean(this.name);
	}

	@Override
	public void writeToNbt(CompoundTag tag) {
		tag.putBoolean(this.name, this.bool);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		} else if (obj instanceof BooleanComponent other) {
			return this.get() == other.get() && this.name.equals(other.name);
		}
		return false;
	}
}
