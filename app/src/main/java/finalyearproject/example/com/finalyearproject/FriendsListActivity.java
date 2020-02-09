package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
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

public class FriendsListActivity extends AppCompatActivity {

    private ListView navigationList, friendList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    String email;
    String name;
    String countriesLength;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> friendListData = new ArrayList<>();
    ArrayList<String> countryNames2 = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friends_list);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        friendListData = receivedIntent.getStringArrayListExtra("friends");
        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");

        avatar = (ImageView) findViewById(R.id.avatar);
        navigationList = (ListView) findViewById(R.id.navigationList);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        listData.add("My Map");
        listData.add("Country List");
        listData.add("Friends List");
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
                        ConnectDB connectDB = new ConnectDB(FriendsListActivity.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(FriendsListActivity.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        //ChartIntent.putExtra("countries",countries);
                        //ChartIntent.putExtra("countriesLength",countries.length);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
                        break;
                    case "Friends List":
                        toastMessage("Friends List");
                        Intent friendsIntent = getIntent();
                        finish();
                        startActivity(friendsIntent);
                        break;
                    case "option 4":
                        toastMessage("option 3");
                        break;
                }


            }
        });
        //friend List Start
        friendList = (ListView) findViewById(R.id.friendList);
        //final ArrayList<String> friendListData = new ArrayList<>();
        //final ArrayList<String> friendListData2 = new ArrayList<>();
        //String task = "friends";
        //ConnectDB connectDB = new ConnectDB(FriendsListActivity.this);
        //connectDB.execute(task,email,name);
        String friend = "stevey@gmail.com";
        String friend2 = "jacob@gmail.com";
        //friendListData.add(friend);
        //friendListData.add(friend2);
        //Log.e("friend names", friendListData.get(0) + "");
        ListAdapter friendAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, friendListData);
        friendList.setAdapter(friendAdapter);
        //friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            //@Override
            //public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String countrySelection = countryNames.get(i);
                //String selectedNum = countries.get(i);
                //Intent viewCountryIntent = new Intent(FriendsListActivity.this,ViewfriendsActivity.class);
                //viewCountryIntent.putExtra("email",email);
                //viewCountryIntent.putExtra("name",name);
                //viewCountryIntent.putStringArrayListExtra("countries", countries);
                //viewCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                //viewCountryIntent.putExtra("selectedValue",countrySelection);
                //String selectedNum ="0";
                //viewCountryIntent.putExtra("selectedNum",selectedNum);
                //startActivity(viewCountryIntent);


           // }
        //});
        //friend List end


        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(FriendsListActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent addFriendsIntent = new Intent(FriendsListActivity.this, AddFriendsActivity.class);
                String task = "addFriends";
                ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(FriendsListActivity.this);
                AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,countries,countryNames,friendListData);
                connectDBPassArray.execute(AsyncTaskParams);
                //addFriendsIntent.putExtra("name", name);
                //addFriendsIntent.putExtra("email", email);
                //addFriendsIntent.putStringArrayListExtra("friends", friendListData);
                //addFriendsIntent.putStringArrayListExtra("countries", countries);
                //addFriendsIntent.putStringArrayListExtra("countryNames", countryNames);
                //startActivity(addFriendsIntent);
            }
        });





    }
    private void toastMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}

