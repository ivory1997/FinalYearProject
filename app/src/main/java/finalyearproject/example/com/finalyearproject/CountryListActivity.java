package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.List;

public class CountryListActivity extends AppCompatActivity {
    private ListView navigationList, countryList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    String email;
    String name;
    String profilePicString;
    Bitmap profilePicBitmap;
    String countriesLength;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> countryNames2 = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_list);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");

        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        //String globalprofiler = g.getProfiler();
        //Log.e("globalprofiler", globalprofiler + "");

        avatar = (ImageView) findViewById(R.id.avatar);
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        navigationList = (ListView) findViewById(R.id.navigationList);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        listData.add("My Map");
        listData.add("Country List");
        listData.add("Friends List");
        listData.add("Random Country Picker");
        listData.add("Country Recommender");
        listData.add("Log out");
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
                        ConnectDB connectDB = new ConnectDB(CountryListActivity.this);
                        connectDB.execute(task,email,name,profilePicString);
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
                        ConnectDB connectDB2 = new ConnectDB(CountryListActivity.this);
                        connectDB2.execute(task,email,name,profilePicString);
                        /*
                        Intent listIntent = getIntent();
                        finish();
                        startActivity(listIntent);
                        */

                        break;
                    case "Friends List":
                        toastMessage("Friends List");
                        task = "friends";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(CountryListActivity.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(CountryListActivity.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        //RandomCountryIntent.putExtra("profilePicString", profilePicString);
                        RandomCountryIntent.putStringArrayListExtra("countries", countries);
                        RandomCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(RandomCountryIntent);
                        break;
                    case "Country Recommender":
                        toastMessage("Country Recommender");
                        //task = "recommend";
                        //ConnectDBPassArray connectDBPassArray2 = new ConnectDBPassArray(ChartActivity.this);
                        //AsyncTaskParams AsyncTaskParams2 = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        //connectDBPassArray2.execute(AsyncTaskParams2);
                        /*
                        Intent CountryRecommenderIntent = new Intent(ChartActivity.this,CountryRecommenderActivity.class);
                        CountryRecommenderIntent.putExtra("email",email);
                        CountryRecommenderIntent.putExtra("name",name);
                        CountryRecommenderIntent.putStringArrayListExtra("countries", countries);
                        CountryRecommenderIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryRecommenderIntent);
                        */

                        break;
                    case "Log out":
                        toastMessage("Log out");
                        Intent logoutIntent = getBaseContext().getPackageManager().getLaunchIntentForPackage(getBaseContext().getPackageName());
                        logoutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(logoutIntent);
                        finish();

                        break;
                }


            }
        });
        //Country List Start
        countryList = (ListView) findViewById(R.id.countryList);
        final ArrayList<String> countryListData = new ArrayList<>();
        final ArrayList<String> countryListData2 = new ArrayList<>();
        for (int i =0; i < countryNames.size(); i++)
        {
            String selection = countryNames.get(i);
            switch (selection) {
                case "AX":
                    selection = "Åland Islands";
                    break;
                case "XK":
                    selection = "Republic of Kosovo";
                    break;
                case "CI":
                    selection = "Côte d'Ivoire";
                    break;
                case "RE":
                    selection = "Réunion";
                    break;
                case "BN":
                    selection = "Brunei Darussalam";
                    break;
                case "TL":
                    selection = "Timor-Leste";
                    break;
                case "FK":
                    selection = "Falkland Islands (Malvinas)";
                    break;
                case "SS":
                    selection = "South Sudan";
                    break;
                case "CG":
                    selection = "Congo";
                    break;
                case "CD":
                    selection = "Democratic Republic of the Congo ";
                    break;
            }
            countryListData.add(selection);
        }

        ListAdapter countryAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, countryListData)
        {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                //for (int i = 0; i < countries.size(); i++) {
                    View view = super.getView(position, convertView, parent);
                Log.e("position1", position + "");
                Log.e("countryposition1", countries.get(position) + "");
                    if (countries.get(position).equals("2")) {

                        view.setBackgroundColor(getResources().getColor(R.color.greenList));
                    } else {
                        view.setBackgroundColor(getResources().getColor(R.color.redList));
                    }

                /*
                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.BLUE);
                } else {
                    view.setBackgroundColor(Color.CYAN);
                }
                */


                    return view;
                }

        };

        countryList.setAdapter(countryAdapter);




        countryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String countrySelection = countryListData.get(i).toString();
                String countrySelection = countryNames.get(i);
                String selectedNum = countries.get(i);
                Intent viewCountryIntent = new Intent(CountryListActivity.this,ViewCountryActivity.class);
                viewCountryIntent.putExtra("email",email);
                viewCountryIntent.putExtra("name",name);
                viewCountryIntent.putStringArrayListExtra("countries", countries);
                viewCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                viewCountryIntent.putExtra("selectedValue",countrySelection);
                //String selectedNum ="0";
                viewCountryIntent.putExtra("selectedNum",selectedNum);
                startActivity(viewCountryIntent);


            }
        });
        //country List end


        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(CountryListActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putExtra("profilePicString", profilePicString);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });






    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

