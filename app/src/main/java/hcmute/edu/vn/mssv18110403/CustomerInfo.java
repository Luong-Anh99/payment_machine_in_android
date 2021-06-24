package hcmute.edu.vn.mssv18110403;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110403.databaseHandler.DatabaseHelper;
import hcmute.edu.vn.mssv18110403.model.Customer;
import hcmute.edu.vn.mssv18110403.model.OrderDetail;

public class CustomerInfo extends AppCompatActivity {
    EditText editcustomername,editcustomerphone,editcustomeremail;
    Button btnconfirm, btnback;
    DatabaseHelper  db = new DatabaseHelper(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_info);

        List<Customer> cateList = new ArrayList<>();
        cateList = db.getAllCustomer();
        int cout =cateList.size() + 1;
        System.out.println(cout);

        Anhxa();
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        EvenButton(cout);
    }

    private void EvenButton(int cout) {
        btnconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = editcustomername.getText().toString().trim();
                String phone = editcustomerphone.getText().toString().trim();
                String email = editcustomeremail.getText().toString().trim();
                if(name.length()>0 && phone.length()>0&&email.length()>0){


                    db.addCustomer(new Customer(cout,name,null,phone,email));
                    for(int j = 0;j<MainActivity.arrayCart.size();j++){
                        int productId = MainActivity.arrayCart.get(j).getId_product();
                        int quantity = MainActivity.arrayCart.get(j).getQuantity();
                        double price = MainActivity.arrayCart.get(j).getPrice();
                        db.addOrderDetail(new OrderDetail(cout,productId,quantity,price));
                    }
                    MainActivity.arrayCart.clear();
                    Toast.makeText(CustomerInfo.this, "Success", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(intent);
                }
                else {
                    Toast.makeText(CustomerInfo.this, "Check again your data input", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void Anhxa() {
        editcustomername = (EditText)findViewById(R.id.edittextnamecustomer);
        editcustomerphone=(EditText)findViewById(R.id.edittextphonecustomer);
        editcustomeremail = (EditText)findViewById(R.id.edittextmailcustomer);
        btnconfirm = (Button)findViewById(R.id.buttonconfirm);
        btnback = (Button)findViewById(R.id.buttonback);
    }
}