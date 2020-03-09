package finalyearproject.example.com.finalyearproject;

import java.util.ArrayList;

public class AsyncTaskParams {
    String task;
    String email;
    String name;
    String profilePicString;
    String friendName;
    ArrayList<String> friends = new ArrayList<>();
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    public AsyncTaskParams(String task,String email, String name, String profilePicString, ArrayList<String> countries,ArrayList<String> countryNames){
        this.task = task;
        this.email = email;
        this.name = name;
        this.profilePicString = profilePicString;
        this.countries = countries;
        this.countryNames = countryNames;

    }
    public AsyncTaskParams(String task,String email, String name, String friendName, String profilePicString, ArrayList<String> countries,ArrayList<String> countryNames){
        this.task = task;
        this.email = email;
        this.name = name;
        this.friendName = friendName;
        this.profilePicString = profilePicString;
        this.countries = countries;
        this.countryNames = countryNames;

    }
    public AsyncTaskParams(String task,String email, String name,String profilePicString, ArrayList<String> countries,ArrayList<String> countryNames, ArrayList<String> friends){
        this.task = task;
        this.email = email;
        this.name = name;
        this.profilePicString = profilePicString;
        this.countries = countries;
        this.countryNames = countryNames;
        this.friends = friends;
    }
}
