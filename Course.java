import java.util.LinkedList;
import java.util.Queue;
// import java.util.EmptyQueueException; for some reason this is also giving me an error

public class Course {
    private Queue<Task> taskQ;
    private String name;

    public Course(String course_name){
        if (course_name == null || course_name.trim().isEmpty()) {
            throw new IllegalArgumentException("Course name cannot be blank.");
        }
        taskQ = new LinkedList<>();
        name = course_name.trim();
    }

    public void addTask(Task taskToAdd){
        if (taskToAdd == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
        taskQ.add(taskToAdd);
    }

    //THIS CODE DOES NOT THROW EXCEPTIONS EVEN THOUGH IT PROBABLY SHOULD. FOR BAD INPUTS, THIS DEFINITELY NEEDS TO BE FIXED.
    //BUT FOR NOW, if "null" is returned, then no elements exist in the Queue
    public Task removeTask(){
        if(taskQ.isEmpty()){
            return null;
        }
        return taskQ.remove();
    }

    public String getName(){
        return name;
    }

    public String getTasks(){
        return taskQ.toString();
    }

    public Task peekNextTask(){
        if(taskQ.isEmpty()){
            // throw new EmptyQueueException(); FOR SOME REASON, THIS IS RETURNING AN ERROR. IDK WHY
            return null;
        }
        return taskQ.peek();
    }

    public int getTaskCount(){
        return taskQ.size();
    }

    // A copy keeps the original queue order unchanged when sorting.
    public LinkedList<Task> getTaskList() { 
        return new LinkedList<>(taskQ); 
    }
    
    public boolean completeTask(String taskName) {
        if (taskName == null || taskName.trim().isEmpty()) { 
            return false; 
        }
        for (Task task : taskQ) {
            if (task.getName().equals(taskName.trim()) && !task.isCompleted()) {
                task.setCompleted(true);
                return true;
            }
        }
        return false;
    }

}
