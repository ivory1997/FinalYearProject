package finalyearproject.example.com.finalyearproject;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.DhcpInfo;
import android.net.NetworkInfo;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Formatter;
import android.util.Log;
import android.widget.Button;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;


//Final year Project
//Author: Mark Ivory
//Student Number: C16307271
//Personal World Travel Map Application


public class MainActivity extends AppCompatActivity {

    Button RegisterButton;
    Button LoginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RegisterButton = (Button) findViewById(R.id.RegisterButton);
        LoginButton = (Button) findViewById(R.id.LoginButton);
        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
        LoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });
    }
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }


}
