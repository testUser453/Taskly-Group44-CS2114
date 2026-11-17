# Taskly : Deliverable 4 Second Prototype

## Project Overview

Taskly is a Java task manager that helps students organize assignments for their classes. Users can add tasks, view the next assignment for a course, remove tasks, and mark assignments as completed without removing them. The program uses a text-based menu and stores each course's assignments in a queue.

Our goal is to create a simple way for students to keep track of assignments. Displaying tasks in due-date order is still a future goal and is not part of the current menu.

## Reflection

### What Changed From Our Specification?

Our original specification planned for a Model-View-Controller (MVC) design with Task, Course, a custom queue, a manager class, and TasklyView.

We kept the main MVC structure, but used Java's built-in LinkedList through the Queue<Task> interface instead of making our own queue class. We also renamed TasklyManager to TasklyController. The controller uses a HashMap<String, Course> to keep courses separate.

Our new feature is marking tasks as completed without removing them. We originally expected to change Task, the controller, and the view. We ended up changing Course too, because it needed a method to find a task in its queue and mark it completed. Task now has a completed field with getter and setter methods, the controller calls Course.completeTask(), and the view has a COMPLETE option.

We also added more validation. Task rejects blank names and minutes that are zero or negative. Course rejects blank course names and null tasks. The controller checks the minutes input before creating a task, and the view catches invalid task input so the program can continue running.
Did the Model Stand Alone?
Yes. Task stores assignment information and completion status, and Course manages the task queue. Neither class uses Scanner or prints menu messages. They can be tested without running the view.

TasklyController connects the view to the model by finding courses and calling model methods. TasklyView reads user input and displays messages. This keeps the model/view boundary close to what we planned in the specification.

### What Happened to Our Unknowns?

We figured out how to store multiple courses by using a HashMap<String, Course> and how to manage tasks using a queue backed by LinkedList.

We also figured out how to add a completion feature without removing tasks. The program can find a task by name within a course and change its completion status. If two tasks have the same name, it marks the first one that is not already completed.

We still need to connect due-date input to the menu. Task can store and validate a date in MM/dd/yyyy format, but the current view only asks for a course name, task name, and estimated minutes.

### What Doesn't Work Yet?

- Users cannot enter due dates through the current text menu.
- The current menu does not display assignments in due-date order.
- Past due dates are not rejected by Task.setDueDate().
- The menu only supports single-word course and task names when adding tasks.
- Some missing-course and empty-queue cases use null or "error" instead of more specific messages.

## Next Steps

### Remaining MVP Features

For the final version, we plan to focus on these tasks in order:
1. Verify tests: Make sure the JUnit setup works and run the existing CourseTest, TaskTest, and TasklyControllerTest classes.
2. Finish input handling: Improve messages for invalid commands, empty queues, and missing courses. Check whether the program should reject past due dates.
3. Add due-date input: Update the controller and view so users can enter a date when adding a task.
4. Improve the menu: Make the available commands clearer and show users the information they need.
5. Review remaining MVP goals: Decide how to handle displaying assignments by due date before the final submission. This is not part of the current working menu.

### New Change Feature

We implemented the new feature of marking assignments as completed without immediately removing them.

The feature affected these classes:
- Task.java: Added a completed boolean field, isCompleted(), and setCompleted().
- Course.java: Added completeTask() to find a task by name and mark it completed.
- TasklyController.java: Added completeTask() to find the correct course and call the course method.
- TasklyView.java: Added the COMPLETE action to the menu.
Our original prediction did not include a change to Course.java. We needed that change because Course owns the queue and should handle finding a task inside it.

### Stretch Goals

Our first priority is finishing and testing the MVP. If we have enough time, we may add the ability to edit an existing assignment. We will leave other optional features until the main program works reliably.

### New Tests Needed

We have written three unit test classes:

| Test File | What It Covers |
|---|---|
| `CourseTest.java` | Course names, adding tasks, queue order, removing and peeking, empty queues, copying the task list, and completing tasks |
| `TaskTest.java` | Constructors, names, estimated minutes, due-date format, completion status, and `toString()` |
| `TasklyControllerTest.java` | Adding tasks, invalid inputs, finding and removing tasks, listing courses, and completion tracking |

The tests include normal inputs, bad inputs, and edge cases. We still need to run the full suite and confirm the results. The test files currently use a mix of JUnit 4 and JUnit 5, so we need to make sure the project is configured correctly.

We also plan to test the menu manually by adding tasks, marking a task completed, checking its status, removing tasks, and entering invalid commands.

### Team Responsibilities

| Remaining Work | Team Member |
|---|---|
| Due-date input and validation | Aneesh |
| Due-date ordering work, if kept for the final version | Emma |
| Menu improvements | Matias |
| Completion feature follow-up and fixes | Matias & Aneesh |
| Model tests and edge cases | Aneesh & Emma |
| Final testing and README updates | Emma & Matias |


## Attribution

The contributor names below are carried over from our previous README where available. We still need to confirm the exact contribution for each current file before submitting.

| Class or Test File | Contributor(s) | Contribution |
|---|---|---|
| `Task.java` | Aneesh & Matias | Created task methods, validation, and completion tracking. |
| `Course.java` | Aneesh & Emma | Implemented queue methods and completion tracking. |
| `TasklyController.java` | Emma & Aneesh | Managed courses, tasks, and input validation. |
| `TasklyView.java` | Matias & Aneesh | Created the menu and user interactions. |
| `TasklyDriver.java` | Aneesh | Tested basic task operations. |
| `CourseTest.java` | Emma | Tested queue methods and completion. |
| `TaskTest.java` | Matias | Tested task methods and validation. |
| `TasklyControllerTest.java` | Aneesh | Tested controller methods and invalid inputs. |

### GenAI Use

We used ChatGPT to review parts of our Java code, identify validation and edge cases, and review test coverage to match the second prototype requirements. We reviewed the suggestions against our code. We still need to run the tests and verify the program ourselves before submission.

## Running the Program

Make sure the Java source files are in the same folder or package and that a compatible Java JDK is installed.

Compile the application classes (without requiring JUnit for the test files):

```bash
javac Task.java Course.java TasklyController.java TasklyDriver.java TasklyView.java
```

Run the example driver:

```bash
java TasklyDriver
```

Run the interactive program:

```bash
java TasklyView
```

The main menu accepts these commands:

- `TASK` — Add assignments.
- `ACTION` — View, remove, or mark assignments as completed.
- `EXIT` — Exit the program.

To add a task, type `TASK` and enter the course name, task name, and estimated minutes separated by spaces:

```text
CS_2114 weekly_homework 60
```

Type `done` to return to the main menu.

To mark a task completed, type `ACTION`, then `COMPLETE`, then enter the course name and task name when prompted. A completed task stays in the queue until it is removed.

To view the next task, type `ACTION` and enter a course name. After viewing the task, type `finished` to remove it or `other` to return to the action menu. Type `done` to leave the action menu.

**Note:** The current menu does not accept due dates when creating tasks, and it does not display a sorted due-date list.
