package finalyearproject.example.com.finalyearproject;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.provider.Settings;
import android.util.Base64;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

public class Globals extends Application {
    private static Globals instance;
    public void onCreate() {
        super.onCreate();
        instance = this;
    }
    public static Globals getConfig() {
        return instance;
    }
    private String profilePicString  = "";
    private Bitmap profilePicBitmap;
    private Bitmap flagBitmap;
    //private String urlProfilePic  = "https://c16307271.000webhostapp.com/profilePic.php";
    private String email = "";
    private String name = "";
    private String population = "";
    private String capital = "";
    private String countryNameRandom = "Ireland";
    private String countryNameRandom2 = "";

    private ArrayList<String> friendCountries = new ArrayList<>();
    private ArrayList<String> friendCountryNames = new ArrayList<>();
    //byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
    //private Bitmap profilePicBitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);

    public String getData(){
        return this.profilePicString;
    }

    public void setData(String d){
        this.profilePicString=d;
    }
    public void setEmail(String e){
        this.email=e;
    }
    public void setName(String n){
        this.name=n;
    }
    public void setProfilePicBitmap(Bitmap bitm){
        this.profilePicBitmap = bitm;
    }
    public Bitmap getProfilePicBitmap(){
        return this.profilePicBitmap;
    }

    public void setFlagBitmap(Bitmap fbitm){
        this.flagBitmap = fbitm;
    }
    public Bitmap getFlagBitmap(){
        return this.flagBitmap;
    }

    public ArrayList<String> getFriendCountries(){
        return this.friendCountries;
    }
    public void setFriendCountries(ArrayList<String> fc){
        this.friendCountries=fc;
    }

    public ArrayList<String> getFriendCountryNames(){
        return this.friendCountryNames;
    }
    public void setFriendCountryNames(ArrayList<String> fcn){
        this.friendCountryNames=fcn;
    }

    public void setPop(String pop){
        this.population=pop;
    }
    public void setCap(String cap){
        this.capital=cap;
    }
    public String getPop(){
        return this.population;
    }
    public String getCap(){
        return this.capital;
    }

    public void setCountryNameRandom(String countryNameRandom){
        this.countryNameRandom=countryNameRandom;
    }
    public String getCountryNameRandom(){
        return this.countryNameRandom;
    }

    public void setCountryNameRandom2(String countryNameRandom2){
        this.countryNameRandom2=countryNameRandom2;
    }
    public String getCountryNameRandom2(){
        return this.countryNameRandom2;
    }

}
