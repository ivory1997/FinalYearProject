package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class CountryListActivity extends AppCompatActivity {
    private ListView navigationList, countryList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    String email;
    String name;
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

        avatar = (ImageView) findViewById(R.id.avatar);
        navigationList = (ListView) findViewById(R.id.navigationList);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        listData.add("My Map");
        listData.add("Country List");
        listData.add("option 3");
        listData.add("option 4");
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
                        ConnectDB connectDB = new ConnectDB(CountryListActivity.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent listIntent = getIntent();
                        finish();
                        startActivity(listIntent);
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

        ListAdapter countryAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, countryListData);
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
