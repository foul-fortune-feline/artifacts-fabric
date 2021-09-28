package artifacts.trinkets;

import dev.emi.trinkets.api.SlotGroups;
import dev.emi.trinkets.api.Slots;

public enum Slot {
    HAT("hat", SlotGroups.HEAD),
    NECKLACE(Slots.NECKLACE, SlotGroups.CHEST),
    GLOVE_MAINHAND(Slots.GLOVES, SlotGroups.HAND),
    GLOVE_OFFHAND(Slots.GLOVES, SlotGroups.OFFHAND),
    BELT(Slots.BELT, SlotGroups.LEGS),
    SHOES("shoes", SlotGroups.FEET);

    private final String slotId;
    private final String groupId;

    Slot(String slot, String group) {
        this.slotId = slot;
        this.groupId = group;
    }

    public String getSlotId() {
        return slotId;
    }

    public String getGroupId() {
        return groupId;
    }
}
