package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewProfile extends AppCompatActivity {
    Button btnEdit, btnDelete;
    private String name, email;
    private ImageView avatar;
    String profilePicString;
    Bitmap profilePicBitmap;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    private TextView userName, welcomeMessage, nameView, emailView;
    private ListView navigationList2;
    private RelativeLayout profileBox;
    private ImageView profilePic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_profile);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        btnDelete = (Button) findViewById(R.id.btnDelete);

        Intent receivedIntent2 = getIntent();
        name = receivedIntent2.getStringExtra("name");
        email = receivedIntent2.getStringExtra("email");
        countries = receivedIntent2.getStringArrayListExtra("countries");
        countryNames = receivedIntent2.getStringArrayListExtra("countryNames");
        nameView = (TextView) findViewById(R.id.namev);
        emailView = (TextView) findViewById(R.id.emailv);
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        nameView.setText("Name: " + name);
        emailView.setText("Email: " + email);


        //profilePicString = receivedIntent2.getStringExtra("profilePicString");
        Globals g = (Globals)getApplication();
        String  data=g.getData();
        profilePicString = g.getData();
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        Log.e("pictureString4", profilePicString + "");
        avatar = (ImageView) findViewById(R.id.avatar);
        avatar.setImageBitmap(profilePicBitmap);
        profilePic.setImageBitmap(profilePicBitmap);
        final ArrayList<String> listData2 = new ArrayList<>();
        listData2.add("My Map");
        listData2.add("Country List");
        listData2.add("Friends List");
        listData2.add("Random Country Picker");
        listData2.add("Country Recommender");
        listData2.add("Log out");
        navigationList2 = (ListView) findViewById(R.id.navigationList);
        ListAdapter adapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listData2);
        navigationList2.setAdapter(adapter2);
        navigationList2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String name2 = listData2.get(i).toString();
                switch(name2)
                {
                    case "My Map":
                        String task = "countries";
                        Intent receivedIntent = getIntent();
                        name = receivedIntent.getStringExtra("name");
                        email = receivedIntent.getStringExtra("email");
                        profilePicString = receivedIntent.getStringExtra("profilePicString");
                        //countriesLength = receivedIntent.getStringExtra("countriesLength");
                        //int countriesLengthInt = Integer.parseInt(countriesLength);
                        //String[] countries = new String[countriesLengthInt];
                        //countries = receivedIntent.getStringExtra("countries");
                        ConnectDB connectDB = new ConnectDB(ViewProfile.this);
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
                        ConnectDB connectDB2 = new ConnectDB(ViewProfile.this);
                        connectDB2.execute(task,email,name,profilePicString);
                        /*
                        Intent CountryListIntent = new Intent(ViewProfile.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        //CountryListIntent.putExtra("profilePicString", profilePicString);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
                        */

                        break;
                    case "Friends List":
                        toastMessage("Friends List");
                        task = "friends";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(ViewProfile.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(ViewProfile.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        RandomCountryIntent.putExtra("profilePicString", profilePicString);
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
/*
        profileBox = (RelativeLayout)findViewById(R.id.profileBox);
        profileBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent EditProfileIntent = new Intent(ViewProfile.this,ViewProfile.class);
                EditProfileIntent.putExtra("name",name);
                EditProfileIntent.putExtra("email",email);
                startActivity(EditProfileIntent);

            }
        });
        */


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent editProfileIntent = new Intent(ViewProfile.this,EditActivity.class);
                editProfileIntent.putExtra("name",name);
                editProfileIntent.putExtra("email",email);
                editProfileIntent.putExtra("profilePicString", profilePicString);
                editProfileIntent.putStringArrayListExtra("countries", countries);
                editProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(editProfileIntent);



            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent deleteProfileIntent = new Intent(ViewProfile.this,DeleteActivity.class);
                deleteProfileIntent.putExtra("name",name);
                deleteProfileIntent.putExtra("email",email);
                deleteProfileIntent.putExtra("profilePicString", profilePicString);
                deleteProfileIntent.putStringArrayListExtra("countries", countries);
                deleteProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(deleteProfileIntent);

            }
        });
        profilePic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent profilePicIntent = new Intent(ViewProfile.this,ProfilePicture.class);
                profilePicIntent.putExtra("name",name);
                profilePicIntent.putExtra("email",email);
                //profilePicIntent.putExtra("profilePicString", profilePicString);
                profilePicIntent.putStringArrayListExtra("countries", countries);
                profilePicIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(profilePicIntent);

            }
        });


    }


    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
