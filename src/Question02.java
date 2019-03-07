import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Question02 extends Application {

    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {

        // Create a pane and set its properties
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(5));
        pane.setHgap(5.5);
        pane.setVgap(1);

        // Place nodes in the pane
        TextField investmentAmount = new TextField();
        TextField years = new TextField();
        TextField annualInterestRate = new TextField();
        Label futureValue = new Label();

        investmentAmount.setPromptText("0");
        investmentAmount.setAlignment(Pos.BASELINE_RIGHT);
        years.setPromptText("0");
        years.setAlignment(Pos.BASELINE_RIGHT);
        annualInterestRate.setPromptText("0");
        annualInterestRate.setAlignment(Pos.BASELINE_RIGHT);

        futureValue.setText("0  ");
        futureValue.setMinWidth(187);
        futureValue.setMinHeight(31);
        futureValue.setAlignment(Pos.BASELINE_RIGHT);
        futureValue.setStyle("-fx-border-color: lightBlue;" +
                             "-fx-border-radius: 2;");

        pane.add(new Label("Investment Amount:"), 0, 0);
        pane.add(investmentAmount, 1, 0);

        pane.add(new Label("Years:"), 0, 1);
        pane.add(years, 1, 1);

        pane.add(new Label("Annual Interest Rate:"), 0, 2);
        pane.add(annualInterestRate, 1, 2);

        pane.add(new Label("Future Value:"), 0, 3);
        pane.add(futureValue, 1, 3);


        Button btCalc = new Button("Calculate");
        btCalc.setOnAction(e -> {
            futureValue.setText(String.format("%.2f",
                    Double.valueOf(investmentAmount.getText())
                  * Math.pow(1 + Double.valueOf(annualInterestRate.getText()) / 1200,
                    Double.valueOf(years.getText()) * 12)
            ) + "  ");
        });

        pane.add(btCalc, 1, 6);
        GridPane.setHalignment(btCalc, HPos.RIGHT);

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 341, 171);
        primaryStage.setTitle("Investment-Value Calculator"); // Set the stage title
        primaryStage.setScene(scene); // Place the scene in the stage
        primaryStage.show(); // Display the stage
    }

    public static void main(String[] args) {
        launch(args);
    }
}