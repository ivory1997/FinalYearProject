package finalyearproject.example.com.finalyearproject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.renderscript.ScriptGroup;
import android.text.format.Formatter;
import android.util.Log;
import android.webkit.URLUtil;
import android.widget.Toast;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import java.io.BufferedReader;
import java.io.BufferedWriter;
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



public class ConnectDBPassArray  extends AsyncTask<AsyncTaskParams,Void,String>{

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;
    Context context;
    private String line1;
    String email;
    String name;
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

        String task = params[0].task;
        countries = params[0].countries;
        countryNames = params[0].countryNames;
        friends = params[0].friends;
        email = params[0].email;
        name = params[0].name;
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

            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> friends = new ArrayList<>();

            test = serverResponse[0];
            //name  = serverResponse[1];
            Log.e("server response 0", serverResponse[0]+"");
            //Log.e("server response 1", serverResponse[1]+"");
            //Log.e("server response 2", serverResponse[2]+"");
            //Log.e("server response 3", serverResponse[3]+"");
            if(serverResponse.length > 2)
            {
                for (int i = 1; i < serverResponse.length - 1; i++) {
                    friends.add(serverResponse[i]);

                }
            }


                Intent FriendListIntent = new Intent(context,FriendsListActivity.class);
                FriendListIntent.putExtra("email",email);
                FriendListIntent.putExtra("name",name);
                FriendListIntent.putStringArrayListExtra("friends", friends);
                FriendListIntent.putStringArrayListExtra("countries", countries);
                FriendListIntent.putStringArrayListExtra("countryNames", countryNames);
                context.startActivity(FriendListIntent);



        }
        else if(flag.equals("addFriends"))
        {
            String test = "false";
            //String email = "";
            //String name = "";

            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> usernames = new ArrayList<>();

            test = serverResponse[0];
            //name  = serverResponse[1];
            Log.e("server response 0", serverResponse[0]+"");
            Log.e("server response 1", serverResponse[1]+"");
            Log.e("server response 2", serverResponse[2]+"");
            Log.e("server response 3", serverResponse[3]+"");
            if(serverResponse.length > 2)
            {
                for (int i = 1; i < serverResponse.length - 1; i++) {
                    usernames.add(serverResponse[i]);

                }
            }


            Intent AddFriendsIntent = new Intent(context,AddFriendsActivity.class);
            AddFriendsIntent.putExtra("email",email);
            AddFriendsIntent.putExtra("name",name);
            AddFriendsIntent.putStringArrayListExtra("usernames", usernames);
            AddFriendsIntent.putStringArrayListExtra("friends", friends);
            AddFriendsIntent.putStringArrayListExtra("countries", countries);
            AddFriendsIntent.putStringArrayListExtra("countryNames", countryNames);
            context.startActivity(AddFriendsIntent);



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

