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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.model.LatLng;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("SetJavaScriptEnabled")
public class RandomCountry extends AppCompatActivity {

    WebView webView;
    int num1, num2, num3, num4, num5;
    String email;
    String name;
    String profilePicString;
    Bitmap profilePicBitmap;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    //private String population;
    //private String capital;
    String countriesLength;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    String texto;

    //String texto1 = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_country);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");
        navigationList = (ListView) findViewById(R.id.navigationList);
        avatar = (ImageView) findViewById(R.id.avatar);
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        listData.add("My Map");
        listData.add("Country List");
        listData.add("Friends List");
        listData.add("Random Country Picker");
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
                        //profilePicString = receivedIntent.getStringExtra("profilePicString");
                        Globals g = (Globals)getApplication();
                        String  data=g.getData();
                        profilePicString = g.getData();
                        ConnectDB connectDB = new ConnectDB(RandomCountry.this);
                        connectDB.execute(task,email,name,profilePicString);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(RandomCountry.this,CountryListActivity.class);
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
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(RandomCountry.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent randomIntent = getIntent();
                        finish();
                        startActivity(randomIntent);
                        break;
                }


            }
        });
        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(RandomCountry.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                //viewProfileIntent.putExtra("profilePicString", profilePicString);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });






        for(int i = 0;i < countryNames.size();i++)
        {
            if(countryNames.get(i).contains("_"))
            {
                countryNames.get(i).replace("_"," ");
            }
        }
        String one = "1";
        Log.e("country values before", countries.get(0)+"");
        for(int i = 0;i < countries.size();i++)
        {

                countries.set(i,one);

        }
        Log.e("country values after", countries.get(0)+"");

        List<Integer> newCountryList = new ArrayList<Integer>(countries.size()) ;
        for (String myInt : countries)
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
        texto = "[{\"countryName\":"+"\""+countryNames.get(0)+"\""+", \"countryValue\":"+newCountryList.get(0)+"}";
        for(int i=1;i<newCountryList.size();i++)
        {

            texto = texto + ",{\"countryName\":"+"\""+countryNames.get(i)+"\""+", \"countryValue\":"+newCountryList.get(i)+"}";
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
        webView.loadUrl("file:///android_asset/randomGeochart.html");

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
        private String population;
        private String capital;
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

        }


        @JavascriptInterface
        public String getAPIinfo(String selectedValue) {
            //String selectedValue = this.selectedValue2;
            //int selectedNum = this.selectedNum2;
            //TEST start

            //String countryNameRandom = selectedValue;

            String URL;
            Log.e("Value geochart",selectedValue);
            Globals g = (Globals)getApplication();
            g.setCountryNameRandom(selectedValue);
            char[] selectedValueChars = selectedValue.toCharArray();
            for(int i =0; i < selectedValue.length(); i++)
            {
                if (selectedValueChars[i] == '_')
                {
                    selectedValueChars[i] = ' ';
                }
            }

            Log.e("Value modification",selectedValue);
            selectedValue = String.valueOf(selectedValueChars);

            if(selectedValue.equals("United States"))
            {
                selectedValue = "US";
            }
            if(selectedValue.equals("Niger"))
            {
                selectedValue = "NE";
            }
            if(selectedValue.equals("Russia"))
            {
                selectedValue = "Russian Federation";
            }
            Log.e("Value Simplified",selectedValue);
            if(selectedValue.length()==2)
            {
                URL = "https://restcountries.eu/rest/v2/alpha?codes="+selectedValue;
            }
            else
            {
                //URL = "https://restcountries.eu/rest/v2/name/"+selectedValue+"?fullText=true";
                URL = "https://restcountries.eu/rest/v2/name/" + selectedValue;
            }
            Log.e("URL value",URL);
            RequestQueue requestQueue= Volley.newRequestQueue(RandomCountry.this);
            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                    Request.Method.GET,
                    URL,
                    null,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            Log.e("REST RESPONSE",response.toString());
                            try {
                                // Parsing json array response
                                // loop through each json object
                                String jsonResponse = "";
                                for (int i = 0; i < response.length(); i++) {

                                    JSONObject country = (JSONObject) response
                                            .get(i);
                                        String latlng = country.getString("latlng");
                                        String[] coordinates = latlng.split(",");
                                        String lat = coordinates[0].substring(1);
                                        String lon = coordinates[1].replace((coordinates[1].substring(coordinates[1].length() - 1)), "");
                                        lon = lon.split("\\.", 2)[0];
                                        lat = lat.split("\\.", 2)[0];
                                        //String latitude = latlng.substring(1, latlng.indexOf(","));
                                        //latitude.trim();
                                        double latitude1 = Double.valueOf(lat);
                                        double longitude1 = Double.valueOf(lon);
                                        //setLat(latitude1);
                                        //setLon(longitude1);
                                        //coordinatesSet = true;
                                        int latInt = Integer.parseInt(lat);
                                        int lonInt = Integer.parseInt(lon);
                                        jsonResponse += "latlng: " + latlng + "\n\n";
                                        Log.e("latlng", latlng);
                                        Log.e("latitude", coordinates[0]);
                                        Log.e("latitude", lat);
                                        Log.e("longitude", lon);

                                        population = country.getString("population");
                                        Log.e("Population", population);
                                        capital = country.getString("capital");
                                        Log.e("Capital", capital);
                                        setPopulation2(population);
                                        setCapital2(capital);
                                        String pop = population;
                                        String cap = capital;
                                        String countryNameRandom = country.getString("name");
                                        Globals g = (Globals)getApplication();
                                        g.setPop(pop);
                                        g.setCap(cap);
                                        g.setCountryNameRandom2(countryNameRandom);
                                        //String languages = country.getString("languages");
                                        String languageName = "";
                                        String temp;
                                        JSONArray languages = country.getJSONArray("languages");
                                        for (int j = 0; i < languages.length(); i++) {
                                            JSONObject row = languages.getJSONObject(i);
                                            temp = row.getString("name");
                                            languageName = languageName + "," + temp;
                                        }
                                       // languageName = languageName.substring(1);
                                        //String languageName = languages.getString("name");
                                        String flagUrl = country.getString("flag");
                                        Log.e("flagUrl", flagUrl);
                                        String task = "flag";






                                }


                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(getApplicationContext(),
                                        "Error: " + e.getMessage(),
                                        Toast.LENGTH_LONG).show();
                            }

                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.e("REST RESPONSE ERROR",error.toString());
                        }
                    }
            );
            requestQueue.add(jsonArrayRequest);
            //TESTING API RESPONSE END
            if(selectedValue == "US")
            {
                selectedValue = "United_States";
            }
            if(selectedValue.equals("Russian Federation"))
            {
                selectedValue = "Russia";
            }

            if(selectedValue.equals("NE"))
            {
                selectedValue = "Niger";
            }

            Log.e("selectedValue2", selectedValue2+"");
            //Log.e("selectedNum2", selectedNum3+"");

            //TEST END
            //String task = "updateCountries";
            //ConnectDB connectDB = new ConnectDB(ChartActivity.this);
            //connectDB.execute(task,email,name,selectedValue2,selectedNum3);
            return population;
        }
        @JavascriptInterface
        public String setPopulation()
        {
            Globals g = (Globals)getApplication();
            String  pop=g.getPop();
            return pop;
        }
        @JavascriptInterface
        public void setPopulation2(String population2)
        {
            this.population = population2;
        }
        @JavascriptInterface
        public String setCapital()
        {
            Globals g = (Globals)getApplication();
            String  cap=g.getCap();
            return cap;
        }
        @JavascriptInterface
        public String setCountryNameRandom()
        {
            Globals g = (Globals)getApplication();
            String countryNameRandom=g.getCountryNameRandom();
            Log.e("country going to js", countryNameRandom+"");
            return countryNameRandom;
        }

        @JavascriptInterface
        public String setCountryNameRandom2()
        {
            Globals g = (Globals)getApplication();
            String  countryNameRandom2=g.getCountryNameRandom2();
            return countryNameRandom2;
        }

        @JavascriptInterface
        public void setCapital2(String capital2)
        {
           this.capital = capital2;
        }
    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

}
