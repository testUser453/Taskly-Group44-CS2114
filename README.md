# Taskly: Deliverable 4

## Project Overview

Taskly is a Java task manager that helps students organize assignments for their classes. Users can add tasks, view assignments for a course, and remove completed tasks. The program uses a text-based menu and stores each course's assignments in a queue.

Our goal is to create a simple way for students to keep track of their assignments and view them in order of their due dates.

## Reflection

### What Changed From Our Specification?

Our original specification planned for a Model-View-Controller (MVC) design with `Task`, `Course`, a custom queue, `TasklyManager`, and `TasklyView`.

In our current prototype, we kept the main classes but decided to use Java's built-in `LinkedList` through the `Queue<Task>` interface instead of writing our own queue class. This allowed us to focus on connecting the classes and getting the program running.

We also made a few changes to our methods. Our `addTask()` method currently takes three String parameters: the course name, task name, and estimated minutes. We originally planned to include due dates, but we have not added them to the input system yet. The `Task` class already has a String field for due dates.

Our specification also planned for validation of incorrect inputs. We added validation in `TasklyController.addTask()` for blank course names and invalid minute values. However, we still need to check every possible invalid value, including blank task names and negative minutes.

### How Did Our Design Absorb the Change?

For our new feature, we added the ability to mark assignments as completed without immediately removing them.

We added a `completeTask()` method to `TasklyController` that communicates with the appropriate `Course` and completes the specified task. This allows assignments to have a completion status rather than only being removed from the queue.

Our implementation also includes `getSortedTasks()`, which gathers tasks across courses and sorts them by due date without changing the original queue order.

The main classes involved in our task management logic are `TasklyController`, `Course`, and `Task`. The view must also provide a way for users to access the completion feature.

Our original prediction was that `Task.java`, `TasklyManager.java`, and `TasklyView.java` would need modifications, while `Course.java` would remain mostly unchanged. In the current implementation, we used `TasklyController` instead of `TasklyManager`, and the completion functionality also involves the course management logic.

### Did the Change Cross the Model/View Line?

Our model classes can work without the view. `Task` stores assignment information, and `Course` manages a queue of tasks. Neither class needs Scanner or user input to work.

`TasklyController` manages the courses and tasks, while `TasklyView` handles user interaction and printing messages.

User actions in the view communicate with `TasklyController`, which updates the model. The view then retrieves information through the controller to display the current state.

We did not have to move input or output code into the model to implement the task management logic. The main separation between the model and view is still the same as our specification.

### What Changed From Our D3 Plan?

We originally planned to use a custom queue implementation, but decided to use Java's built-in `LinkedList` through the `Queue<Task>` interface instead.

We also originally planned to use `TasklyManager` as our manager class, but renamed it to `TasklyController`.

Our `addTask()` method currently accepts three String parameters rather than including a due date. We also expanded our implementation with task completion and sorting functionality.

We added validation for blank course names and invalid minute values, but still need to expand our validation and testing to cover additional bad inputs.

### What Doesn't Work Yet?

Our prototype can add, view, and remove tasks, but some parts of our MVP are still incomplete.

- Users cannot enter due dates when adding assignments.
- We still need to fully test sorting assignments from earliest to latest due date.
- The program does not reject every invalid input, such as negative minutes or blank task names.
- Some empty queue cases still require more consistent error handling.
- We need to ensure the completion feature is fully accessible through the view.
- Additional independent model tests and bad-input cases are still needed.

These are the main issues we plan to address in the final build.

## Next Steps

### Remaining MVP Features

For the final build, we plan to finish the remaining MVP features in this order:

1. **Add due dates:** Update `TasklyController.addTask()` and `TasklyView` so users can enter a due date in `MM/DD/YYYY` format.
2. **Validate inputs:** Check for blank names, invalid or negative minutes, impossible dates, missing courses, and empty queues.
3. **Finish completion functionality:** Ensure users can mark assignments as completed through the view and see their completion status.
4. **Improve sorting:** Ensure tasks from different courses are displayed from earliest to latest due date without changing their original queue order.
5. **Improve the menu:** Update the text menu to display sorted assignments and provide clearer messages.
6. **Finish testing:** Write and run tests for all core model methods, sorting, completion functionality, and invalid inputs.

### Stretch Goals

Our main priority is completing the MVP before adding extra features.

We plan to attempt the ability to edit an existing assignment if we have enough time after finishing the required features and tests.

We will postpone other optional features until the core task management system is working correctly.

### Three-Minute Demo

Our planned demonstration will follow this sequence:

1. **Introduction:** Briefly explain Taskly and its purpose.
2. **Adding tasks:** Demonstrate adding assignments for multiple courses.
3. **Viewing tasks:** Show how users can retrieve the next assignment for a specific course.
4. **Completing tasks:** Demonstrate marking an assignment as completed without immediately removing it.
5. **Sorting:** Show how assignments from multiple courses can be displayed by due date.
6. **Error handling:** Demonstrate how the program responds to invalid user input.

### Team Responsibilities

| Remaining Work | Team Member |
|---|---|
| Due-date input and validation | Aneesh |
| Sorting assignments | Emma |
| Menu improvements | Matias |
| Completion tracking feature | Matias & Aneesh |
| Model tests and edge cases | Aneesh & Emma |
| Final testing and README updates | Emma & Matias |

## Attribution

The following table lists the classes and test files in our project and the contributions of each team member.

| Class or Test File | Contributor(s) | Contribution |
|---|---|---|
| `Task.java` | Aneesh & Matias | Wrote, revised, and reviewed task fields and methods |
| `Course.java` | Aneesh & Emma | Wrote, revised, and reviewed course and queue operations |
| `TasklyController.java` | Emma & Aneesh | Wrote, revised, and reviewed course and task management |
| `TasklyView.java` | Matias & Aneesh | Wrote, revised, and reviewed Scanner input and menu |
| `TasklyControllerTest.java` | Aneesh | Wrote and ran JUnit tests for TasklyController |
| `CourseTest.java` | Emma | Wrote and ran JUnit tests for Course |
| `TaskTest.java` | Matias | Wrote and ran JUnit tests for Task |

### GenAI Use

We used ChatGPT to help review Java code, identify potential problems with input validation, generate suggestions for JUnit test cases, and troubleshoot compilation and test execution. We reviewed the suggested code and are responsible for understanding, verifying, and testing our implementation.

## Running the Program

Make sure the Java files are in the same folder or package.

Compile our program:

```bash
javac TasklyController.java TasklyView.java Task.java Course.java TasklyDriver.java
```

Run Taskly with the driver: TasklyModelDriver

(In the driver, tasks have to be added and removed manually with the methods in TasklyManager)

```bash
java TasklyModelDriver
```

Run Taskly with UI:

```bash
java TasklyView
```

The program accepts three main commands:

- `TASK` — Add an assignment.
- `ACTION` — View or remove assignments.

Example task input:

```text
CS_2114 weekly_homework 60
```

The `ACTION` menu allows users to view all tasks for a course, view the next task, or remove the next task.

# To test our program:

## Step 1. Compile all the Test Classes

```bash
javac -cp ".:lib/*" TasklyControllerTest.java TaskTest.java CourseTest.java
```

## Step 2. Run the JUnit Tests:

```bash
java -jar lib/junit-platform-console-standalone-*.jar execute --class-path ".:lib/*" --select-class TasklyControllerTest --select-class TaskTest --select-class CourseTest
```