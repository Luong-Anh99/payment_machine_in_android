package hcmute.edu.vn.mssv18110403;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110403.databaseHandler.DatabaseHelper;
import hcmute.edu.vn.mssv18110403.model.Account;

public class LoginActivity extends AppCompatActivity {

    TextView login_btn, regist_btn, forgotPass_btn, info;
    EditText Name, Password;

    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Name=(EditText)findViewById(R.id.inputEmailLogin);
        Password = (EditText) findViewById(R.id.inputPasswordLogin);
        login_btn = (TextView)findViewById(R.id.btnLogin);
        regist_btn=(TextView)findViewById(R.id.gotoRegister);
        forgotPass_btn=(TextView)findViewById(R.id.forgotPassword);
        info = (TextView)findViewById(R.id.info);


        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentRegist = new Intent(getBaseContext(), RegistActivity.class);
                startActivity(intentRegist);
            }
        });

        forgotPass_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentForgotPass = new Intent(getBaseContext(), ForgotPassActivity.class);
                startActivity(intentForgotPass);
            }
        });
        info.setText(" ");
        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate(Name.getText().toString().trim(), Password.getText().toString().trim());
            }
        });
    }
    private void validate(String userName, String userPassword)
    {
        List<Account> accountList = new ArrayList<>();
        accountList = db.getAllAccount();

        for (int i = 0;i<accountList.size();i++){
            String name = accountList.get(i).get_name();
            String password = accountList.get(i).getPassword();

            if(userName.equals(name)==false && userPassword.equals(password)==false){
                info.setText("Username or password is incorrect. Please re-enter!!!");
            }
            else {
                info.setText(" ");
                Intent intentLogin= new Intent(getBaseContext(), MainActivity.class);
                startActivity(intentLogin);
            }
        }
    }
}