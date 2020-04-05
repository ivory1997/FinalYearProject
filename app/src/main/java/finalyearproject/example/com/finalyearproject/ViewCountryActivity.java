package finalyearproject.example.com.finalyearproject;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.ahmadrosid.svgloader.SvgLoader;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.model.StreamEncoder;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ViewCountryActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    String email;
    String name;
    String profilePicString;
    Bitmap profilePicBitmap;
    String selectedValue;
    String selectedNum2;
    double latitude = -33.865143;
    double longitude = 151.209900;
    Button btnChangeColour;
    Button btnBackToMap;
    private GoogleMap map;
    int latInt;
    int lonInt;
    boolean coordinatesSet = false;
    int selectedNum3;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private ImageView flag;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_country);
        //SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
        //        .findFragmentById(R.id.map);
       //mapFragment.getMapAsync(this);

        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        selectedValue = receivedIntent.getStringExtra("selectedValue");
        selectedNum2 = receivedIntent.getStringExtra("selectedNum");
        avatar = (ImageView) findViewById(R.id.avatar);
        flag = (ImageView) findViewById(R.id.flag);
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        //flag.setImageBitmap(profilePicBitmap);
        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
        final TextView textView4 = (TextView) findViewById(R.id.textView4);
        final TextView textView5 = (TextView) findViewById(R.id.textView5);
        final TextView userName = (TextView) findViewById(R.id.userName);
        final TextView textViewStatus = (TextView) findViewById(R.id.textViewStatus);

        if(selectedNum2.equals("1"))
        {
            textViewStatus.setText("Not visited");
            textViewStatus.setTextColor(Color.RED);
        }
        else if(selectedNum2.equals("2"))
        {
            textViewStatus.setText("Visited");
            textViewStatus.setTextColor(Color.GREEN);
        }

        if(selectedValue.equals("AX"))
        {
            selectedValue = "Åland Islands";
        }
        if(selectedValue.equals("XK"))
        {
            selectedValue = "Republic of Kosovo";
        }
        if(selectedValue.equals("CI"))
        {
            selectedValue = "Côte d'Ivoire";
        }
        if(selectedValue.equals("RE"))
        {
            selectedValue = "Réunion";
        }
        if(selectedValue.equals("BN"))
        {
            selectedValue = "Brunei Darussalam";
        }
        if(selectedValue.equals("TL"))
        {
            selectedValue = "Timor-Leste";
        }
        if(selectedValue.equals("FK"))
        {
            selectedValue = "Falkland Islands(Malvinas)";
        }
        if(selectedValue.equals("FK"))
        {
            selectedValue = "Falkland Islands(Malvinas)";
        }
        if(selectedValue.equals("SS"))
        {
            selectedValue = "South Sudan";
        }
        if(selectedValue.equals("CG"))
        {
            selectedValue = "Congo";
        }
        if(selectedValue.equals("CD"))
        {
            selectedValue = "Democratic republic of the Congo";
        }

        textView1.setText(selectedValue);

        if(selectedValue.equals("Åland Islands"))
        {
            selectedValue = "AX";
        }
        if(selectedValue.equals("Republic of Kosovo"))
        {
            selectedValue = "XK";
        }
        if(selectedValue.equals("Côte d'Ivoire"))
        {
            selectedValue = "CI";
        }
        if(selectedValue.equals("Réunion"))
        {
            selectedValue = "RE";
        }
        if(selectedValue.equals("Brunei Darussalam"))
        {
            selectedValue = "BN";
        }
        if(selectedValue.equals("Timor-Leste"))
        {
            selectedValue = "TL";
        }
        if(selectedValue.equals("Falkland Islands(Malvinas)"))
        {
            selectedValue = "FK";
        }
        if(selectedValue.equals("South Sudan"))
        {
            selectedValue = "SS";
        }
        if(selectedValue.equals("Congo"))
        {
            selectedValue = "CG";
        }
        if(selectedValue.equals("Democratic republic of the Congo"))
        {
            selectedValue = "CD";
        }

        userName.setText(name);

        btnChangeColour = (Button) findViewById(R.id.btnChangeColour);
        btnChangeColour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "updateCountries";
                ConnectDB connectDB = new ConnectDB(ViewCountryActivity.this);
                connectDB.execute(task,email,name,selectedValue,selectedNum2);
                if(textViewStatus.getText().toString().equals("Visited"))
                {
                    textViewStatus.setText("Not visited");
                    textViewStatus.setTextColor(Color.RED);
                }
                else if(textViewStatus.getText().toString().equals("Not visited"))
                {
                    textViewStatus.setText("Visited");
                    textViewStatus.setTextColor(Color.GREEN);
                }




            }
        });
        btnBackToMap = (Button) findViewById(R.id.btnBackToMap);
        btnBackToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "countries";
                Intent receivedIntent = getIntent();
                name = receivedIntent.getStringExtra("name");
                email = receivedIntent.getStringExtra("email");
                ConnectDB connectDB = new ConnectDB(ViewCountryActivity.this);
                connectDB.execute(task,email,name);




            }
        });


        //TESTING API RESPONSE START
        String URL;
        Log.e("Value geochart",selectedValue);
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
        RequestQueue requestQueue=Volley.newRequestQueue(this);
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
                                if (textView2.getText().length() == 0 && textView3.getText().length() == 0 && textView4.getText().length() == 0) {
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
                                    setLat(latitude1);
                                    setLon(longitude1);
                                    coordinatesSet = true;
                                    int latInt = Integer.parseInt(lat);
                                    int lonInt = Integer.parseInt(lon);
                                    jsonResponse += "latlng: " + latlng + "\n\n";
                                    Log.e("latlng", latlng);
                                    Log.e("latitude", coordinates[0]);
                                    Log.e("latitude", lat);
                                    Log.e("longitude", lon);
                                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(new LatLng(latitude1, longitude1), 4);
                                    map.animateCamera(cameraUpdate);

                                    String population = country.getString("population");
                                    textView2.setText("Population: " + population);
                                    String capital = country.getString("capital");
                                    textView3.setText("Capital City: " + capital);
                                    //String languages = country.getString("languages");
                                    String languageName = "";
                                    String temp;
                                    JSONArray languages = country.getJSONArray("languages");
                                    for (int j = 0; i < languages.length(); i++) {
                                        JSONObject row = languages.getJSONObject(i);
                                        temp = row.getString("name");
                                        languageName = languageName + "," + temp;
                                    }
                                    languageName = languageName.substring(1);
                                    //String languageName = languages.getString("name");
                                    textView4.setText("Languages: " + languageName);
                                    String currencyName = "";

                                    JSONArray currencies = country.getJSONArray("currencies");
                                    for (int k = 0; k < currencies.length(); k++) {
                                        JSONObject rowCurrency = currencies.getJSONObject(k);
                                        temp = rowCurrency.getString("name");
                                        currencyName = currencyName + "," + temp;
                                    }
                                    currencyName = currencyName.substring(1);
                                    textView5.setText("Currencies: " + currencyName);
                                    String flagUrl = country.getString("flag");
                                    Log.e("flagUrl", flagUrl);
                                    String task = "flag";
                                    ConnectDB connectDB = new ConnectDB(ViewCountryActivity.this);
                                    connectDB.execute(task, email, name, flagUrl);
                                    //Bitmap flagBitmap = profilePicBitmap;
                                    Globals g = Globals.getConfig();
                                    Bitmap flagBitmap = g.getFlagBitmap();
                                    //Picasso.with(getApplicationContext()).load("https://restcountries.eu/data/rus.svg").into(flag);
                                    //GlideApp.with(context).load("https://restcountries.eu/data/rus.svg").into(flag);
                                    //Glide.with(ViewCountryActivity.this).load("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg").into(flag);
                                    /*
                                    SvgLoader.pluck()
                                           .with(ViewCountryActivity.this)
                                            .setPlaceHolder(R.mipmap.ic_launcher, R.mipmap.ic_launcher)
                                            .load("http://www.clker.com/cliparts/u/Z/2/b/a/6/android-toy-h.svg", flag);
*/
                                    //flag.setImageBitmap(flagBitmap);
                                    /*
                                    ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                                    flagBitmap.compress(Bitmap.CompressFormat.PNG,1, baos);
                                    byte [] arr=baos.toByteArray();
                                    String flagBitmapString=Base64.encodeToString(arr, Base64.DEFAULT);
                                    Log.e("flagBitmap", flagBitmapString + "");
                                    */


                                    /*
                                    Picasso picasso = Picasso.with(getApplicationContext());
                                    picasso.load(flagUrl)
                                            .into(flag);
                                            */

                                    //Picasso.with(ViewCountryActivity.this).load(flagUrl).into(flag);
                                    /*
                                    try {
                                        URL url = new URL(flagUrl);
                                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                                        connection.setDoInput(true);
                                        connection.connect();
                                        InputStream input = connection.getInputStream();
                                        Bitmap bitmap = BitmapFactory.decodeStream(input);
                                        flag.setImageBitmap(bitmap);
                                    } catch (MalformedURLException e) {
                                        e.printStackTrace();
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
*/
                                }



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

        //task temp

        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");
        navigationList = (ListView) findViewById(R.id.navigationList);
        avatar = (ImageView) findViewById(R.id.avatar);
        final ArrayList<String> listData = new ArrayList<>();
        listData.add("My Map");
        listData.add("Country List");
        listData.add("Friends List");
        listData.add("Random Country Picker");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
                        //countriesLength = receivedIntent.getStringExtra("countriesLength");
                        //int countriesLengthInt = Integer.parseInt(countriesLength);
                        //String[] countries = new String[countriesLengthInt];
                        //countries = receivedIntent.getStringExtra("countries");
                        ConnectDB connectDB = new ConnectDB(ViewCountryActivity.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        task = "countryList";
                        Intent receivedIntent2 = getIntent();
                        name = receivedIntent2.getStringExtra("name");
                        email = receivedIntent2.getStringExtra("email");
                        //profilePicString = receivedIntent.getStringExtra("profilePicString");
                        Globals g2 = (Globals)getApplication();
                        String  data2=g2.getData();
                        profilePicString = g2.getData();
                        ConnectDB connectDB2 = new ConnectDB(ViewCountryActivity.this);
                        connectDB2.execute(task,email,name,profilePicString);
                        /*
                        Intent CountryListIntent = new Intent(ViewCountryActivity.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
                        */

                    break;
                    case "Friends List":
                        toastMessage("Friends List");
                        task = "friends";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(ViewCountryActivity.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(ViewCountryActivity.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        RandomCountryIntent.putStringArrayListExtra("countries", countries);
                        RandomCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(RandomCountryIntent);
                        break;
                }


            }
        });
        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(ViewCountryActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                startActivity(viewProfileIntent);

            }
        });

    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    public void setLat(double latitude) {
        this.latitude = latitude;
    }
    public void setLon(double longitude) {
        this.longitude = longitude;
    }
    public double getLat() {
        return this.latitude;

    }

    public double getLon() {
        return this.longitude;
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
            map = googleMap;

            double latitude2 = getLat();
            double longitude2 = getLon();
            String latitudetest = String.valueOf(latitude2);
            String longitudetest = String.valueOf(longitude2);
            Log.e("latitudetest", latitudetest);
            Log.e("longitudetest", longitudetest);
            LatLng pos= new LatLng(latitude2, longitude2);
            //map.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(latitude2, longitude2), 4));

        // Set listeners for click events.
        //googleMap.setOnPolylineClickListener(this);
        //googleMap.setOnPolygonClickListener(this);
    }
}
