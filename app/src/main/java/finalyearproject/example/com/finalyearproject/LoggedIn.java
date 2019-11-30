package finalyearproject.example.com.finalyearproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class LoggedIn extends AppCompatActivity {
    String name;
    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logged_in);
        String task = "countries";
        Intent receivedIntent = getIntent();
        name = receivedIntent.getStringExtra("name");
        email = receivedIntent.getStringExtra("email");
        //countriesLength = receivedIntent.getStringExtra("countriesLength");
        //int countriesLengthInt = Integer.parseInt(countriesLength);
        //String[] countries = new String[countriesLengthInt];
        //countries = receivedIntent.getStringExtra("countries");
        ConnectDB connectDB = new ConnectDB(LoggedIn.this);
        connectDB.execute(task,email,name);
    }
}
