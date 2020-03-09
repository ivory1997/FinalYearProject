package finalyearproject.example.com.finalyearproject;

import android.annotation.TargetApi;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.annotation.SuppressLint;
import android.util.Base64;
import android.util.JsonReader;
import android.util.JsonToken;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("SetJavaScriptEnabled")
public class FriendChartActivity extends AppCompatActivity {

    WebView webView;
    int num1, num2, num3, num4, num5;
    String email;
    String name;
    String friendName;
    String profilePicString;
    Bitmap profilePicBitmap;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    String countriesLength;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> friendCountries = new ArrayList<>();
    ArrayList<String> friendCountryNames = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend_chart);

        Globals g = (Globals)getApplication();
        friendCountries=g.getFriendCountries();
        friendCountryNames=g.getFriendCountryNames();
        Log.e("friend country names", friendCountryNames.get(0) + "");
        Log.e("friend country values", friendCountries.get(0) + "");
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        friendName = receivedIntent.getStringExtra("friendName");
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        //Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte=Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        Log.e("pictureString4", profilePicString + "");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");
        navigationList = (ListView) findViewById(R.id.navigationList);
        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setImageBitmap(profilePicBitmap);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);

        listData.add("My Map");
        listData.add("Country List");
        listData.add("Friends List");
        listData.add("Random Country Picker");
        listData.add("Country Recommender");
        ListAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData);
        navigationList.setAdapter(adapter);
        navigationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name1 = listData.get(i).toString();
                switch (name1) {
                    case "My Map":
                        String task = "countries";
                        Intent receivedIntent = getIntent();
                        name = receivedIntent.getStringExtra("name");
                        email = receivedIntent.getStringExtra("email");
                        ConnectDB connectDB = new ConnectDB(FriendChartActivity.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(FriendChartActivity.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        //CountryListIntent.putExtra("profilePicString", profilePicString);
                        //ChartIntent.putExtra("countries",countries);
                        //ChartIntent.putExtra("countriesLength",countries.length);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
                        break;
                    case "Friends List":
                        toastMessage("Friends List");
                        task = "friends";
                        String placeholder = "placeholder";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(FriendChartActivity.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(FriendChartActivity.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        RandomCountryIntent.putExtra("profilePicString", profilePicString);
                        RandomCountryIntent.putStringArrayListExtra("countries", countries);
                        RandomCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(RandomCountryIntent);
                        break;
                    case "Country Recommender":
                        toastMessage("Country Recommender");
                        task = "recommend";
                        ConnectDBPassArray connectDBPassArray2 = new ConnectDBPassArray(FriendChartActivity.this);
                        AsyncTaskParams AsyncTaskParams2 = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray2.execute(AsyncTaskParams2);
                        /*
                        Intent CountryRecommenderIntent = new Intent(ChartActivity.this,CountryRecommenderActivity.class);
                        CountryRecommenderIntent.putExtra("email",email);
                        CountryRecommenderIntent.putExtra("name",name);
                        CountryRecommenderIntent.putStringArrayListExtra("countries", countries);
                        CountryRecommenderIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryRecommenderIntent);
                        */

                        break;
                }


            }
        });
        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(FriendChartActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putExtra("profilePicString", profilePicString);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });






        for(int i = 0;i < friendCountryNames.size();i++)
        {
            if(friendCountryNames.get(i).contains("_"))
            {
                friendCountryNames.get(i).replace("_"," ");
            }
        }


        List<Integer> newCountryList = new ArrayList<Integer>(friendCountries.size()) ;
        for (String myInt : friendCountries)
        {
            newCountryList.add(Integer.valueOf(myInt));
        }
        Log.e("country values", newCountryList.get(0)+"");
        Log.e("country values", newCountryList.get(1)+"");
        num1 = newCountryList.get(0);
        num2 = newCountryList.get(1);
        num3 = 2;
        num4 = 4;
        num5 = 5;
        texto = "[{\"countryName\":"+"\""+friendCountryNames.get(0)+"\""+", \"countryValue\":"+newCountryList.get(0)+"}";
        for(int i=1;i<newCountryList.size();i++)
        {

            texto = texto + ",{\"countryName\":"+"\""+friendCountryNames.get(i)+"\""+", \"countryValue\":"+newCountryList.get(i)+"}";
            // stringBuilder.append("{countryName: "+countryNames.get(i)+", countryValue: "+newCountryList.get(i)+"}");
        }
        texto = texto + "]";
        Log.e("texto", texto+"");
        Log.e("texto", newCountryList.size()+"");
        webView = (WebView)findViewById(R.id.web);
        webView.addJavascriptInterface(new WebAppInterface(), "Android");
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
        webView.requestFocusFromTouch();
        webView.getSettings().setBuiltInZoomControls(true);
        //webView.getSettings().setLoadWithOverviewMode(true);
        //webView.getSettings().setUseWideViewPort(true);
        webView.setInitialScale(180);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.loadUrl("file:///android_asset/friendGeochart.html");

        //Android version variable
        final int version = Build.VERSION.SDK_INT;
//Because this method can only be used in Android version 4.4, version judgment is needed when using it.
        if (version < 19) {
            webView.loadUrl("javascript:callJS()");
        } else {
            webView.evaluateJavascript("javascript:Android.getValue(selectedValue);", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String value) {
                    Log.e("value from js", value+"");
                    JsonReader reader = new JsonReader(new StringReader(value));

                    // Must set lenient to parse single values
                    reader.setLenient(true);

                    try {
                        if(reader.peek() != JsonToken.NULL) {
                            if(reader.peek() == JsonToken.STRING) {
                                String msg = reader.nextString();
                                if(msg != null) {
                                    Log.e("value from js2", msg+"");
                                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    } catch (IOException e) {
                        Log.e("TAG", "MainActivity: IOException", e);
                    } finally {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            // NOOP
                        }
                    }
                }

            });
        }
        /*
        String script = "javascript:GoBack();";

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {

            webView.evaluateJavascript(script, null);
        } else {
            webView.loadUrl(script);
        }

        webView.evaluateJavascript("(function() { return " + command + "; })();", new ValueCallback<String>() {
        @Override
        public void onReceiveValue(String s) {
            returnDataFromJs(s);
        }
    });
    */




    }
    public class WebAppInterface {

/*
        @JavascriptInterface
        public JSONArray getChartData() {

            JSONArray jsonar=null;
            try {
                jsonar = new JSONArray(texto);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return jsonar;
        }
        */

        String selectedValue2;
        int selectedNum2;
        String selectedNum3;

        @JavascriptInterface
        public String getChartData() {
            return texto;
        }

        @JavascriptInterface
        public int getNum1() {
            return num1;
        }

        @JavascriptInterface
        public int getNum2() {
            return num2;
        }

        @JavascriptInterface
        public int getNum3() {
            return num3;
        }

        @JavascriptInterface
        public int getNum4() {
            return num4;
        }

        @JavascriptInterface
        public int getNum5() {
            return num5;
        }

        @JavascriptInterface
        public void getValue(String selectedValue, int selectedNum) {
            this.selectedValue2 = selectedValue;
            this.selectedNum2 = selectedNum;
            selectedNum3 = Integer.toString(selectedNum2);
/*

            //test start
            Intent viewCountryIntent = new Intent(FriendChartActivity.this,ViewCountryActivity.class);
            viewCountryIntent.putExtra("email",email);
            viewCountryIntent.putExtra("name",name);
            viewCountryIntent.putStringArrayListExtra("countries", countries);
            viewCountryIntent.putStringArrayListExtra("countryNames", countryNames);
            viewCountryIntent.putExtra("selectedValue",selectedValue2);
            viewCountryIntent.putExtra("selectedNum",selectedNum3);
            startActivity(viewCountryIntent);
            //test end
            Log.e("selectedValue2", selectedValue2+"");
            Log.e("selectedNum2", selectedNum3+"");
*/

            //String task = "updateCountries";
            //ConnectDB connectDB = new ConnectDB(ChartActivity.this);
            //connectDB.execute(task,email,name,selectedValue2,selectedNum3);
        }
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
