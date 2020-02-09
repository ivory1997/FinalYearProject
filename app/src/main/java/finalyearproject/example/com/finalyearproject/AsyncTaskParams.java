package finalyearproject.example.com.finalyearproject;

import java.util.ArrayList;

public class AsyncTaskParams {
    String task;
    String email;
    String name;
    ArrayList<String> friends = new ArrayList<>();
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    public AsyncTaskParams(String task,String email, String name, ArrayList<String> countries,ArrayList<String> countryNames){
        this.task = task;
        this.email = email;
        this.name = name;
        this.countries = countries;
        this.countryNames = countryNames;
    }
    public AsyncTaskParams(String task,String email, String name, ArrayList<String> countries,ArrayList<String> countryNames, ArrayList<String> friends){
        this.task = task;
        this.email = email;
        this.name = name;
        this.countries = countries;
        this.countryNames = countryNames;
        this.friends = friends;
    }
}
