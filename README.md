
### **📜 Max - Your Ever-Diligent Butler**
![Max Chatbot](https://your-image-link.com)  
_A sophisticated virtual butler at your service._

Welcome to **Max**, a chatbot designed to assist you in managing your tasks efficiently, all while maintaining an air of sophistication. Max will help you keep track of your **To-Dos, Deadlines, Events, and Priorities**, with a touch of **Victorian elegance**.

🔗 **[View Online User Guide](https://yourusername.github.io/yourrepo/)**

---

## **📖 Table of Contents**
1. [Getting Started](#-getting-started)
2. [Features](#-features)
   - [📌 Adding Tasks](#-adding-tasks)
   - [✅ Marking Tasks as Done](#-marking-tasks-as-done)
   - [🗑 Deleting Tasks](#-deleting-tasks)
   - [📅 Viewing Tasks on a Specific Date](#-viewing-tasks-on-a-specific-date)
   - [🔍 Finding Tasks](#-finding-tasks)
   - [⚙ Setting Task Priorities](#-setting-task-priorities)
   - [💾 Saving and Loading](#-saving-and-loading)
3. [Command Summary](#-command-summary)
4. [Troubleshooting](#-troubleshooting)
5. [Feedback & Contributions](#-feedback--contributions)

---

## **🛠 Getting Started**
### **Prerequisites**
- Ensure you have **Java 11 or later** installed.
- Download the latest release of Max from **[GitHub Releases](https://github.com/yourusername/yourrepo/releases)**.
- Open a terminal and run the chatbot:
  ```
  java -jar Max.jar
  ```

---

## **📝 Features**
### **📌 Adding Tasks**
Max supports **three types of tasks**:
- **To-Dos** (tasks without a date)
- **Deadlines** (tasks with a due date)
- **Events** (tasks with a start and end time)

#### **To-Do**
_Adds a simple task without a date._
```
todo [description]
```
**Example:**
```
todo Buy Earl Grey tea
```

#### **Deadline**
_Adds a task with a due date and time._
```
deadline [description] /by YYYY-MM-DD HHmm
```
**Example:**
```
deadline Submit essay /by 2025-02-20 2359
```

#### **Event**
_Adds an event with a start and end time._
```
event [description] /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm
```
**Example:**
```
event Afternoon tea /from 2025-02-18 1500 /to 2025-02-18 1600
```

---

### **✅ Marking Tasks as Done**
Use this command to mark a task as completed.
```
mark [task number]
```
**Example:**
```
mark 1
```

---

### **🗑 Deleting Tasks**
Removes a task from your list.
```
delete [task number]
```
**Example:**
```
delete 2
```

---

### **📅 Viewing Tasks on a Specific Date**
Lists all tasks happening on a specific date.
```
on YYYY-MM-DD
```
**Example:**
```
on 2025-02-18
```

---

### **🔍 Finding Tasks**
Search for tasks using keywords.
```
find [keyword]
```
**Example:**
```
find tea
```

---

### **⚙ Setting Task Priorities**
Set the priority of a task to **LOW, MEDIUM, or HIGH**.
```
priority [task number] [low/medium/high]
```
**Example:**
```
priority 1 high
```

---

### **💾 Saving and Loading**
Max automatically saves your tasks to a file after every update.
- Your data is stored in `max_data.txt`.
- Tasks will be **loaded automatically** when you restart Max.

---

## **📋 Command Summary**
| Command       | Description |
|--------------|-------------|
| `todo [desc]` | Adds a To-Do task. |
| `deadline [desc] /by YYYY-MM-DD HHmm` | Adds a Deadline task. |
| `event [desc] /from YYYY-MM-DD HHmm /to YYYY-MM-DD HHmm` | Adds an Event task. |
| `mark [task #]` | Marks a task as done. |
| `delete [task #]` | Deletes a task. |
| `on YYYY-MM-DD` | Shows tasks on a specific date. |
| `find [keyword]` | Searches for tasks. |
| `priority [task #] [low/medium/high]` | Sets a task’s priority. |
| `bye` | Exits the chatbot. |

---

## **🔧 Troubleshooting**
**Problem:** Max does not start.  
**Solution:** Ensure Java 11 or later is installed. Run `java -version` to check.

**Problem:** Date format errors.  
**Solution:** Ensure dates are in `YYYY-MM-DD HHmm` format.

**Problem:** Tasks not saving.  
**Solution:** Ensure `max_data.txt` is not **read-only** or located in a protected folder.

---

## **💬 Feedback & Contributions**
Max is constantly improving!
- **Bugs?** Report them on the **[Issues page](https://github.com/yourusername/yourrepo/issues)**.
- **Ideas?** Open a **discussion** or contribute via **pull requests**.

---

### 🎩 **Thank you for using Max!**
*"At your service, always."*

---
