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
        Integer mins = Integer.decode(numMins);
        if(!courseMap.containsKey(courseName)){
            Course nCourse = new Course(courseName);
            courseMap.put(courseName, nCourse);
        }
        Task nTask = new Task(taskName, mins);
        courseMap.get(courseName).addTask(nTask);
    }

    // Removes and returns task that was recently removed 
    // this code DOES NOT account for exceptions that could be thrown from Course's removeTask() method
    // this returns null if the courseMap does not contain the course.
    public Task removeTask(String courseName) {
        if(!courseMap.containsKey(courseName)) {
            return null;
        }
        return courseMap.get(courseName).removeTask();
    }

    public String getCourseTaskList(String courseName){
        if(!courseMap.containsKey(courseName)){
            return null;
        }
        return courseMap.get(courseName).getTasks();
    }
}
