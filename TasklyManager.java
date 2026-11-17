// import java.util.LinkedList;
import java.util.HashMap;

public class TasklyManager {
    // I don't know what data structure to use
    // private LinkedList<Course> courseList; 
    private HashMap<String, Course> courseMap;

    public TasklyManager(){
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
        // Validate the task before creating a new course.
        Task newTask = new Task(taskName, mins, dueDate);
        String name = courseName.trim();
        if (!courseMap.containsKey(name)) {
            courseMap.put(name, new Course(name));
        }
        courseMap.get(name).addTask(newTask);
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

}
