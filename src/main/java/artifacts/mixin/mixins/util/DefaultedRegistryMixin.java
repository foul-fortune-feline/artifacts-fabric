package artifacts.mixin.mixins.util;

import artifacts.mixin.extensions.DefaultedRegistryExtensions;
import com.mojang.serialization.Lifecycle;
import net.minecraft.core.DefaultedRegistry;
import net.minecraft.core.Holder;
import net.minecraft.core.MappedRegistry;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

import java.util.Optional;
import java.util.function.Function;

@Mixin(DefaultedRegistry.class)
public abstract class DefaultedRegistryMixin<T> extends MappedRegistry<T> implements DefaultedRegistryExtensions<T> {

	public DefaultedRegistryMixin(ResourceKey<? extends Registry<T>> resourceKey, Lifecycle lifecycle, @Nullable Function<T, Holder.Reference<T>> function) {
		super(resourceKey, lifecycle, function);
	}

	@Override
	@Unique
	public Optional<ResourceLocation> artifacts$getIdOrEmpty(T entry) {
		return Optional.ofNullable(super.getKey(entry));
	}
}
