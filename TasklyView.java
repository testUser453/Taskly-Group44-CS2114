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
        TasklyManager taskManager = new TasklyManager();

        System.out.println("Hello! Welcome to Taskly!");
        
        boolean run = true;
        while(run){
            System.out.println("To input a task, type TASK. To do an action, type an ACTION. If you are done interacting with the program, type DONE");
            String command = scanner.nextLine();
            if(!command.equals("TASK") || !command.equals("ACTION") || !command.equals("DONE")){
                continue;
            }

            if(command.equals("DONE")){
                break;
            }

            if(command.equals("TASK")){
                // boolean task = true;
                // while(task)
                System.out.println("Input your task in the following format: \nPlease input your tasks in the following format:\n'class_name assignment_name num_mins_to_complete'\nExample: 'CS_2114 sunday_lessons 60':");
                String inStr = scanner.nextLine();
                String[] arr = inStr.split(" ");
                if(arr.length != 3){
                    System.out.println("Incorrect number of arguments");
                }
                try {
                    Integer.parseInt(arr[2]);
                } catch (Exception e){
                    System.out.println("Incorrect input for number");
                    continue;
                }
                taskManager.addTask(arr[0], arr[1], arr[2]);
            }

            if(command.equals("ACTION")){
                // need to peek task for each class
                // need to remove that task when user specifies
                System.out.println("Options:\n-To see the next task to complete for a class, type the 'class_name peek'");
                String nL = scanner.nextLine();
                if(nL.contains("peek")){
                    String[] nL_arr = nL.split(" ");
                    if(taskManager.getNextTask(nL_arr[0]).equals("error")){
                        System.out.println("There is an error, please redo the command");
                        continue;
                    }

                }
            }   
        }
    }
}
