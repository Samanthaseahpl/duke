import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sn = new Scanner(System.in);
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String line = "____________________________________________________________";
        System.out.println("Hello from\n" + logo);
        System.out.println(line);
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        System.out.println(line);

        Store lib = new Store();
        while(sn.hasNext()){
            String input = sn.nextLine();
            if(input.equals("bye")) {
                lib.bye();
            } else if (input.equals("list")) {
                lib.list();
            } else if(input.contains("done")) {
                String[] splited = input.split(" ");
                int index = Integer.parseInt(splited[1]);
                lib.done(index);
            } else if(input.contains("todo")) {
                String[] CheckInput = input.split(" ");
                String NewInput = input.substring(5);
                lib.todo(NewInput);
            } else if (input.contains("deadline")){
                String NewInput = input.substring(9);
                String[] ActionTime = NewInput.split("/");
                lib.deadline(ActionTime);
            } else if (input.contains("event")) {
                String NewInput = input.substring(6);
                String[] ActionTime = NewInput.split("/");
                lib.event(ActionTime);
            } else {
                lib.AddNewAction(input);
            }

        }

    }
}
