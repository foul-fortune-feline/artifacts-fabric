package artifacts.extensions;

import net.minecraft.resources.ResourceLocation;

import java.util.Optional;

public interface DefaultedRegistryExtensions<T> {

	Optional<ResourceLocation> artifacts$getIdOrEmpty(T entry);
}
