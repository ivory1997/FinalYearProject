package finalyearproject.example.com.finalyearproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.text.format.Formatter;
import android.util.Base64;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

import static android.support.v4.content.ContextCompat.getSystemService;



public class ConnectDBPassArray extends AsyncTask<AsyncTaskParams,Void,String>{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;
    Context context;
    private String line1;
    String email;
    String name;
    String friendName;
    String profilePicString;
    ArrayList<String> friends = new ArrayList<>();
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ConnectDBPassArray(Context ctx){
        this.context = ctx;

    }
    @Override
    protected String doInBackground(AsyncTaskParams... params) {

        preferences = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();

        String urlFriends  = "https://c16307271.000webhostapp.com/getFriends.php";
        String urlAddFriends  = "https://c16307271.000webhostapp.com/getUserNames.php";
        String urlCountryRecommender = "https://c16307271.000webhostapp.com/countryRecommender.php";
        String urlProfilePic  = "https://c16307271.000webhostapp.com/profilePic.php";
        String urlConfirmAddFriend = "https://c16307271.000webhostapp.com/confirmAddFriend.php";
        String urlViewFriendsMap = "https://c16307271.000webhostapp.com/viewFriendsMap.php";

        String task = params[0].task;
        countries = params[0].countries;
        countryNames = params[0].countryNames;
        friends = params[0].friends;
        email = params[0].email;
        name = params[0].name;
        friendName = params[0].friendName;
        String profilePicString = params[0].profilePicString;
        if(task.equals("friends")) {
            try {
                //String email = params[0].email;
                //String name = params[0].name;
                Log.e("email test", email + "");
                Log.e("name test", name + "");
                URL url = new URL(urlFriends);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                result += profilePicString + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","friends");
                editor.commit();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("addFriends")) {

            try {
                //String email = params[0].email;
                //String name = params[0].name;
                Log.e("email test2", email + "");
                Log.e("name test2", name + "");
                URL url = new URL(urlAddFriends);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                result += profilePicString + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","addFriends");
                editor.commit();
                Log.e("email test3", email + "");
                Log.e("name test3", name + "");
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("confirmAddFriend")){
            //String userEmail = params[1];
            //String userName = params[2];
            //String friendName = params[3];

            try {
                URL url = new URL(urlConfirmAddFriend);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_userEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_userName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_friendName","UTF-8")+"="+URLEncoder.encode(friendName,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","confirmAddFriend");
                editor.commit();
                String result="";
                result = "Successfully added user " + friendName;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(task.equals("viewFriendsMap")) {
            try {
                //String profilePicString = params[3];
                //Log.e("pictureString3", profilePicString+"");
                URL url = new URL(urlViewFriendsMap);
                Log.e("friendName", friendName + "");
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_userEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_userName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_friendName","UTF-8")+"="+URLEncoder.encode(friendName,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                result += profilePicString + ",";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","viewFriendsMap");
                editor.commit();
                Log.e("result", result + "");
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("recommend")) {
            try {
                //String email = params[0].email;
                //String name = params[0].name;
                Log.e("email test", email + "");
                Log.e("name test", name + "");
                URL url = new URL(urlCountryRecommender);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8");
                bufferedWriter.write(post_data);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream,"iso-8859-1"));
                String result="";
                String line="";
                while((line = bufferedReader.readLine())!= null) {
                    result += line;
                }
                bufferedReader.close();
                inputStream.close();
                httpURLConnection.disconnect();
                editor.putString("flag","recommend");
                editor.commit();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("profilePic")){

            try {
                Globals g = Globals.getConfig();
                profilePicString = g.getData();
                Log.e("PROFILEPICUPDATE", profilePicString + "");
                Bitmap profilePicBitmap = g.getProfilePicBitmap();
                ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                profilePicBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
                byte [] arr=baos.toByteArray();
                String profilePicString2=Base64.encodeToString(arr, Base64.DEFAULT);
                Log.e("PROFILEPICUPDATE2", profilePicString2 + "");
                URL url = new URL(urlProfilePic);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                String myData = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(email,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginName","UTF-8")+"="+URLEncoder.encode(name,"UTF-8")+"&"
                        +URLEncoder.encode("profile_picture","UTF-8")+"="+URLEncoder.encode(profilePicString2,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","profilePic");
                editor.commit();
                String result="";
                result += profilePicString + ",";
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }


        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //alertDialog = new AlertDialog.Builder(context).create();
        //alertDialog.setTitle("Login Status");
    }

    @Override
    protected void onPostExecute(String result) {
        String flag = preferences.getString("flag","0");



        if(flag.equals("friends"))
        {
            String test = "false";
            //String email = "";
            //String name = "";
            String profilePicString = "";
            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> friends = new ArrayList<>();
            profilePicString = serverResponse[0];
            test = serverResponse[1];
            //name  = serverResponse[1];
            Log.e("server response 0", serverResponse[0]+"");
            //Log.e("server response 1", serverResponse[1]+"");
            //Log.e("server response 2", serverResponse[2]+"");
            //Log.e("server response 3", serverResponse[3]+"");
            if(serverResponse.length > 2)
            {
                for (int i = 2; i < serverResponse.length - 1; i++) {
                    friends.add(serverResponse[i]);

                }
            }


                Intent FriendListIntent = new Intent(context,FriendsListActivity.class);
                FriendListIntent.putExtra("email",email);
                FriendListIntent.putExtra("name",name);
                FriendListIntent.putExtra("profilePicString",profilePicString);
                FriendListIntent.putStringArrayListExtra("friends", friends);
                FriendListIntent.putStringArrayListExtra("countries", countries);
                FriendListIntent.putStringArrayListExtra("countryNames", countryNames);
                context.startActivity(FriendListIntent);



        }
        else if(flag.equals("confirmAddFriend")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            String task = "friends";
            ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(context);
            AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
            connectDBPassArray.execute(AsyncTaskParams);
        }
        else if(flag.equals("addFriends"))
        {
            String test = "false";
            //String email = "";
            //String name = "";
            String profilePicString = "";
            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> usernames = new ArrayList<>();
            profilePicString = serverResponse[0];
            test = serverResponse[1];
            //name  = serverResponse[1];
            Log.e("server response 0", serverResponse[0]+"");
            Log.e("server response 1", serverResponse[1]+"");
            Log.e("server response 2", serverResponse[2]+"");
            Log.e("server response 3", serverResponse[3]+"");
            if(serverResponse.length > 2)
            {
                for (int i = 2; i < serverResponse.length - 1; i++) {
                    usernames.add(serverResponse[i]);

                }
            }


            Intent AddFriendsIntent = new Intent(context,AddFriendsActivity.class);
            AddFriendsIntent.putExtra("email",email);
            AddFriendsIntent.putExtra("name",name);
            AddFriendsIntent.putExtra("profilePicString",profilePicString);
            AddFriendsIntent.putStringArrayListExtra("usernames", usernames);
            AddFriendsIntent.putStringArrayListExtra("friends", friends);
            AddFriendsIntent.putStringArrayListExtra("countries", countries);
            AddFriendsIntent.putStringArrayListExtra("countryNames", countryNames);
            context.startActivity(AddFriendsIntent);



        }
        else if(flag.equals("profilePic"))
        {
            String test = "false";
            //String email = "";
            //String name = "";
            String profilePicString = "";
            //String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            //profilePicString = serverResponse[0];
            profilePicString = result;
            //byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
            Log.e("profilePicStringTesting", profilePicString+"");
            Log.e("emailTest", email+"");
            Log.e("nameTest", name+"");
            Log.e("countriesTest", countries.get(0)+"");
            Log.e("countryNamesTest", countryNames.get(0)+"");



            Intent profileIntent = new Intent(context,ViewProfile.class);
            profileIntent.putExtra("email",email);
            profileIntent.putExtra("name",name);
            //profileIntent.putExtra("profilePicString",profilePicString);
            profileIntent.putStringArrayListExtra("countries", countries);
            profileIntent.putStringArrayListExtra("countryNames", countryNames);
            context.startActivity(profileIntent);



        }
        else if(flag.equals("viewFriendsMap"))
        {
            String test = "false";
            //String email = "";
            //String name = "";
            String friendEmail = "";
            //String profilePicString = "";
            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> friendCountries = new ArrayList<>();
            ArrayList<String> friendCountryNames = new ArrayList<>();
            //profilePicString = serverResponse[0];
            test = serverResponse[1];
            friendEmail = serverResponse[2];
            //name = serverResponse[3];

            for(int i = 4; i < serverResponse.length; i+=2)
            {
                friendCountryNames.add(serverResponse[i]);
                friendCountries.add(serverResponse[i+1]);
                //countries[i] = serverResponse[i];

            }
            Globals g = Globals.getConfig();
            g.setFriendCountries(friendCountries);
            g.setFriendCountryNames(friendCountryNames);
            //(Globals) globals.this.getApplication()).setData(profilePicString);
            //Globals g = (Globals).getApplication();
            //Globals g = Globals.getConfig();
            //g.setData(profilePicString);


            if(test.contains("true")){
                Intent FriendChartIntent = new Intent(context,FriendChartActivity.class);
                FriendChartIntent.putExtra("email",email);
                FriendChartIntent.putExtra("name",name);
                FriendChartIntent.putExtra("friendName",friendName);
                //ChartIntent.putExtra("profilePicString",profilePicString);
                FriendChartIntent.putStringArrayListExtra("countries", countries);
                FriendChartIntent.putStringArrayListExtra("countryNames", countryNames);
                context.startActivity(FriendChartIntent);
            }


        }
        else if(flag.equals("recommend"))
        {
            String test = "false";
            //String email = "";
            //String name = "";

            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> friends = new ArrayList<>();

            test = serverResponse[0];
            //name  = serverResponse[1];
            Log.e("Recommender 0", serverResponse[0]+"");
            Log.e("Recommender 1", serverResponse[1]+"");
            //Log.e("server response 2", serverResponse[2]+"");
            //Log.e("server response 3", serverResponse[3]+"");
            String recommendedCountry = serverResponse[1];

            Intent CountryRecommenderIntent = new Intent(context,CountryRecommenderActivity.class);
            CountryRecommenderIntent.putExtra("email",email);
            CountryRecommenderIntent.putExtra("name",name);
            CountryRecommenderIntent.putExtra("recommendedCountry",recommendedCountry);
            CountryRecommenderIntent.putStringArrayListExtra("friends", friends);
            CountryRecommenderIntent.putStringArrayListExtra("countries", countries);
            CountryRecommenderIntent.putStringArrayListExtra("countryNames", countryNames);
            context.startActivity(CountryRecommenderIntent);





        }
        /*
        else
            {
                Intent BacktoLogin = new Intent(context,LoginActivity.class);
                context.startActivity(BacktoLogin);
                Toast.makeText(context,"Incorrect email address and password combination",Toast.LENGTH_LONG).show();
                //display("Login Failed...", "That email and password do not match our records :(.");
            }
*/


        }









    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

    public void display(String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }



}

