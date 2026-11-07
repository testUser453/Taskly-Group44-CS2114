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

    // Define input string to be: "CS_2114 Challenge_Exercise_11 150"
    // This DEFINITELY NEEDS TO CHANGE
    public void addTask(String input){
        //split parameter into a String array
        String[] inArr = input.split(" ");
        String courseName = inArr[0];
        String taskName = inArr[1];
        Integer numMins = Integer.decode(inArr[2]); // not sure if this is right

        if(!courseMap.containsKey(courseName)){
            Course nCourse = new Course(courseName);
            courseMap.put(courseName, nCourse);
        }
        Task nTask = new Task(taskName, numMins);
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
}
