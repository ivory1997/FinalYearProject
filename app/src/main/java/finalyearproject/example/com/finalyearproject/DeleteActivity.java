package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class DeleteActivity extends AppCompatActivity {

    Button btnDelete;
    private String name;
    private String email;
    TextView deletionMessage;
    private TextView userName;
    private ListView navigationList;
    private RelativeLayout profileBox;
    private ImageView avatar;
    private ImageView profilePic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete);
        btnDelete = (Button) findViewById(R.id.btnDelete);
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        deletionMessage = (TextView) findViewById(R.id.textView1);
        deletionMessage.setText("Are you sure you want to delete your profile "+ email+"?");

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
                        ConnectDB connectDB = new ConnectDB(DeleteActivity.this);
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

                Intent viewProfileIntent = new Intent(DeleteActivity.this, ViewProfile.class);
                viewProfileIntent.putExtra("name", name);
                viewProfileIntent.putExtra("email", email);
                startActivity(viewProfileIntent);

            }
        });




        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = "delete";

                ConnectDB connectDB = new ConnectDB(DeleteActivity.this);
                connectDB.execute(task,email);


            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}