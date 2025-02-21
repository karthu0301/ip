# Max - Your Ever Diligent Butler

![Ui.png](Ui.png)

A sophisticated virtual butler at your service.

Greetings, esteemed user! Welcome to the abode of Max Chatbot, your personal Victorian butler in digital form. I am here to assist you in managing your tasks with the utmost diligence and a sprinkle of humor. Allow me to guide you through the functionalities of this splendid application.

Absolutely! Here's a revised version of the user guide that incorporates the personality of Max as a diligent, funny Victorian butler:

---
## Getting Started

### Installation
1. **Download Max, your digital butler**: Venture to the [GitHub repository](https://github.com/karthu0301/ip) and secure the latest release.
2. **Installation Requirements**: Ensure Java is installed on your computing device.
3. **Commence with Max**: Execute the downloaded file with a double-click, and I shall greet you posthaste.

### First Steps
- Upon starting the application, you will be greeted by myself, Max.
- Start a conversation, and I will promptly of the correct ways you may address and use me.
- Use me to record your tasks (ToDos, Deadlines and Events), their dates and times, their priority levels and whether they are done or not.

## Features

### Adding a Task
- **Command**: `todo (task) / deadline (task) /by (yyyy-mm-dd HHmm) / event (task) /from (yyyy-mm-dd HHmm) /to`(yyyy-mm-dd HHmm)
- **Description**: Entrust me with a new task to remember.
- **Usage**: `todo Purchase tea leaves`
- **Example**:
  ```
  You: todo Purchase tea leaves
  Max: Certainly, sir! I shall add that task immediately.: [T][][LOW] Purchase tea leaves
  ```

### Listing All Tasks
- **Command**: `list`
- **Description**: Review all tasks you've assigned to me.
- **Usage**: `list`
- **Example**:
  ```
  You: list
  Max: Certainly, here are your tasks in your list. They show the type, priority, description and duration of your tasks:
      1. [T][][LOW] Purchase tea leaves
  ```

### Marking a Task as Done
- **Command**: `mark`
- **Description**: Inform me which task you've splendidly completed.
- **Usage**: `mark 1`
- **Example**:
  ```
  You: mark 1
  Max: Beautiful! I always knew you could do this!
        [T][][LOW] Purchase tea leaves
  ```

### Unmarking a Task
- **Command**: `unmark`
- **Description**: Inform me which duty you've failed to complete.
- **Usage**: `unmark 1`
- **Example**:
  ```
  You: unmark 1
  Max: Very good, sir! Remember, it's never too late to carry out your duties!
        [T][][LOW] Purchase tea leaves
  ```

### Deleting a Task
- **Command**: `delete`
- **Description**: Command me to dispose of an unwanted task.
- **Usage**: `delete 1`
- **Example**:
  ```
  You: delete 1
  Max: Very well, it's as if it never existed: 
        [T][][LOW] Purchase tea leaves
        Now you have 0 tasks in the list.
  ```

### Setting Task Priority
- **Command**: `priority`
- **Description**: Prioritize a task with either low, medium, or high importance.
- **Usage**: `priority 1 high`
- **Example**:
  ```
  You: priority 1 high
  Max: Very good, sir! I have updated the priority for task:
        [T][][HIGH] Purchase tea leaves
  ```

### Finding tasks
- **Command**: `find`
- **Description**: Find tasks in your never-ending list.
- **Usage**: `find tea`
- **Example**:
  ```
  You: find tea
  Max: Certainly, here are the matching tasks in your list:
        [T][][HIGH] Purchase tea leaves
  ```

### Show tasks 
- **Command**: `on`
- **Description**: Display tasks on a certain date.
- **Usage**: `on 2025-04-02`
  - **Example**:
    ```
    You: on 2025-04-02
    Max: Here are the tasks on 2025-04-02:
    Alas, there are no tasks scheduled on this particular date. What a splendid day for a bit of leisure!
    ```

### Close the chatbot
- **Command**: `bye`
- **Example**:
  ```
  You: bye
  Max: Good night, esteemed sir/madam. Might I suggest a moment of rest after your work?
  ```
  
## Troubleshooting
- **Problem**: Max remains unresponsive.
    - **Solution**: Ensure you are addressing me in the active window; a gentle restart might also nudge me awake.
- **Problem**: Encounter "Invalid command".
    - **Solution**: Peruse this guide to ensure the command syntax is correct and elegantly put.

## FAQ

**Q: How doth one update their digital butler?**
A: Visit the GitHub repository for the latest updates and follow the genteel instructions provided.

## Providing Feedback

Your insights and critiques are invaluable to my improvement. Kindly relay your experiences and suggestions through the [GitHub issue tracker](https://github.com/karthu0301/ip/issues).

I am profoundly grateful for your choice to employ Max Chatbot as your digital aide. Together, we shall bring order to your tasks with a touch of Victorian grace!

---
