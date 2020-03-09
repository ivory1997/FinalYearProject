package finalyearproject.example.com.finalyearproject;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static android.support.v4.content.ContextCompat.getSystemService;

public class LoginActivity extends AppCompatActivity {

    Button btnLogin;
    EditText email,password;
    String loginEmail,loginPassword;
    String result1 = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btnLogin = (Button) findViewById(R.id.btnLogin);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        int maxLength = 30;
        email.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        password.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                loginEmail = email.getText().toString();
                loginPassword = password.getText().toString();
                if((loginEmail.equals(""))||(loginPassword.equals("")))
                {
                    toastMessage("All fields must be filled in");
                }
                else{
                    String task = "login";

                    ConnectDB connectDB = new ConnectDB(LoginActivity.this);
                    connectDB.execute(task,loginEmail,loginPassword);
                }


                //result1 = connectDB.doInBackground();
                //toastMessage(result1);

            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
