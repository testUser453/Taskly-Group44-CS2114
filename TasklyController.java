import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;

public class TasklyController {
    // I don't know what data structure to use
    // private LinkedList<Course> courseList; 
    private HashMap<String, Course> courseMap;

    public TasklyController(){
        // courseList = new LinkedList<>();
        courseMap = new HashMap<>();
    }

    // Changed input to be three parameters
    public void addTask(String courseName, String taskName, String numMins){
        if (courseName == null || courseName.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be blank.");
        }
        if (numMins == null) {
            throw new IllegalArgumentException("Minutes must be a number.");
        }
        int mins;
        try {
            mins = Integer.parseInt(numMins);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Minutes must be a number.");
        }
        Task task = new Task(taskName, mins);

        String name = courseName.trim();
        if (!courseMap.containsKey(name)) {
            courseMap.put(name, new Course(name));
        }
        courseMap.get(name).addTask(task);
    }

    // Get the next task
    public String getNextTask(String courseName){
        Course course = courseMap.get(courseName);
        if (course == null || course.peekNextTask() == null) {
            // return "There are no tasks left for this course, or the course doesn't exist";
            return "error";
        }
        return course.peekNextTask().toString();
    }

    // Removes and returns task that was recently removed 
    // this code DOES NOT account for exceptions that could be thrown from Course's removeTask() method
    // this returns null if the courseMap does not contain the course.
    public Task removeTask(String courseName) {
        Course course = courseMap.get(courseName);
        return course == null ? null : course.removeTask();
    }

    public String getCourseTaskList(String courseName){
        Course course = courseMap.get(courseName);
        return course == null ? null : course.getTasks();
    }

    public String getCoursesWithTasks(){
        return courseMap.keySet().toString();
    }

    
    public boolean completeTask(String courseName, String taskName) {
        Course course = courseMap.get(courseName);
        return course != null && course.completeTask(taskName);
    }

    // Shows tasks from all courses by date, without changing queue order.
    public String getSortedTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        for (Course course : courseMap.values()) {
            tasks.addAll(course.getTaskList());
        }
        tasks.sort(Comparator.comparing(Task::getDueDate,
            Comparator.nullsLast(Comparator.comparing(date ->
                java.time.LocalDate.parse(date,
                    java.time.format.DateTimeFormatter.ofPattern("MM/dd/uuuu"))))));
        if (tasks.isEmpty()) {
            return "No tasks available.";
        }
        StringBuilder result = new StringBuilder();
        for (Task task : tasks) {
            result.append(task).append("\n");
        }
        return result.toString();
    }

}
