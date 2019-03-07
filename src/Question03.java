import javafx.application.Application;
import javafx.beans.property.IntegerProperty;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

import java.util.Collections;
import java.util.Random;
import java.util.regex.Pattern;

//import java.awt.event.MouseEvent;


public class Question03 extends Application {
    @Override // Override the start method in the Application class
    public void start(Stage primaryStage) {
        // Create a pane and set its properties
        Pane pane = new Pane();
        Random rand = new Random();
        double deltaX, deltaY;
        double radius = 80;

        Circle circle = new Circle(150,150,radius,Color.WHITE);
        circle.setStroke(Color.BLACK);
        pane.getChildren().add(circle);
        
        SimpleDoubleProperty[] position = new SimpleDoubleProperty[6];
        Circle[] point = new Circle[3];
        double[] lineLength = new double[3];
        double[] angle = new double[3];
        Line[] line = new Line[3];




        for (int i=0; i<3; i++) {
            deltaX = rand.nextDouble() * radius * // randomly generate x coordinate component of the point
                    (rand.nextBoolean() ? 1 : -1 );
            deltaY = Math.sqrt(Math.pow(radius,2) - Math.pow(deltaX,2)) *
                    (rand.nextBoolean() ? 1 : -1 ); // get y coordinate component of point from x coordinate

            point[i] = new Circle();
            point[i].setCenterX(150+deltaX);
            point[i].setCenterY(150+deltaY);
            point[i].setRadius(5);
            point[i].setFill(Color.RED);


            point[i].setStroke(Color.BLACK);
            position[i*2] = new SimpleDoubleProperty(0);
            position[i*2+1] = new SimpleDoubleProperty(0);

            position[i*2].bindBidirectional(point[i].centerXProperty()); // get x coordinate of point
            position[i*2+1].bindBidirectional(point[i].centerYProperty());// get y coordinate of point

            pane.getChildren().add(point[i]);
        }

        for (int i=0; i<2; i++) {
                line[i] = new Line(position[i*2].get(),position[i*2+1].get(),position[(i+1)*2].get(),position[(i+1)*2+1].get());
                line[i].startXProperty().bindBidirectional(position[i*2]);
                line[i].startYProperty().bindBidirectional(position[i*2+1]);
                line[i].endXProperty().bindBidirectional(position[(i+1)*2]);
                line[i].endYProperty().bindBidirectional(position[(i+1)*2+1]);
                pane.getChildren().add(line[i]);
                lineLength[i] = Math.sqrt(
                        Math.pow(Math.abs(position[(i+1)*2].get()-position[i*2].get()), 2)
                      + Math.pow(Math.abs(position[(i+1)*2+1].get()-position[i*2+1].get()), 2)
                );
        }
        line[2] = new Line(position[4].get(),position[5].get(),position[0].get(),position[1].get());
        line[2].startXProperty().bindBidirectional(position[4]);
        line[2].startYProperty().bindBidirectional(position[5]);
        line[2].endXProperty().bindBidirectional(position[0]);
        line[2].endYProperty().bindBidirectional(position[1]);
        lineLength[2] = Math.sqrt(
                Math.pow(Math.abs(position[0].get()-position[4].get()), 2)
              + Math.pow(Math.abs(position[1].get()-position[5].get()), 2)
        );
        pane.getChildren().add(line[2]);
        for (int i=0; i<3; i++) point[i].toFront();

        for (int i = 0; i < 3; i++) {
            angle[i] = Math.acos(
                      (Math.pow(lineLength[i],2)
                     - Math.pow(lineLength[i==0?1:0],2)
                     - Math.pow(lineLength[i==2?1:2],2))
                     / (-2 * lineLength[i==0?1:0] * lineLength[i==2?1:2])
            ) / Math.PI * 180;
        }

        for (int i = 0; i < 3; i++) {
            Label degree;
            if (i<2) {
                degree = new Label(Long.toString(Math.round(angle[i+1])));
            } else {
                degree = new Label(Long.toString(Math.round(angle[0])));
            }

            degree.setLayoutX(point[i].getCenterX() + (point[i].getCenterX()-150>0? -15: 0));
            degree.setLayoutY(point[i].getCenterY() + (point[i].getCenterY()-150>0? -15: 0));
            pane.getChildren().add(degree);
        }

        for (int i = 0; i < 3; i++) {
            final int index = i;
            point[i].setOnMouseDragged(e -> {
                point[index].setLayoutX(e.getSceneX()-point[index].getCenterX());
                point[index].setLayoutY(e.getSceneY()-point[index].getCenterY());

                position[index*2].set(point[index].getCenterX());
                position[index*2+1].set(point[index].getCenterY());

                for (int j=0; j<2; j++) {
                    line[j].startXProperty().set(position[j*2].get());
                    line[j].startYProperty().set(position[j*2+1].get());
                    line[j].endXProperty().set(position[(j+1)*2].get());
                    line[j].endYProperty().set(position[(j+1)*2+1].get());
                    lineLength[j] = Math.sqrt(
                            Math.pow(Math.abs(position[(j+1)*2].get()-position[j*2].get()), 2)
                                    + Math.pow(Math.abs(position[(j+1)*2+1].get()-position[j*2+1].get()), 2)
                    );
                }
                line[2].startXProperty().bindBidirectional(position[4]);
                line[2].startYProperty().bindBidirectional(position[5]);
                line[2].endXProperty().bindBidirectional(position[0]);
                line[2].endYProperty().bindBidirectional(position[1]);
                lineLength[2] = Math.sqrt(
                        Math.pow(Math.abs(position[0].get()-position[4].get()), 2)
                                + Math.pow(Math.abs(position[1].get()-position[5].get()), 2)
                );

                System.out.println(position[2]);

            });


        }

        // Create a scene and place it in the stage
        Scene scene = new Scene(pane, 300, 300);
        primaryStage.setTitle("Dragging Points on a Circle"); // Set the stage title
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