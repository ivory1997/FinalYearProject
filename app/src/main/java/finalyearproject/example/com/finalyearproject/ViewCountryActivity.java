package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.RequestQueue;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class ViewCountryActivity extends AppCompatActivity implements
        OnMapReadyCallback {
    String email;
    String name;
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
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
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
        final TextView textView1 = (TextView) findViewById(R.id.textView1);
        final TextView textView2 = (TextView) findViewById(R.id.textView2);
        final TextView textView3 = (TextView) findViewById(R.id.textView3);
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
        textView1.setText(selectedValue);
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
                                if (textView2.getText().length() == 0 && textView3.getText().length() == 0) {
                                    String latlng = country.getString("latlng");
                                    String[] coordinates = latlng.split(",");
                                    String lat = coordinates[0].substring(1);
                                    String lon = coordinates[1].replace((coordinates[1].substring(coordinates[1].length() - 1)), "");
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
        listData.add("option 2");
        listData.add("option 3");
        listData.add("option 4");
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
                    case "option 2":
                        toastMessage("option 2");
                        break;
                    case "option 3":
                        toastMessage("option 3");
                        break;
                    case "option 4":
                        toastMessage("option 3");
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
