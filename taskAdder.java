import javafx.application.Application;
//for tasks
import java.util.ArrayList;
import java.util.List;

public class taskAdder{
    //hold data
    private String name;
    private String time;
    private String details;

    //default constructor
    public taskAdder(){
        name = "name";
        time = "00:00";
        details = "info";
    }//end of default constructor

    //constructor
    public taskAdder(String n, String t, String d){
        name = n;
        time = t;
        details = d;
    }//end of constructor

    //returns name of task
    public String getName(){
        return name;
    }//end of getName()

    //returns time of task
    public String getTime(){
        return time;
    }//end of getTime()

    //returns details of taks
    public String getDetails(){
        return details;
    }//end of getDetails()
}//end of taskAdder