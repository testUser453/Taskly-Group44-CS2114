// need to import the actual class from the package.
// import TasklyManager;
import java.util.Scanner;

public class TasklyView {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        TasklyManager taskManager = new TasklyManager();

        System.out.println("Hello! Welcome to Taskly!");
        
        boolean run = true;
        while(run){
            System.out.println("\nTo input a task, type TASK. To do an action, type an ACTION. If you are done interacting with the program, type EXIT");
            String command = scanner.nextLine();
            if(!command.equals("TASK") && !command.equals("ACTION") && !command.equals("EXIT")){
                // System.out.println("coming here 1");
                continue;
            }

            if(command.equals("EXIT")){
                break;
            }

            if(command.equals("TASK")){
                // boolean task = true;
                // while(task)
                boolean task = true;
                while(task){
                    System.out.println("\nPlease input your tasks in the following format:\n'course_name assignment_name num_mins_to_complete'\nExample: 'CS_2114 sunday_lessons 60'. If you are done adding tasks, type 'done':");
                    String inStr = scanner.nextLine();
                    if(inStr.equals("done")){
                        break;
                    }
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
                    // System.out.println("started adding all the arguments");
                    taskManager.addTask(arr[0], arr[1], arr[2]);
                    // System.out.println("finished adding all arguments");
                    // System.out.println("Task list for element is: " + taskManager.getNextTask(arr[0]));
                }
            }

            if(command.equals("ACTION")){
                // need to peek task for each class
                // need to remove that task when user specifies
                boolean action = true;
                while(action){
                    System.out.println("\nOptions:\n-To see the next task to complete for a course, type the 'course_name peek'\n-To get out of the actions menu, type 'done'\n");
                    String nL = scanner.nextLine();
                    if(nL.equals("done")){
                        break;
                    }
                    if(nL.contains("peek")){
                        // System.out.println("code comes here 1");
                        String[] nL_arr = nL.split(" ");
                        String courseName = nL_arr[0];
                        if(taskManager.getNextTask(courseName).equals("error")){
                            System.out.println("You have either completed all tasks for this course, or your this course does not exist");
                            continue;
                        }

                        System.out.println(taskManager.getNextTask(nL_arr[0]));
                        
                        System.out.println("\nWhen you are finished with this task type 'finished'. If you want to worry about a different action, type 'other'");
                        nL = scanner.nextLine();
                        if(nL.equals("finished")){
                            taskManager.removeTask(courseName);
                            continue;
                        }
                        if(nL.equals("other")){
                            continue;
                        }
                        continue;
                    }
                }
            }   
        }
    }
}
