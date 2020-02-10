import java.io.*;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class Duke extends Application {

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    private Image user = new Image(this.getClass().getResourceAsStream("/images/gudetama1.png"));
    private Image duke = new Image(this.getClass().getResourceAsStream("/images/gudetama2.png"));

    private Store lib;
    private Ui ui = new Ui();
    private Scanner sn = new Scanner(System.in);
    private DukeException DE;
    String[] CheckInput;

    public Duke(){}

//    /**
//     * This method uses filepath to locate the file.
//     * If file exist, load the file. Otherwise create new file.
//     * @param filepath the absolute location of the .txt file.
//     */
//    public Duke(String filepath){
//        DE = new DukeException();
//        File file = new File(filepath); //create a file obj with the given filepath.
//        this.lib = new Store(file); //create store from absolute filepath
//        try {
//            boolean result = file.exists();
//            if(!result){
//                boolean isNewFile = file.createNewFile();
//            } else {
//                    Scanner newSN = new Scanner(file);
//                    while(newSN.hasNextLine()){
//                        String nxtLine = newSN.nextLine();
//                        lib.load(nxtLine);
//                    } //end while: for reading existing file
//                    System.out.print(ui.line());
//            }
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//    } //end Duke

    /**
     * This method takes input from scanner and redirect it to the respective action.
     */
    public void run(){
        ui.showWelcome();
        while(sn.hasNext()) {
            String input = sn.nextLine();
            if (input.equals("bye")) {
                lib.bye();
            } else if (input.equals("list")) {
                lib.list();
            } else if (input.contains("done")) {
                String[] splited = input.split(" ");
                int index = Integer.parseInt(splited[1]);
                lib.done(index);
            } else if (input.contains("delete")) {
                String[] splited = input.split(" ");
                int index = Integer.parseInt(splited[1]);
                lib.delete(index);
            } else if (input.contains("todo")) {
                CheckInput = input.split(" ");
                if (CheckInput.length < 2) {
                    DE.IncorrectInputTodo();
                } else {
                    String NewInput = input.substring(5);
                    System.out.print(lib.todo(NewInput));
                }
            } else if (input.contains("deadline")) {
                CheckInput = input.split(" ");
                if (CheckInput.length < 2) {
                    DE.IncorrectInputDeadline();
                } else if (!input.contains("/")) {
                    DE.DeadlineMissingDate();
                } else {
                    String NewInput = input.substring(9);
                    String[] ActionTime = NewInput.split("/", 2);
                    lib.deadline(ActionTime);
                }
            } else if (input.contains("event")) {
                CheckInput = input.split(" ");
                if (CheckInput.length < 2) {
                    DE.IncorrectInputEvent();
                } else if (!input.contains("/")) {
                    DE.EventMissingDate();
                } else {
                    String NewInput = input.substring(6);
                    String[] ActionTime = NewInput.split("/", 2);
                    lib.event(ActionTime);
                }
            } else if (input.contains("find")){
                CheckInput = input.split(" ");
                if (CheckInput.length < 2) {
                    DE.InvalidInput();
                } else {
                    String NewInput = input.substring(4).strip();
                    lib.find(NewInput);
                }
            }else {
                DE.InvalidInput();
            }
        } // end sn.hasNext()
    }

//    /**
//     * This is the main method that uses Duke method.
//     * @param args Unused.
//     */
//    public static void main(String[] args) {
//        new Duke("D:/duke/data/d.txt").run();
//    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();                //create a textfield
        sendButton = new Button("Send");       //set button with "send" label

        AnchorPane mainLayout = new AnchorPane();   //the background
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //Step 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        //Scroll down to the end every time dialogContainer's height changes.
        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));

        //Part 3. Add functionality to handle user input.
        sendButton.setOnMouseClicked((event) -> {
            handleUserInput();
        });

        userInput.setOnAction((event) -> {
            handleUserInput();
        });

    }
    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }
}

