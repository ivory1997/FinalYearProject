package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Base64;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;
import android.widget.TextView;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class EditActivity extends AppCompatActivity {

    Button btnEdit;
    private String name;
    private String email;
    String profilePicString;
    Bitmap profilePicBitmap;
    private String updateEmail2;
    private String updatePassword2;
    private String updateName2;
    private TextView userName, welcomeMessage, nameView, emailView;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private ImageView profilePic;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();

    EditText updateEmail,updatePassword,updateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        updateEmail = (EditText) findViewById(R.id.updateEmail);
        updatePassword = (EditText) findViewById(R.id.updatePassword);
        updateName = (EditText) findViewById(R.id.updateName);
        int maxLength = 30;
        updateEmail.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        updatePassword.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        updateName.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        updateEmail2 = updateEmail.getText().toString();
        updatePassword2 = updatePassword.getText().toString();
        updateName2 = updateName.getText().toString();
        navigationList = (ListView) findViewById(R.id.navigationList);
        avatar = (ImageView) findViewById(R.id.avatar);
        //profilePicString = receivedIntent.getStringExtra("profilePicString");
        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        final ArrayList<String> listData = new ArrayList<>();
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
                        ConnectDB connectDB = new ConnectDB(EditActivity.this);
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
                        ConnectDB connectDB2 = new ConnectDB(EditActivity.this);
                        connectDB2.execute(task,email,name,profilePicString);
                        /*
                        Intent CountryListIntent = new Intent(EditActivity.this,CountryListActivity.class);
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
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(EditActivity.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(EditActivity.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
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
        profileBox = (RelativeLayout) findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent viewProfileIntent = new Intent(EditActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putExtra("profilePicString", profilePicString);
                //viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);
            }
        });





        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "edit";
                updateEmail2 = updateEmail.getText().toString();
                updatePassword2 = updatePassword.getText().toString();
                updateName2 = updateName.getText().toString();
                ConnectDB connectDB = new ConnectDB(EditActivity.this);
                connectDB.execute(task,email,updateEmail2,updatePassword2,updateName2);


            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}