package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
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
    private String updateEmail2;
    private String updatePassword2;
    private String updateName2;
    private TextView userName, welcomeMessage, nameView, emailView;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private ImageView profilePic;

    EditText updateEmail,updatePassword,updateName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        btnEdit = (Button) findViewById(R.id.btnEdit);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
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
        userName = (TextView) findViewById(R.id.userName);
        userName.setText(name);
        final ArrayList<String> listData = new ArrayList<>();
        listData.add("My Map");
        listData.add("option 2");
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
                        ConnectDB connectDB = new ConnectDB(EditActivity.this);
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

                Intent viewProfileIntent = new Intent(EditActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
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