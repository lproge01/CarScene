
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.geometry.Insets;
import javafx.scene.layout.Pane;
import javafx.scene.control.Slider;
import javafx.scene.shape.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class Car extends Application
{
    //Shapes for the scene
    Slider sunSlider = new Slider();
    Polygon carBody = new Polygon();
    Rectangle road = new Rectangle();
    Rectangle grass = new Rectangle();
    Rectangle sky = new Rectangle();
    Circle sun = new Circle();
    Circle tire1 = new Circle();
    Circle tire2 = new Circle();
    Polygon window1 = new Polygon();
    Polygon window2 = new Polygon();
    
    @Override
    public void start(Stage stage)
    {
        // Create a new grid pane
        Pane pane = new Pane();
        
        // ***set up the shapes***
        // sky set up
        sky.setX(0);
        sky.setY(0);
        sky.setWidth(800);
        sky.setHeight(500);
        sky.setFill(Color.SKYBLUE);
        
        // sun setup
        sun.setCenterX(400);
        sun.setCenterY(75);
        sun.setRadius(60);
        sun.setFill(Color.YELLOW);
        
        // road setup
        road.setX(0);
        road.setY(450);
        road.setWidth(800);
        road.setHeight(20);
        road.setFill(Color.DARKGRAY);
        
        // grass setup
        grass.setX(0);
        grass.setY(470);
        grass.setWidth(800);
        grass.setHeight(30);
        grass.setFill(Color.GREEN);
        
        // car body set up
        carBody.getPoints().addAll(
            125.0, 425.0,
            125.0, 300.0,
            225.0, 300.0,
            275.0, 200.0,
            425.0, 200.0,
            525.0, 300.0,
            675.0, 300.0,
            675.0, 425.0
        );
        carBody.setFill(Color.RED);
        
        //tire setup
        tire1.setCenterX(250);
        tire1.setCenterY(410);
        tire1.setRadius(50);
        tire1.setFill(Color.rgb(50, 50, 50));
        
        tire2.setCenterX(550);
        tire2.setCenterY(410);
        tire2.setRadius(50);
        tire2.setFill(Color.rgb(50, 50, 50));
        
        //window setup
        //window 1
        window1.getPoints().addAll(
            285.0, 220.0,
            245.0, 300.0,
            345.0, 300.0,
            345.0, 220.0
        );
        window1.setFill(Color.LIGHTBLUE);
        
        //window2
        window2.getPoints().addAll(
            355.0, 220.0,
            355.0, 300.0,
            505.0, 300.0,
            425.0, 220.0
        );
        window2.setFill(Color.LIGHTBLUE);
        
        //slider 
        sunSlider.setMin(-100);
        sunSlider.setMax(900);
        sunSlider.setValue(400);
        
        sunSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            sun.setCenterX(newValue.doubleValue());
            updateSkyColor(newValue.doubleValue());
        });
        
        // Add the everything into the pane
        pane.getChildren().addAll(
            sky, sunSlider, carBody, road, grass, sun, tire1, tire2, 
            window1, window2);

        // JavaFX must have a Scene (window content) inside a Stage (window)
        Scene scene = new Scene(pane, 800, 500);
        stage.setTitle("Car");
        stage.setScene(scene);

        // Show the Stage (window)
        stage.show();
    }
    
    private void updateSkyColor(double sunPosition)
    {
        // find proportion of sun's position
        double prop = (sunPosition + 100) / 1000;
        // change skies color between black and light blue and back
        Color skyColor;
        if (prop <= 0.5) {
            // black to light blue
            skyColor = changeColor(Color.BLACK, Color.SKYBLUE, prop * 2);
        } else {
            // light blue to black
            skyColor = changeColor(Color.SKYBLUE, Color.BLACK, (prop - 0.5) * 2);
        }
        sky.setFill(skyColor);
    }
    
    private Color changeColor(Color color1, Color color2, double proportion)
    {
        double red = color1.getRed() * (1 - proportion) + color2.getRed() * proportion;
        double green = color1.getGreen() * (1 - proportion) + color2.getGreen() * proportion;
        double blue = color1.getBlue() * (1 - proportion) + color2.getBlue() * proportion;
        return new Color(red, green, blue, 1.0);
    }
}
