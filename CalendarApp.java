import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class CalendarApp extends Application { 
    public void start(Stage yearlyCalendarStage) 
    { 
        //title of page, opening screen with year
        yearlyCalendarStage.setTitle("Welcome to the Calendar App!");

        

        //navigator Buttons to Yearly, Monthly, and Daily Views
        Button yearlyView = new Button();
        Button monthlyView = new Button();
        Button dailyView = new Button();
        //give the buttons text
        yearlyView.setText("Current Year");
        monthlyView.setText("Current Month");
        dailyView.setText("Current Day");
        
        //create scene
        //Scene yearlyScene = new Scene(200, 200);
    }//end of start
    public static void main(String args[]) 
    { 
        // launch the application 
        launch(args); 
    }//end of main class
}//end of calendarApp class 