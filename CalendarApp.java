import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.Group;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class CalendarApp extends Application { 
    //set the yearly view as the primary stage
    private Stage calendar;
    //different screens of the app
    private Scene yearScene;
    private Scene monthScene;
    private Scene dayScene;
    private Scene taskScene;
    //layout for navigation bar
    private HBox navigationBar;
    public static void main(String args[]) 
    { 
        // launch the application 
        launch(args); 
    }//end of main 

    @Override
    public void start(Stage primaryStage) throws Exception{ 
        calendar = primaryStage;
        
        //set title
        calendar.setTitle("Calendar App");

        yearScene = yearlyScene();
        monthScene = monthlyScene();
        dayScene = dailyScene();

        calendar.setScene(yearScene);

        //display stage
        calendar.show();
    }//end of start()

    private Scene yearlyScene(){
        //navigator Buttons to Yearly, Monthly, and Daily Views
        Button yearlyView = new Button("Current Year");
        Button monthlyView = new Button("Current Month");
        Button dailyView = new Button("Current Day");
        //add event handler to switch between scenes
        yearlyView.setOnAction(e -> switchScenes(yearScene)); 
        monthlyView.setOnAction(e -> switchScenes(monthScene));
        dailyView.setOnAction(e -> switchScenes(dayScene));
        
        //create HBox for layout of navigation bar
        navigationBar = new HBox(30, yearlyView, monthlyView, dailyView);
        navigationBar.setPadding(new Insets(30));
        //set alignment
        navigationBar.setAlignment(Pos.BOTTOM_CENTER);

        //set color
        navigationBar.setStyle("-fx-background-color: green");
        
        //creates scene 450 pixels wide by 750 pixels tall
        yearScene = new Scene(navigationBar, 450, 750);
        yearScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");

        return yearScene;
    }//end of yearScene()

    private Scene monthlyScene(){
        //navigator Buttons to Yearly, Monthly, and Daily Views
        Button yearlyView = new Button("Current Year");
        Button monthlyView = new Button("Current Month");
        Button dailyView = new Button("Current Day");
        //add event handler to switch between scenes
        yearlyView.setOnAction(e -> switchScenes(yearScene)); 
        monthlyView.setOnAction(e -> switchScenes(monthScene));
        dailyView.setOnAction(e -> switchScenes(dayScene));
        
        //create HBox for layout of navigation bar
        navigationBar = new HBox(30, yearlyView, monthlyView, dailyView);
        navigationBar.setPadding(new Insets(30));
        //set alignment
        navigationBar.setAlignment(Pos.BOTTOM_CENTER);

        //set color
        navigationBar.setStyle("-fx-background-color: green");
        
        //creates scene 450 pixels wide by 750 pixels tall
        monthScene = new Scene(navigationBar, 450, 750);
        monthScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return monthScene;
    }//end of monthlyScene()

    private Scene dailyScene(){
        //navigator Buttons to Yearly, Monthly, and Daily Views
        Button yearlyView = new Button("Current Year");
        Button monthlyView = new Button("Current Month");
        Button dailyView = new Button("Current Day");
        //add event handler to switch between scenes
        yearlyView.setOnAction(e -> switchScenes(yearScene)); 
        monthlyView.setOnAction(e -> switchScenes(monthScene));
        dailyView.setOnAction(e -> switchScenes(dayScene));
        
        //create HBox for layout of navigation bar
        navigationBar = new HBox(30, yearlyView, monthlyView, dailyView);
        navigationBar.setPadding(new Insets(30));
        //set alignment
        navigationBar.setAlignment(Pos.BOTTOM_CENTER);

        //set color
        navigationBar.setStyle("-fx-background-color: green");
        
        //creates scene 450 pixels wide by 750 pixels tall
        dayScene = new Scene(navigationBar, 450, 750);
        dayScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return dayScene;
    }//end of dailyScene()

    private Scene taskScene(){
        return taskScene;
    }//end of taskScene()

    //method to switch between scenes
    public void switchScenes(Scene tempScene){
        calendar.setScene(tempScene);
    }//end of switchScenes()
}//end of calendarApp class 