package max.task;

/**
 * Represents the priority levels for tasks.
 */
public enum Priority {
    LOW(1), MEDIUM(2), HIGH(3);

    private final int level;

    Priority(int level) {
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    /**
     * Converts an integer priority value to a Priority enum.
     * Defaults to LOW if an invalid value is provided.
     */
    public static Priority fromInt(int level) {
        switch (level) {
        case 3:
            return HIGH;
        case 2:
            return MEDIUM;
        case 1:
        default:
            return LOW;
        }
    }
}
