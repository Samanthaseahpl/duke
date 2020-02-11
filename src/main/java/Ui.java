public class Ui {

    public Ui(){
    }

    /**
     * This method forms a divider line.
     * @return String This is the line to be return.
     */
    public String line(){
        return "____________________________________________________________\n";
    }

    /**
     * This method prints the welcome message when user first run the program.
     */
    public void logo(){
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    public static String welcomeMessage() {
        return "Hi, welcome to Duke! \nHow can I help you?";
    }

    /**
     * This method return the Bye Message.
     * @return String This is the bye message.
     */
    public String byeMessage(){
        return "Bye. Hope to see you again soon!";
    }

    /**
     * This method forms the Delete action message.
     * @param Action This is the Action to be deleted.
     * @param amt This is the amount of element in the storage.
     * @return String This is the Message for the Delete action.
     */
    public String DeleteMessage(String Action, int amt){
        String Output = String.format("\nNow you have %d tasks in the list\n", amt);
        return "Noted. I've removed this task: \n" + Action + Output;
    }

    /**
     * This method forms the message for the Done action.
     * @param Action This is the action that has been completed.
     * @return This is the message for the Done action.
     */
    public String DoneMessage(String Action){
        return "Nice! I've marked this task as done:\n" + Action;
    }
}
