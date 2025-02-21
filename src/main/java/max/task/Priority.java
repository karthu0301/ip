package max.task;

/**
 * Represents the priority levels for tasks.
 */
public enum Priority {
    LOW("LOW"), MEDIUM("MEDIUM"), HIGH("HIGH");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getLevel() {
        return name;
    }
}

