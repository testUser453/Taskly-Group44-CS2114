public class TasklyModelDriver {
    public static void main(String[] args){
        TasklyManager taskManager = new TasklyManager();
        /*
        taskManager.addTask("CS_2114", "sunday_hw", "60");
        taskManager.addTask("CS_2114", "hw_1", "60");
        taskManager.addTask("MATH_1111", "sunday_hw", "50");

        System.out.println(taskManager.getNextTask("CS_2114"));
        taskManager.removeTask("CS_2114");

        System.out.println(taskManager.getNextTask("CS_2114"));
        taskManager.removeTask("CS_2114");

        System.out.println(taskManager.getNextTask("CS_2114"));

        System.out.println(taskManager.getNextTask("MATH_1111"));
        taskManager.removeTask("MATH_1111");
         */
        System.out.println(taskManager.getNextTask("CS_2114"));
    }
}
