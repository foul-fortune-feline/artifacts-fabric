package artifacts.item.curio.hands;

import artifacts.item.curio.TrinketArtifactItem;
import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;

public class GoldenHookItem extends TrinketArtifactItem {
    @Override
    public boolean canWearInSlot(String group, String slot) {
        return (group.equals(SlotGroups.HAND) || group.equals(SlotGroups.OFFHAND)) && slot.equals(Slots.GLOVES);
    }
}
