import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.Random;

public class Question01 extends Application {
    @Override
    public void start(Stage primaryStage) {

        Pane pane = new HBox(5);
        pane.setPadding(new Insets(1,1,1,1));

        Random rand = new Random();
        int[] card = new int[3];

        for (int i=0; i<3; i++) {
            card[i] = rand.nextInt(54) + 1;
            String imagePath = "Cards/" + card[i] + ".png";
            Image image = new Image(imagePath);
            pane.getChildren().add(new ImageView(image));
        }

        Scene scene = new Scene(pane);
        primaryStage.setTitle("Displaying Three Cards");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}