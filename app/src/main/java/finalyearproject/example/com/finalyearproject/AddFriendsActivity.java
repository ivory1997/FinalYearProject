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

public class AddFriendsActivity extends AppCompatActivity {

    private ListView navigationList, userList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private TextView userName;
    String email;
    String name;
    String countriesLength;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    ArrayList<String> friendListData = new ArrayList<>();
    ArrayList<String> userListData = new ArrayList<>();
    ArrayList<String> countryNames2 = new ArrayList<>();
    List<Integer> newCountryList = new ArrayList<Integer>();
    String texto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friends);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        friendListData = receivedIntent.getStringArrayListExtra("friends");
        userListData = receivedIntent.getStringArrayListExtra("usernames");
        Log.e("country names", countryNames.get(0) + "");
        Log.e("country values", countries.get(0) + "");
        Log.e("username 1", userListData.get(0) + "");
        Log.e("username 2", userListData.get(1) + "");
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
                        ConnectDB connectDB = new ConnectDB(AddFriendsActivity.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(AddFriendsActivity.this,CountryListActivity.class);
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
                        task = "friends";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(AddFriendsActivity.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "option 4":
                        toastMessage("option 3");
                        break;
                }


            }
        });
        //friend List Start
        userList = (ListView) findViewById(R.id.userList);
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
        ListAdapter userAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userListData);
        userList.setAdapter(userAdapter);
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

                Intent viewProfileIntent = new Intent(AddFriendsActivity.this, ViewProfile.class);
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