//for layouts and stuff
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.shape.Line;
import javafx.scene.control.*;
import javafx.scene.Group;
import javafx.scene.text.*;
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
    public ArrayList<taskAdder> toDoList = new ArrayList<>();
    
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

        //creates scene 550 pixels wide by 800 pixels tall
        yearScene = new Scene(layout, 550, 800);
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
        HBox header = new HBox(30, back, theMonth);
        header.setPadding(new Insets(30));
        header.setAlignment(Pos.CENTER); //set alignment
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

        //creates scene 425 pixels wide by 425 pixels tall, smaller due to less calendars
        monthScene = new Scene(mainContent, 450, 425);
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

        //display the To Do List
        Text list = new Text("To Do List: ");
        VBox taskList = new VBox(10);
        taskList.setPadding(new Insets(10));
        taskList.setAlignment(Pos.CENTER); //set alignment
        //fill list with tasks
        updateTaskList(taskList, toDoList);
        //scroller in case of too many tasks
        ScrollPane scroller = new ScrollPane(taskList);

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
        VBox mainContent = new VBox(30, header, theDay, addTask, list, scroller, navigationBar);
        mainContent.setPadding(new Insets(30));
        //set alignment
        mainContent.setAlignment(Pos.CENTER);
        
        //creates scene 650 pixels wide by 650 pixels tall
        dayScene = new Scene(mainContent, 650, 650);
        //dayScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return dayScene;
    }//end of dailyScene()

    private Scene taskScene(){
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

        //default text to avoid input validation
        //name of the task
        TextField taskName = new TextField("Name: ");
        //time to do task
        TextField taskTime = new TextField("Due By: ");
        //notes on the task
        TextField taskDetails = new TextField("Notes: ");

        //cancel button
        Button cancel = new Button("Cancel");
        //change button color
        cancel.setStyle("-fx-background-color: red");
        //add event handler
        //canceling sends user back to daily task scene
        cancel.setOnAction(e -> switchScenes(dayScene));
        //create button
        Button create = new Button("Create");
        //change button color
        create.setStyle("-fx-background-color: green");
        //add event handler
        //create will send user back to update daily task scene 
        //action event created with help of ChatGPT
        create.setOnAction(e -> {
            //get user input
            String name = taskName.getText();
            String time = taskTime.getText();
            String details = taskDetails.getText();
            //create the task and add it to the to do list
            taskAdder myTask = new taskAdder(name, time, details);
            //add to list
            toDoList.add(myTask);
            //reset values
            taskName.setText("Name");
            taskTime.setText("Due By: ");
            taskDetails.setText("Notes: ");
            switchScenes(dayScene);
        });

        //HBox for layout
        HBox buttons = new HBox(30, cancel, create);
        buttons.setPadding(new Insets(30));
        //set alignment
        buttons.setAlignment(Pos.BOTTOM_CENTER);

        //Vbox to wrap everything
        VBox mainContent = new VBox(30, header, taskName, taskTime, taskDetails, buttons);
        mainContent.setPadding(new Insets(30));
        //set alignment
        mainContent.setAlignment(Pos.CENTER);

        //creates scene 450 pixels wide by 450 pixels tall
        taskScene = new Scene(mainContent, 450, 450);
        //taskScene.getStylesheets().add("/com/javacodejunkie/stylesheet.css");
        return taskScene;
    }//end of taskScene()

    //method to switch between scenes
    public void switchScenes(Scene tempScene){
        //check if tempScene is dayScene
        //dayScene must be constantly refreshed to ensure accurate task display
        //ChatGPT suggestion after numerous failed builds
        if(tempScene == dayScene){
            dayScene = dailyScene(); //rebuild
            calendar.setScene(dayScene);
        }//end of if
        else{
            calendar.setScene(tempScene);
        }//end of else
    }//end of switchScenes()

    /* 
    creates a calendar that returns in the month with names and day in grid format
    used suggestions from chatGPT
    */
    private VBox createMonthlyCalendar(int year, int month){
        //calendar layout
        VBox calendar = new VBox(5);
        calendar.setStyle("-fx-padding: 10; -fx-border-color: black;");
        calendar.setAlignment(Pos.CENTER);
        //name of the month
        Label whatMonth = new Label(YearMonth.of(year, month).getMonth().toString());
        whatMonth.setAlignment(Pos.CENTER);
        //calendar grid
        GridPane mCalendar = new GridPane();
        mCalendar.setHgap(5);
        mCalendar.setVgap(5);
        mCalendar.setAlignment(Pos.CENTER);

        //add headers for each day of the week
        String[] days = {"S", "M", "T", "W", "T", "F", "S"};
        for(int i=0; i<days.length; i++){
            mCalendar.add(new Label(days[i]), i, 0);
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
            Label day = new Label(String.valueOf(j));
            //set size constraints
            day.setMinSize(15,15);
            day.setMaxSize(30,30);
            day.setAlignment(Pos.CENTER);
            mCalendar.add(day, col, row);
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

    //refresh task list diplay, made with the help of ChatGPT
    private static void updateTaskList(VBox b, ArrayList<taskAdder> l){
        //clears current task list display
        b.getChildren().clear();
        //for delete button
        for(taskAdder task: l){
            VBox t = new VBox(5);
            t.setPadding(new Insets(5));
            t.setAlignment(Pos.CENTER);
            t.setStyle("-fx-border-color: gray; -fx-padding: 10;");
            //display task details
            Label name = new Label("Task: " + task.getName());
            Label time = new Label("Due By: " + task.getTime());
            Label details = new Label("Details: " + task.getDetails());
            //add delete button, second functionality
            Button delete = new Button("Delete Task");
            //change button color
            delete.setStyle("-fx-background-color: red");
            //add action event, deletes data from list
            delete.setOnAction(e -> {
                l.remove(task);
                updateTaskList(b, l);
            });
            //add to VBox t for formatting
            t.getChildren().addAll(name, time, details);
            //HBox for delete button formatting
            HBox f = new HBox(30, t, delete);
            f.setPadding(new Insets(30));
            f.setAlignment(Pos.CENTER);
            //add to original VBox
            b.getChildren().add(f);
        }//end of for loop
    }//end of updateTaskList()
}//end of calendarApp class 