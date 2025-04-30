import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class CalendarApp extends Application { 
    public static void main(String args[]) 
    { 
        // launch the application 
        launch(args); 
    }//end of main class
    @Override
    public void start(Stage yearlyCalendarStage) 
    { 
        //name of stage
        Label yearLabel = new Label("Welcome to the Calendar App!");

        //navigator Buttons to Yearly, Monthly, and Daily Views
        Button yearlyView = new Button("Current Year");
        Button monthlyView = new Button("Current Month");
        Button dailyView = new Button("Current Day");
        //create HBox for layout of navigation bar
        HBox navigationBar = new HBox(10, yearlyView, monthlyView, dailyView);
        navigationBar.setPadding(new Insets(10));
        //create scene (750 pixels wide by 1334 pixels high, dimensions of my phone)
        Scene yearlyScene = new Scene(navigationBar, 750, 1334);
        //add yearlyScene to yearlyCalendarStage
        yearlyCalendarStage.setScene(yearlyScene);
        //set title for stage
        yearlyCalendarStage.setTitle("Yearly View");
        
        //display stage
        yearlyCalendarStage.show();
    }//end of start
}//end of calendarApp class 