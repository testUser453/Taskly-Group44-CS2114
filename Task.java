public class Task {
    private String name; // name of task
    private int mins; // number of minutes it takes to complete the task
    private String dueDate; //This field is for the future when we implement a PriorityQueue

    public Task(String task_name, int num_mins){
        name = task_name;
        mins = num_mins;
    }

    public Task(String task_name, int num_mins, String due_date){
        name = task_name;
        mins = num_mins;
        dueDate = due_date;
    }

    public void setName(String task_name){
        name = task_name;
    }

    public String getName(){
        return name;
    }

    public void setMinsToComplete(int num_mins){
        mins = num_mins;
    }

    public int getMinsToComplete(){
        return mins;
    }

    public void setDueDate(String date){
        //need a check or something to make sure that the right for date is given
        dueDate = date;
    }

    public String getDueDate(){
        return dueDate;
    }

    public String toString(){
        return "Task Name: " + name + " Total Mins to complete task: " + mins + " Due Date of task: " + dueDate;
    }



}
