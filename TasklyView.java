// need to import the actual class from the package.
// import TasklyManager;
import java.util.Scanner;

public class TasklyView {
    public static void main(String[] args){
        /*
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
        */

        Scanner scanner = new Scanner(System.in);

        System.out.println("Hello! Welcome to Taskly!");
        
        boolean run = true;
        while(run){
            System.out.println("To input a task, type TASK. To do an action, type an ACTION");
            String command = scanner.nextLine();
            if(!command.equals("TASK") || !command.equals("ACTION")){
                continue;
            }

            if(command.equals("TASK")){
                System.out.println("Input your task in the following format: \nPlease input your tasks in the following format:\n'class_name assignment_name num_mins_to_complete'\nExample: 'CS_2114 sunday_lessons 60':");
                String inStr = scanner.nextLine();
                String[] arr = inStr.split(" ");
                if(arr.length != 3){
                    System.out.println("Incorrect number of arguments");
                }
                
            }
        }
    }
}
