//for layouts and stuff
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
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.text.*;
import javafx.scene.control.ScrollPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
//for calendar
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
//for tasks
import java.util.ArrayList;
import java.util.List;

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
    //ArrayList to hold tasks from taskAdder
    private List<taskAdder> toDoList = new ArrayList<>();
    
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
        taskScene = taskScene();

        calendar.setScene(yearScene);

        //display stage
        calendar.show();
    }//end of start()

    private Scene yearlyScene(){
        //get current year
        int currentYear = LocalDate.now().getYear();

        //display year
        Text theYear = new Text();
        //set font
        theYear.setFont(new Font(50));
        //set text
        theYear.setText("Current Year: " + currentYear);
        
        //change year button
        Button changeYear = new Button("Change Year");

        //VBox to set layout for year and button
        VBox yearButton = new VBox();
        yearButton.getChildren().addAll(theYear, changeYear);
        //set spacing
        yearButton.setPadding(new Insets(30));
        //set alignment
        yearButton.setAlignment(Pos.TOP_CENTER);
        //set background color
        yearButton.setStyle("-fx-background-color: green");

        //display yearly calendar in a grid structure
        GridPane months = new GridPane();
        months.setHgap(20);
        months.setVgap(20);
        //add calendars for each month into the GridPane
        //i=month
        for (int i=1; i<=12; i++){
            //create calendar view for each month
            VBox monthDisplay = createMonthlyCalendar(currentYear, i);
            //find position in grid (doing standard 3x4 matrix view)
            int row = (i-1)/3;
            int col = (i-1)%3;
            //add new calendar into the grid
            months.add(monthDisplay, col, row);
        }//end of for loop
        //scrollable feature (used chatGPT to write)
        ScrollPane scroller = new ScrollPane(months);
        
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
        
        //layout: put calendar above navigation
        VBox layout = new VBox();
        layout.getChildren().addAll(yearButton, scroller, navigationBar);

        //creates scene 800 pixels wide by 800 pixels tall
        yearScene = new Scene(layout, 800, 800);
        //yearScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
    
        return yearScene;
    }//end of yearScene()

    private Scene monthlyScene(){
        //get current year and month
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();

        //display current month in text
        //can you tell my variable names are getting worse lol
        Text theMonth = new Text();
        theMonth.setFont(new Font(50)); //set font size
        theMonth.setText(YearMonth.of(currentYear, currentMonth).getMonth().toString()); //set text
        
        //back button
        Button back = new Button("Back");
        back.setOnAction(e -> switchScenes(yearScene)); //add event handler

        //HBox to display header
        HBox header = new HBox(100, back, theMonth);
        header.setPadding(new Insets(30));
        header.setAlignment(Pos.TOP_CENTER); //set alignment
        header.setStyle("-fx-background-color: green"); //set background color to green

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
        navigationBar.setAlignment(Pos.BOTTOM_CENTER); //set alignment
        navigationBar.setStyle("-fx-background-color: green"); //set color
        
        /* 
            monthly calendar
            call monthly calendar method
            VBox to set layout for everything
        */
        VBox mainContent = new VBox(30, header, createMonthlyCalendar(currentYear, currentMonth), navigationBar);
        mainContent.setPadding(new Insets(30));
        mainContent.setAlignment(Pos.CENTER); //set alignment

        //creates scene 450 pixels wide by 450 pixels tall, smaller due to less calendars
        monthScene = new Scene(mainContent, 450, 450);
        //monthScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return monthScene;
    }//end of monthlyScene()

    private Scene dailyScene(){
        //title of page
        Text title = new Text();
        //set font size
        title.setFont(new Font(50));
        //set text
        title.setText(" " + LocalDate.now());

        //back button
        Button back = new Button("Back");
        //add event handler
        back.setOnAction(e -> switchScenes(monthScene));
        
        //HBox for header made up of title and back button
        HBox header = new HBox(30, back, title);
        header.setPadding(new Insets(30));
        //set alignment
        header.setAlignment(Pos.TOP_CENTER);
        header.setStyle("-fx-background-color: green");

        //display current day
        Text theDay = new Text();
        //set text font size
        theDay.setFont(new Font(25));
        //set text
        theDay.setText(LocalDate.now().getDayOfWeek().toString());

        //add task button
        //sends user to task screen
        Button addTask = new Button("Add Task");
        //add event handler
        addTask.setOnAction(e -> switchScenes(taskScene));

        //delete button
        Button deleteTask = new Button("Delete Task");
        
        //HBox for current date, add button, and delete button for layout purposes
        //(D)ate + (A)dd task + (D)elete Task = DAD
        HBox DAD = new HBox(30, deleteTask, theDay, addTask);
        DAD.setPadding(new Insets(30));
        //set alignment
        DAD.setAlignment(Pos.CENTER);

        //task list
        Text list = new Text("To Do List: ");

        //day scroller how to implement?
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

        //VBox to hold it all togther
        VBox mainContent = new VBox(30, header, DAD, list, navigationBar);
        mainContent.setPadding(new Insets(30));
        //set alignment
        mainContent.setAlignment(Pos.CENTER);
        
        //creates scene 800 pixels wide by 800 pixels tall
        dayScene = new Scene(mainContent, 800, 800);
        //dayScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return dayScene;
    }//end of dailyScene()

    private Scene taskScene(){
        //each label will be placed in an HBox with 
        //title of screen
        Text title = new Text();

        //set font
        title.setFont(new Font(50));
        //set text
        title.setText("Task");

        //VBox for header and layout
        VBox header = new VBox(title);
        //set alignment
        header.setAlignment(Pos.CENTER);
        header.setStyle("-fx-background-color: green");

        //name label
        Label name = new Label("Name: ");
        TextField taskName = new TextField();

        //time label
        Label time = new Label("Time: ");

        //notes label
        Label notes = new Label("Notes: ");

        //cancel button
        Button cancel = new Button("Cancel");
        //add event handler
        //canceling sends user back to daily task scene
        cancel.setOnAction(e -> switchScenes(dayScene));

        //create button
        Button create = new Button("Create");
        //add event handler
        //create will send user back to update daily task scene 
        create.setOnAction(e -> switchScenes(dayScene));

        //HBox for layout
        HBox buttons = new HBox(30, cancel, create);
        buttons.setPadding(new Insets(30));
        //set alignment
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        //Vbox to wrap everything
        VBox mainContent = new VBox(30, header, taskName, taskTime, taskNotes, buttons);
        mainContent.setPadding(new Insets(30));
        //set alignment
        mainContent.setAlignment(Pos.CENTER);

        //creates scene 800 pixels wide by 800 pixels tall
        taskScene = new Scene(mainContent, 800, 800);
        //taskScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return taskScene;
    }//end of taskScene()

    //method to switch between scenes
    public void switchScenes(Scene tempScene){
        calendar.setScene(tempScene);
    }//end of switchScenes()

    /* 
    creates a calendar that returns in the month with names and day in grid format
    used suggestions from chatGPT
    */
    private VBox createMonthlyCalendar(int year, int month){
        //calendar layout
        VBox calendar = new VBox(5);
        calendar.setStyle("-fx-padding: 10; -fx-border-color: black;");
        //name of the month
        Label whatMonth = new Label(YearMonth.of(year, month).getMonth().toString());
        //calendar grid
        GridPane mCalendar = new GridPane();
        mCalendar.setHgap(5);
        mCalendar.setVgap(5);

        //add headers for each day of the week
        DayOfWeek[] days = DayOfWeek.values();
        int tempNum = days.length-1;
        //add Sunday first
        String sunday = days[tempNum].toString().substring(0,3);
        mCalendar.add(new Label(sunday), tempNum, 0);
        for(int i=0; i<tempNum; i++){
            String name = days[i].toString().substring(0,3);
            mCalendar.add(new Label(name), i, 0);
        }//end of for loop

        //get month
        YearMonth yearMonth = YearMonth.of(year, month);
        //get number of days in a month
        int numDays = yearMonth.lengthOfMonth();
        //get first day of the month
        LocalDate firstDay = yearMonth.atDay(1);

        //calculate starting column index for first day
        int startDay = firstDay.getDayOfWeek().getValue()%7;

        //add in dates
        int row = 1;
        int col = startDay;
        for (int j=1; j<=numDays; j++){
            //label for the day
            mCalendar.add(new Label(String.valueOf(j)), col, row);
            col++;
            //check if should move to next column so it doesn't go past Saturday
            if(col>6){
                col=0;
                row++;
            }//end of if statement
        }//end of for loop
        //add title and calendar to the monthly calendar
        calendar.getChildren().addAll(whatMonth, mCalendar);
        return calendar;
    }//end of createMonthlyCalendar()

    //methods to hold task data
    

}//end of calendarApp class 