package max.task;

public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected Priority priority;
    public Task(String description) {
        this.description = description;
        this.isDone = false;
        this.priority = Priority.LOW;
    }

    /**
     * Returns the task description.
     *
     * @return The task description.
     */
    public String getDescription() {
        return description;
    }

    public String getStatusIcon() {
        return (isDone ? "X" : " "); // mark done task with X
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public void markAsDone(){
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:\n [X] " + this.description);
    }

    public void markAsNotDone(){
        this.isDone = false;
        System.out.println("OK, I've marked this task as not done yet:\n [] " + this.description);
    }

    public String toString() {
        return "[" + this.getStatusIcon() + "] " + this.description;
    }

    public abstract String toFileString();

    public static Task fromFileString(String line) {
        String[] parts = line.split(" \\| ");
        String type = parts[0];
        boolean isDone = parts[1].equals("1");

        switch (type) {
            case "T":
                ToDo todo = new ToDo(parts[2]);
                if (isDone) todo.markAsDone();
                return todo;

            case "D":
                Deadline deadline = new Deadline(parts[2], parts[3]);
                if (isDone) deadline.markAsDone();
                return deadline;

            case "E":
                Event event = new Event(parts[2], parts[3], parts[4]);
                if (isDone) event.markAsDone();
                return event;

            default:
                return null; 
        }
    }

}