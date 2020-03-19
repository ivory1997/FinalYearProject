package finalyearproject.example.com.finalyearproject;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
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
    private TextView usernamesFriends;
    String email;
    String name;
    String profilePicString;
    Bitmap profilePicBitmap;
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
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        navigationList = (ListView) findViewById(R.id.navigationList);
        final ArrayList<String> listData = new ArrayList<>();
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        usernamesFriends = (TextView) findViewById(R.id.usernamesFriends);
        String textforUsernamesFriends = name + "'s friends";
        usernamesFriends.setText(textforUsernamesFriends);
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
                        ConnectDB connectDB = new ConnectDB(FriendsListActivity.this);
                        connectDB.execute(task,email,name,profilePicString);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(FriendsListActivity.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        //CountryListIntent.putExtra("profilePicString",profilePicString);

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
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(FriendsListActivity.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        //RandomCountryIntent.putExtra("profilePicString",profilePicString);
                        RandomCountryIntent.putStringArrayListExtra("countries", countries);
                        RandomCountryIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(RandomCountryIntent);
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
        friendList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String friendSelection = friendListData.get(i);
                String task = "viewFriendsMap";
                ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(FriendsListActivity.this);
                AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,friendSelection,profilePicString,countries,countryNames);
                connectDBPassArray.execute(AsyncTaskParams);
            }
        });


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
                //viewProfileIntent.putExtra("profilePicString", profilePicString);
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
                AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames,friendListData);
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

