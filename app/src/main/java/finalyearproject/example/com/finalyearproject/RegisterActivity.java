package finalyearproject.example.com.finalyearproject;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    Button btnRegister;
    EditText newName, newEmail,newPassword;
    String stringName, stringEmail,stringPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        newName = (EditText) findViewById(R.id.newName);
        newEmail = (EditText) findViewById(R.id.newEmail);
        newPassword = (EditText) findViewById(R.id.newPassword);
        int maxLength = 30;
        newName.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        newEmail.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});
        newPassword.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength)});

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringName = newName.getText().toString();
                stringEmail = newEmail.getText().toString();
                stringPassword = newPassword.getText().toString();
                String task = "register";
                ConnectDB connectDB = new ConnectDB(RegisterActivity.this);
                connectDB.execute(task,stringEmail, stringPassword, stringName);
                //finish();
            }
        });
    }
}
