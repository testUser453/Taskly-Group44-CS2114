# Taskly — First Working Prototype

## Project Overview

Taskly is a text-based task manager designed to help students organize assignments by course. Users can enter a course name, task name, and estimated number of minutes to complete a task. Each course stores its tasks in a queue.

This repository contains our first working prototype for CS 2114 Project 3.

## Current Features

- Add tasks with a course name, assignment name, and estimated minutes.
- Automatically create a course when adding its first task.
- View all tasks for a course.
- View the next task without removing it.
- Remove the next task from a course.
- Use `TASK`and `ACTION` commands.
- Display error messages for invalid commands and certain invalid inputs.

Each course uses Java's built-in `LinkedList` through the `Queue<Task>` interface. Tasks are removed in first-in, first-out (FIFO) order.

## Class Responsibilities

| Class | Responsibility |
|---|---|
| `Task.java` | Stores a task's name, estimated minutes, and due-date string. |
| `Course.java` | Stores a course name and a queue of tasks. |
| `TasklyManager.java` | Manages courses and handles adding, viewing, and removing tasks. |
| `TasklyView.java` | Uses Scanner to read user input and display results. |

## How to Run

1. Open the project in Eclipse or VS Code.
2. Make sure all four Java files are in the same package.
3. Compile the Java files.
4. Run `TasklyView.java`.

From a terminal, you can use:

```bash
javac *.java
java TasklyView
```

## How to Use

When the program starts, enter one of the following commands:

- `TASK` — Add an assignment.
- `ACTION` — View or remove assignments.

Example task input:

```text
CS_2114 sunday_lessons 60
```

The program will add the assignment to the CS_2114 course.

For `ACTION`, choose one of the following:

1. View all tasks for a course.
2. View the next task.
3. Remove the next task.

## Reflection

Our first prototype focuses on getting the main classes to work together and allowing users to interact with the program through the terminal.

We followed our original plan by separating the program into a model, controller, and view. The `Task` and `Course` classes store information, `TasklyManager` handles operations, and `TasklyView` handles user interaction.

One difference from our original specification is that we currently use Java's built-in `LinkedList` instead of creating our own queue implementation. We also automatically create courses when users add their first assignment to that course.

The model can be used separately from the view because its methods do not depend on Scanner or console input.

Some features are still unfinished. Due dates are stored as Strings, but the current input system does not collect them. Tasks are also not yet displayed in due-date order.

## Next Steps

For Deliverable 4, we plan to:

- Add due dates when users create assignments.
- Check for invalid dates and other bad inputs.
- Display assignments from earliest to latest due date.
- Improve the user interface and error messages.
- Add independent tests for the model classes.
- Test empty queues, missing courses, and invalid inputs.

A possible additional feature is marking assignments as completed. This would require updating the `Task` class to store completion status, the manager to update that status, and the view to let users mark assignments complete.

If time allows, we could also add the ability to edit existing assignments.

## Testing

The prototype should be tested using the following cases:

- Add a task to a new course.
- Add multiple tasks to the same course.
- View all tasks for a course.
- View the next task.
- Remove a task.
- Try removing a task from an empty queue
