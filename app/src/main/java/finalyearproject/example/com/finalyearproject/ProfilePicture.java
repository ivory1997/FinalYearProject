package finalyearproject.example.com.finalyearproject;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
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
    private ImageView showImage;
    private Button btnGetImage;
    private Button btnUploadImage;
    String name;
    String email;
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
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        final ArrayList<String> listData = new ArrayList<>();
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
                        ConnectDB connectDB = new ConnectDB(ProfilePicture.this);
                        connectDB.execute(task,email,name);
                        break;
                    case "Country List":
                        toastMessage("Country List");
                        Intent CountryListIntent = new Intent(ProfilePicture.this,CountryListActivity.class);
                        CountryListIntent.putExtra("email",email);
                        CountryListIntent.putExtra("name",name);
                        CountryListIntent.putStringArrayListExtra("countries", countries);
                        CountryListIntent.putStringArrayListExtra("countryNames", countryNames);
                        startActivity(CountryListIntent);
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

                Intent viewProfileIntent = new Intent(ProfilePicture.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                viewProfileIntent.putStringArrayListExtra("countries", countries);
                viewProfileIntent.putStringArrayListExtra("countryNames", countryNames);
                startActivity(viewProfileIntent);

            }
        });

        btnGetImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*
                Intent intent = new Intent();

                intent.setType("image/*");

                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Select Image From Gallery"), 1);
*/
                Intent getIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                //startActivityForResult(getIntent, RESULT_LOAD_IMAGE);
            }
        });
        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });

    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }

}
