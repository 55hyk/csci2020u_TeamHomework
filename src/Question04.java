import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class Question04 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) throws Exception {
        // Create a border pane
        VBox vBox = new VBox(15);
        Pane pane = new Pane();
        HBox hBox = new HBox();
        hBox.setSpacing(10);

        vBox.setPadding(new Insets(15, 5, 5, 5));
        vBox.getChildren().add(pane);

        TextField filePath = new TextField();
        Button btView = new Button("View");
        btView.setOnAction(e -> {
            String fileName = filePath.getText();
            java.io.File file = new java.io.File(fileName);
            try {
                Scanner input = new Scanner(file);
                while (input.hasNext()) {
                    String firstName = input.next();
                    System.out.println(firstName);
                }
                input.close();
            } catch (FileNotFoundException ex) {
                System.out.println("File Not Found");
            }

        });



        hBox.getChildren().addAll(new Label("Filename"), filePath, btView);
        vBox.getChildren().add(hBox);

        // Create a scene and place it in the stage
        Scene scene = new Scene(vBox, 800, 500);
        primaryStage.setTitle("Histogram"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }


    /**
     * The main method is only needed for the IDE with limited
     * JavaFX support. Not needed for running from the command line.
     */
    public static void main(String[] args) {
        launch(args);
    }
} 