# Taskly : Deliverable 3

## Project Overview

Taskly is a Java task manager that helps students organize assignments for their classes. Users can add tasks, view assignments for a course, and remove completed tasks. The program uses a text-based menu and stores each course's assignments in a queue.

Our goal is to create a simple way for students to keep track of their assignments and view them in order of their due dates.

## Reflection

### What Changed From Our Specification?

Our original specification planned for a Model-View-Controller (MVC) design with `Task`, `Course`, a custom queue, `TasklyManager`, and `TasklyView`.

In our current prototype, we kept the four main classes but decided to use Java's built-in `LinkedList` through the `Queue<Task>` interface instead of writing our own queue class. This allowed us to focus on connecting the classes and getting the program running.

We also made a few changes to our methods. Our `addTask()` method currently takes three String parameters: the course name, task name, and estimated minutes. We originally planned to include due dates, but we have not added them to the input system yet. The `Task` class already has a String field for due dates.

Our specification also planned for validation of incorrect inputs. Right now, most input checking happens in `TasklyView`, such as checking commands and the number of arguments. `TasklyManager` handles converting minutes into an integer, but it does not yet check every invalid value.

We have started checking bad inputs through the menu, but we still need more tests for the model classes.

### Did the Model Stand Alone?

Our model classes can work without the view. `Task` stores assignment information, and `Course` manages a queue of tasks. Neither class needs Scanner or user input to work.

`TasklyManager` connects the courses and tasks, while `TasklyView` handles user interaction and printing messages.

We did not have to move any input or output code into the model to get the program working. The main separation between the model and view is still the same as our specification.

One improvement we still need is moving more validation into the manager or model so invalid information cannot be added when the program is used without the view.

### What Happened to Our Unknowns?

One thing we needed to figure out was how to store multiple courses and keep their assignments separate. We decided to use a `HashMap<String, Course>` in `TasklyManager`, which allows us to find courses using their names.

We also figured out how to use a queue to add, view, and remove tasks. We used `LinkedList` to implement the queue and connected those methods to the text menu.

Another challenge was reading user input with Scanner. We now have a working menu structure where users can type commands and enter task information.

We still need to figure out the best way to sort assignments by due date while keeping dates stored as Strings. We also need to finish checking invalid dates and handling all possible bad inputs.

### What Doesn't Work Yet?

Our prototype can add, view, and remove tasks, but some parts of our MVP are still incomplete.

- Users cannot enter due dates when adding assignments.
- Assignments are not sorted from earliest to latest due date.
- The program does not reject every invalid input, such as negative minutes or blank names.
- Some empty queue cases still return `null` instead of providing more specific error handling.
- We have not completed independent model tests for every method and edge case.

These are the main issues we plan to address in Deliverable 4.

## Next Steps

### Remaining MVP Features

For Deliverable 4, we plan to finish the remaining MVP features in this order:

1. **Add due dates:** Update `TasklyManager.addTask()` and `TasklyView` so users can enter a due date in `MM/DD/YYYY` format. The date will remain a String in `Task`.
2. **Validate inputs:** Check for blank names, invalid minutes, impossible dates, missing courses, and empty queues.
3. **Sort assignments:** Add a method that displays tasks from earliest to latest due date, even when assignments belong to different courses.
4. **Improve the menu:** Update the text menu to display the sorted assignment list and provide clearer messages.
5. **Finish testing:** Write and run tests for the model methods, sorting, and invalid inputs.

### New Change Feature

For our required new feature, we plan to add the ability to **mark assignments as completed** without immediately removing them.

We predict this feature will affect:

- **Task.java:** Add a Boolean field to track whether a task is completed, along with getter and setter methods.
- **TasklyManager.java:** Add a method that finds a task and updates its completion status.
- **TasklyView.java:** Add an option for users to mark assignments as completed.

We do not expect `Course.java` to need major changes because it will still be responsible for storing tasks in its queue.

After implementing this feature, we will compare our prediction to the classes we actually had to modify.

### Stretch Goals

Our main priority is completing the MVP before adding extra features.

We plan to attempt the ability to edit an existing assignment if we have enough time after finishing the required features and tests.

We will postpone any other optional features until the core task management system is working correctly.

### New Tests Needed

We plan to add tests for the following methods and situations:

| Method or Feature | Tests Needed |
|---|---|
| `addTask()` | Valid task, blank name, invalid minutes, missing course |
| Due-date validation | Valid date, incorrect format, impossible date |
| Task sorting | Different due dates, matching due dates, tasks from different courses |
| `removeTask()` | Normal removal, empty queue, nonexistent course |
| `peekNextTask()` | Queue with tasks, empty queue |
| Completion status | Mark task complete, check status, invalid task |
| Scanner menu | Invalid commands, missing arguments, invalid action |

These tests will help us make sure our changes do not break the existing features.

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

The table below lists the classes and test files in our project. We will update it with the actual work completed by each team member before submitting.

| Class or Test File | Contributor(s) | Contribution |
|---|---|---|
| `Task.java` | Aneesh & Matias | Wrote, revised, or reviewed task fields and methods |
| `Course.java` | Aneesh & Emma | Wrote, revised, or reviewed queue operations |
| `TasklyManager.java` | Emma & Aneesh | Wrote, revised, or reviewed course and task management |
| `TasklyView.java` | Matias & Aneesh | Wrote, revised, or reviewed Scanner input and menu |
| Model test file(s), if created | Aneesh | Wrote and ran tests for model methods and bad inputs |

### GenAI Use

We used ChatGPT to review parts of our Java code, help identify problems with Scanner input and validation, and organize our README to match all of the assignment requirements. We reviewed the suggested changes and are responsible for making sure they work with our program.

## Running the Program

Make sure the Java files are in the same folder or package.

Compile the program:

```bash
javac *.java
```

Run Taskly with the driver: TasklyModelDriver
(In the driver, tasks have to be updated manually and courses have to be updated manually)
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
