package artifacts.common.init;

import artifacts.Artifacts;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;

public class ModSoundEvents {

	public static final SoundEvent POP = register("generic.pop");
	public static final SoundEvent MIMIC_HURT = register("entity.mimic.hurt");
	public static final SoundEvent MIMIC_DEATH = register("entity.mimic.death");
	public static final SoundEvent MIMIC_OPEN = register("entity.mimic.open");
	public static final SoundEvent MIMIC_CLOSE = register("entity.mimic.close");
	public static final SoundEvent FART = register("item.whoopee_cushion.fart");
	public static final SoundEvent WATER_STEP = register("block.water.step");

	private static SoundEvent register(String name) {
		ResourceLocation id = new ResourceLocation(Artifacts.MODID, name);
		return Registry.register(Registry.SOUND_EVENT, id, new SoundEvent(id));
	}
}
