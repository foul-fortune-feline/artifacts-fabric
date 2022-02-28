package artifacts.extensions;

public interface MobEffectInstanceExtensions {

	/**
	 * Sets the duration of the status effect instance and
	 *
	 * @param duration the duration to set
	 */
	default void artifacts$setDuration(int duration) {
		throw new IllegalStateException();
	}
}
