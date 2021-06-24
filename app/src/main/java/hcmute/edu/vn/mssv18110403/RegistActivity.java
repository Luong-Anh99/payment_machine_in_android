package hcmute.edu.vn.mssv18110403;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110403.databaseHandler.DatabaseHelper;
import hcmute.edu.vn.mssv18110403.model.Account;

public class RegistActivity extends AppCompatActivity {
    DatabaseHelper db = new DatabaseHelper(this);
    TextView backLogin_btn;

    EditText name,password,email,confirm;
    Button btnregister;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
        
        backLogin_btn = (TextView)findViewById(R.id.gotoLogin);

        backLogin_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getBaseContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        EvenButtonRegist();
    }

    private void EvenButtonRegist() {
        btnregister = (Button)findViewById(R.id.btnRegister);
        name = (EditText)findViewById(R.id.inputUsername);
        password=(EditText)findViewById(R.id.inputPassword);
        confirm=(EditText)findViewById(R.id.inputConfirmed) ;
        email = (EditText)findViewById(R.id.inputEmail);

        btnregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName  =  name.getText().toString().trim();
                String inputPassword = password.getText().toString().trim();
                String confirmPassword = confirm.getText().toString().trim();
                String inputEmail = email.getText().toString().trim();

                if(inputName.length()<=0 || inputPassword.length()<=0)
                {

                    Toast.makeText(RegistActivity.this, "Please enter Username and password", Toast.LENGTH_SHORT).show();


                }
                else if((inputPassword.equals(confirmPassword) == false)){
                    Toast.makeText(RegistActivity.this, "confirm password wrong!!!", Toast.LENGTH_SHORT).show();
                }
                else {
                    if(inputEmail.length() == 0)
                    {
                        inputEmail = null;
                    }
                    db.addAccount(new Account(inputName,inputPassword,inputEmail));
                    Toast.makeText(RegistActivity.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getBaseContext(), MainActivity.class);
                    startActivity(intent);

                }
            }
        });

    }
}