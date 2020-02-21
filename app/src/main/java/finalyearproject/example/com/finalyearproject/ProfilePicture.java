package finalyearproject.example.com.finalyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;
import java.io.BufferedReader;
import java.net.URLEncoder;
import java.util.Map;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.URL;
import javax.net.ssl.HttpsURLConnection;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;

public class ProfilePicture extends AppCompatActivity {
    //ProfilePicture obj = new ProfilePicture();
    private ImageView showImage;
    private Button btnGetImage;
    private Button btnUploadImage;
    String name;
    String email;
    private String profilePicString;
    private Bitmap profilePicBitmap;
    private TextView userName;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private ImageView profilePic;
    ArrayList<String> countries = new ArrayList<>();
    ArrayList<String> countryNames = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_picture);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        countries = receivedIntent.getStringArrayListExtra("countries");
        countryNames = receivedIntent.getStringArrayListExtra("countryNames");
        btnGetImage = (Button) findViewById(R.id.btnGetImage);
        btnUploadImage = (Button) findViewById(R.id.btnUploadImage);
        navigationList = (ListView) findViewById(R.id.navigationList);
        avatar = (ImageView) findViewById(R.id.avatar);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        profilePicString = receivedIntent.getStringExtra("profilePicString");
        byte [] encodeByte= Base64.decode(profilePicString, Base64.DEFAULT);
        profilePicBitmap= BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
        avatar.setImageBitmap(profilePicBitmap);
        profilePic.setImageBitmap(profilePicBitmap);
        //obj.setProfilePicBitmap(profilePicBitmap);
        //obj.setProfilePicString(profilePicString);
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        final ArrayList<String> listData = new ArrayList<>();
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
                        profilePicString = receivedIntent.getStringExtra("profilePicString");
                        ConnectDB connectDB = new ConnectDB(ProfilePicture.this);
                        connectDB.execute(task,email,name,profilePicString);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(ProfilePicture.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        CountryListIntent.putExtra("profilePicString", profilePicString);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
                        break;
                    case "Friends List":
                        toastMessage("Friends List");
                        task = "friends";
                        ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(ProfilePicture.this);
                        AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                        connectDBPassArray.execute(AsyncTaskParams);
                        break;
                    case "Random Country Picker":
                        toastMessage("Random Country Picker");
                        Intent RandomCountryIntent = new Intent(ProfilePicture.this,RandomCountry.class);
                        RandomCountryIntent.putExtra("email",email);
                        RandomCountryIntent.putExtra("name",name);
                        RandomCountryIntent.putExtra("profilePicString", profilePicString);
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
                Intent viewProfileIntent = new Intent(ProfilePicture.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putExtra("profilePicString", profilePicString);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });

        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), 1);
                /*
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
*/
                Intent getIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //obj.setProfilePicBitmap(profilePicBitmap);
                //obj.setProfilePicString(profilePicString);
                Log.e("profilePicStringget", profilePicString + "");
                //startActivityForResult(getIntent, RESULT_LOAD_IMAGE);
            }
        });
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //profilePicBitmap = obj.getProfilePicBitmap();
                //profilePicString = obj.getProfilePicString();
                /*
                profilePic.buildDrawingCache (true);
                profilePicBitmap = profilePic.getDrawingCache (true);

                BitmapDrawable drawable = (BitmapDrawable)profilePic.getDrawable();
                Bitmap bitmap = drawable.getBitmap();
                */
                profilePicBitmap = ((BitmapDrawable)profilePic.getDrawable()).getBitmap();
                ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                profilePicBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
                byte [] arr=baos.toByteArray();
                profilePicString=Base64.encodeToString(arr, Base64.DEFAULT);
                Log.e("profilePicStringupload", profilePicString + "");
                avatar.setImageBitmap(profilePicBitmap);
                String task = "profilePic";
                ConnectDBPassArray connectDBPassArray = new ConnectDBPassArray(ProfilePicture.this);
                AsyncTaskParams AsyncTaskParams = new AsyncTaskParams(task,email,name,profilePicString,countries,countryNames);
                connectDBPassArray.execute(AsyncTaskParams);


            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
    /*
    public void setProfilePicString( String profilePicString2)
    {
        profilePicString = profilePicString2;
    }
    public void setProfilePicBitmap( Bitmap profilePicBitmap2)
    {
        profilePicBitmap = profilePicBitmap2;
    }
    public String getProfilePicString(){
        return profilePicString;
    }
    public Bitmap getProfilePicBitmap(){
        return profilePicBitmap;
    }
*/
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {

            Uri uri = data.getData();

            try {
                profilePicBitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);



                profilePic.setImageBitmap(profilePicBitmap);
                ByteArrayOutputStream baos=new  ByteArrayOutputStream();
                profilePicBitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
                byte [] arr=baos.toByteArray();
                profilePicString=Base64.encodeToString(arr, Base64.DEFAULT);
                //obj.setProfilePicBitmap(profilePicBitmap);
                //obj.setProfilePicString(profilePicString);
                Log.e("profilePicStringget", profilePicString + "");



            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
