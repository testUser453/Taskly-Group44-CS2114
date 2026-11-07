// need to import the actual class from the package.
// import TasklyManager;

public class TasklyView {
    public static void main(String[] args){
        System.out.println("Hello! Welcome to Taskly!");

        // Need to implement Scanner class and scanner interface
        String courseName = "CS_2114";
        String taskName = "CS_Lab";
        String numMins = "150";

        TasklyManager taskManager = new TasklyManager();

        taskManager.addTask(courseName, taskName, numMins);

        System.out.println(taskManager.getCourseTaskList(courseName));

        taskManager.addTask("CS_2114", "weekly hw", "60");
        System.out.println(taskManager.getCourseTaskList(courseName));
        // I don't know why the taskManager.getCourseTaskList keeps returning a string array.
    }
}
