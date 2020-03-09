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

import com.squareup.picasso.Picasso;

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


public class ConnectDB extends AsyncTask<String,Void,String> {

    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    AlertDialog alertDialog;
    Context context;
    private String line1;

    ConnectDB(Context ctx){
        this.context = ctx;

    }

    @Override
    protected String doInBackground(String... params) {

        preferences = context.getSharedPreferences("PREFERENCES", Context.MODE_PRIVATE);
        editor = preferences.edit();
        editor.putString("flag","0");
        editor.commit();


        String urlRegistration = "https://c16307271.000webhostapp.com/register.php";
        String urlLogin  = "https://c16307271.000webhostapp.com/login.php";
        String urlDelete  = "https://c16307271.000webhostapp.com/delete.php";
        String urlEdit  = "https://c16307271.000webhostapp.com/edit.php";
        String urlCountries  = "https://c16307271.000webhostapp.com/getCountries.php";
        String urlUpdateCountries  = "https://c16307271.000webhostapp.com/updateCountries.php";
        String urlFriends  = "https://c16307271.000webhostapp.com/getFriends.php";
        String urlConfirmAddFriend = "https://c16307271.000webhostapp.com/confirmAddFriend.php";
        String task = params[0];

        if(task.equals("register")){
            String registerEmail = params[1];
            String registerPassword = params[2];
            String registerName = params[3];
            Bitmap defaultPicBitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.defaultpic);
            ByteArrayOutputStream baos=new  ByteArrayOutputStream();
            defaultPicBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
            byte [] arr=baos.toByteArray();
            String defaultpicString=Base64.encodeToString(arr, Base64.DEFAULT);
            Log.e("pictureString", defaultpicString+"");
            try {
                URL url = new URL(urlRegistration);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_email","UTF-8")+"="+URLEncoder.encode(registerEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_password","UTF-8")+"="+URLEncoder.encode(registerPassword,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_name","UTF-8")+"="+URLEncoder.encode(registerName,"UTF-8")+"&"
                        +URLEncoder.encode("profile_picture","UTF-8")+"="+URLEncoder.encode(defaultpicString,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","register");
                editor.commit();
                String result="";
                result = "Successfully Registered " + registerName;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        if(task.equals("login")) {
            try {
                String user_name = params[1];
                String password = params[2];
                URL url = new URL(urlLogin);
                HttpURLConnection httpURLConnection = (HttpURLConnection)url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                httpURLConnection.setDoInput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                String post_data = URLEncoder.encode("identifier_loginEmail","UTF-8")+"="+URLEncoder.encode(user_name,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_loginPassword","UTF-8")+"="+URLEncoder.encode(password,"UTF-8");
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
                editor.putString("flag","login");
                editor.commit();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(task.equals("delete")){
            String deleteEmail = params[1];
            String deleteName = params[2];

            try {
                URL url = new URL(urlDelete);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_deleteEmail","UTF-8")+"="+URLEncoder.encode(deleteEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_deleteName","UTF-8")+"="+URLEncoder.encode(deleteName,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","delete");
                editor.commit();
                String result="";
                result = "Successfully deleted " + deleteEmail;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //test countries import start
        if(task.equals("countries")) {
            try {
                String email = params[1];
                String name = params[2];
                Globals g = Globals.getConfig();
                String profilePicString = g.getData();
                //String profilePicString = params[3];
                //Log.e("pictureString3", profilePicString+"");
                URL url = new URL(urlCountries);
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
                editor.putString("flag","countries");
                editor.commit();
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //test countries import end
        //test countries update start
        if(task.equals("updateCountries")){
            String updateCountriesEmail = params[1];
            String updateCountriesName = params[2];
            String updateCountriesValue = params[3];
            String updateCountriesNum = params[4];

            try {
                URL url = new URL(urlUpdateCountries);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                char[] updateCountriesValueChars = updateCountriesValue.toCharArray();
                for(int i =0; i < updateCountriesValue.length(); i++)
                {
                    if (updateCountriesValueChars[i] == ' ')
                    {
                        updateCountriesValueChars[i] = '_';
                    }
                }
                updateCountriesValue = String.valueOf(updateCountriesValueChars);
                String myData = URLEncoder.encode("updateCountriesEmail","UTF-8")+"="+URLEncoder.encode(updateCountriesEmail,"UTF-8")+"&"
                        +URLEncoder.encode("updateCountriesName","UTF-8")+"="+URLEncoder.encode(updateCountriesName,"UTF-8")+"&"
                        +URLEncoder.encode("updateCountriesValue","UTF-8")+"="+URLEncoder.encode(updateCountriesValue,"UTF-8")+"&"
                        +URLEncoder.encode("updateCountriesNum","UTF-8")+"="+URLEncoder.encode(updateCountriesNum,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","updateCountries");
                editor.commit();
                String result="";
                result = "Successfully Updated Countries for:  " + updateCountriesName;
                return result;
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //test countries update end
        if(task.equals("friends")) {
            try {
                String email = params[1];
                String name = params[2];
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
        if(task.equals("confirmAddFriend")){
            String userEmail = params[1];
            String userName = params[2];
            String friendName = params[3];

            try {
                URL url = new URL(urlConfirmAddFriend);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_userEmail","UTF-8")+"="+URLEncoder.encode(userEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_userName","UTF-8")+"="+URLEncoder.encode(userName,"UTF-8")+"&"
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
        if(task.equals("edit")){
            String oldEmail = params[1];
            String updatedEmail = params[2];
            String updatedPassword = params[3];
            String updatedName = params[4];
            Log.e("oldEmail", oldEmail + "");
            Log.e("updatedEmail", updatedEmail + "");
            Log.e("updatedPassword", updatedPassword + "");
            Log.e("updatedName", updatedName + "");

            try {
                URL url = new URL(urlEdit);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("POST");
                httpURLConnection.setDoOutput(true);
                OutputStream outputStream = httpURLConnection.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream,"UTF-8");
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
                String myData = URLEncoder.encode("identifier_oldEmail","UTF-8")+"="+URLEncoder.encode(oldEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_updateEmail","UTF-8")+"="+URLEncoder.encode(updatedEmail,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_updatePassword","UTF-8")+"="+URLEncoder.encode(updatedPassword,"UTF-8")+"&"
                        +URLEncoder.encode("identifier_updateName","UTF-8")+"="+URLEncoder.encode(updatedName,"UTF-8");
                bufferedWriter.write(myData);
                bufferedWriter.flush();
                bufferedWriter.close();
                InputStream inputStream = httpURLConnection.getInputStream();
                inputStream.close();

                editor.putString("flag","edit");
                editor.commit();
                String result="";
                result = "Successfully Updated profile" + updatedEmail;
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


        if(flag.equals("register")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent loginIntent = new Intent(context,LoginActivity.class);
            context.startActivity(loginIntent);
        }
        else if(flag.equals("delete")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent(context,MainActivity.class);
            context.startActivity(mainIntent);
        }
        else if(flag.equals("edit")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent(context,MainActivity.class);
            context.startActivity(mainIntent);
        }
        else if(flag.equals("updateCountries")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
        }
        else if(flag.equals("confirmAddFriend")) {
            Toast.makeText(context,result,Toast.LENGTH_LONG).show();
            Intent mainIntent = new Intent(context,MainActivity.class);
            context.startActivity(mainIntent);
        }
        else if(flag.equals("countries"))
        {
            String test = "false";
            String email = "";
            String name = "";
            //String profilePicString = "";
            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> countries = new ArrayList<>();
            ArrayList<String> countryNames = new ArrayList<>();
            //profilePicString = serverResponse[0];
            test = serverResponse[1];
            email = serverResponse[2];
            name = serverResponse[3];

            Log.e("server response 0", serverResponse[0]+"");
            Log.e("server response 1", serverResponse[1]+"");
            Log.e("server response 2", serverResponse[2]+"");
            Log.e("server response 3", serverResponse[3]+"");
            Log.e("server response 4", serverResponse[4]+"");
            Log.e("server response 5", serverResponse[5]+"");
            Log.e("server response 6", serverResponse[6]+"");
            Log.e("server response 7", serverResponse[7]+"");
            for(int i = 4; i < serverResponse.length; i+=2)
            {
                countryNames.add(serverResponse[i]);
                countries.add(serverResponse[i+1]);
                //countries[i] = serverResponse[i];

            }

            //(Globals) globals.this.getApplication()).setData(profilePicString);
            //Globals g = (Globals).getApplication();
            //Globals g = Globals.getConfig();
            //g.setData(profilePicString);


            if(test.contains("true")){
                Intent ChartIntent = new Intent(context,ChartActivity.class);
                ChartIntent.putExtra("email",email);
                ChartIntent.putExtra("name",name);
                //ChartIntent.putExtra("profilePicString",profilePicString);
                ChartIntent.putStringArrayListExtra("countries", countries);
                ChartIntent.putStringArrayListExtra("countryNames", countryNames);
                context.startActivity(ChartIntent);
            }


        }
        else if(flag.equals("friends"))
        {
            String test = "false";
            String email = "";
            String name = "";

            String[] serverResponse = result.split("[,]");
            //String[] countries = new String[serverResponse.length];
            ArrayList<String> friends = new ArrayList<>();

            test = serverResponse[0];
            name  = serverResponse[1];
            Log.e("server response 0", serverResponse[0]+"");
            Log.e("server response 1", serverResponse[1]+"");
            Log.e("server response 2", serverResponse[2]+"");
            Log.e("server response 3", serverResponse[3]+"");

            for(int i = 1; i < serverResponse.length; i+=2)
            {
                friends.add(serverResponse[i]);

            }

/*
            if(test.contains("true")){

                Intent FriendListIntent = new Intent(context,FriendsListActivity.class);
                FriendListIntent.putExtra("email",email);
                FriendListIntent.putExtra("name",name);
                FriendListIntent.putStringArrayListExtra("friends", friends);
                //FriendListIntent.putStringArrayListExtra("countries", countries);
                //FriendListIntent.putStringArrayListExtra("countryNames", countryNames);
                context.startActivity(FriendListIntent);
            }
*/

        }
        else if(flag.equals("login")){

            String test = "false";
            String name = "";
            String email = "";
            String profilePicString = "";
            String[] serverResponse = result.split("[,]");
            test = serverResponse[0];
            //Log.e("profilePicSTringdecode", serverResponse[3]+"");

            if(test.contains("true")){
                email = serverResponse[1];
                name = serverResponse[2];
                profilePicString = serverResponse[3];
                //Log.e("profilePicStringencode", profilePicString+"");
                //byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
                //profilePicString=Base64.encodeToString(encodeByte, Base64.DEFAULT);
                //Log.e("profilePicStringdecode", profilePicString+"");
                Globals g = Globals.getConfig();
                g.setData(profilePicString);
                //Log.e("pictureString2", serverResponse[3]+"");
                editor.putString("name",name);
                editor.commit();
                editor.putString("email",email);
                editor.commit();
                //editor.putString("profilePicString",profilePicString);
                editor.commit();
                Intent loggedInIntent = new Intent(context,LoggedIn.class);
                loggedInIntent.putExtra("name",name);
                loggedInIntent.putExtra("email",email);
                //loggedInIntent.putExtra("profilePicString",profilePicString);
                context.startActivity(loggedInIntent);

            }
            else{
                Intent BacktoLogin = new Intent(context,LoginActivity.class);
                context.startActivity(BacktoLogin);
                Toast.makeText(context,"Incorrect email address and password combination",Toast.LENGTH_LONG).show();
                //display("Login Failed...", "That email and password do not match our records :(.");
            }



        }



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
