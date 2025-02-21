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
}
