package hcmute.edu.vn.mssv18110403;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.text.DecimalFormat;

import hcmute.edu.vn.mssv18110403.adapter.CartAdapter;

public class Summary extends AppCompatActivity {
    ListView lvcart;
    static TextView txtTotalPrice;
    Button btnCheckout,btnBack;
    Toolbar toolbarMessage;
    TextView txtMessage;
    CartAdapter cartAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        Anhxa();
        ActionToolbar();
        CheckData();
        EvenUltil();
        CactchOnItemLisView();
        EvenClick();

    }

    private void EvenClick() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
        btnCheckout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayCart.size()>0){
                    Intent intent = new Intent(Summary.this,CustomerInfo.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(Summary.this, "your cart don't have product to checkout", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CactchOnItemLisView() {
        lvcart.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Summary.this);
                builder.setTitle("Confirm detete product");
                builder.setMessage("Do you want delete this product ?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if(MainActivity.arrayCart.size()<=0){
                            txtMessage.setVisibility(View.VISIBLE);
                        }else {
                            MainActivity.arrayCart.remove(position);
                            cartAdapter.notifyDataSetChanged();
                            EvenUltil();
                            if(MainActivity.arrayCart.size()<=0){
                                txtMessage.setVisibility(View.VISIBLE);

                            }else {
                                txtMessage.setVisibility(View.INVISIBLE);
                                cartAdapter.notifyDataSetChanged();
                                EvenUltil();
                            }
                        }

                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cartAdapter.notifyDataSetChanged();
                        EvenUltil();

                    }
                });
                builder.show();
                return true;
            }
        });
    }
    public static void EvenUltil() {
        double totalMoney = 0;
        for(int i = 0; i<MainActivity.arrayCart.size();i++){
            totalMoney += MainActivity.arrayCart.get(i).getPrice();
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        txtTotalPrice.setText(decimalFormat.format(totalMoney) + "Đ");
    }

    private void CheckData() {
        if(MainActivity.arrayCart.size()<=0){
            cartAdapter.notifyDataSetChanged();
            txtMessage.setVisibility(View.VISIBLE);
            lvcart.setVisibility(View.INVISIBLE);
        }
        else {
            cartAdapter.notifyDataSetChanged();
            txtMessage.setVisibility(View.INVISIBLE);
            lvcart.setVisibility(View.VISIBLE);
        }
    }

    private void ActionToolbar() {
        if(MainActivity.arrayCart.size()<=0)
        {
            setSupportActionBar(toolbarMessage);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            toolbarMessage.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });

        }
    }

    private void Anhxa() {
        lvcart = (ListView) findViewById(R.id.listViewCart);
        txtMessage = (TextView) findViewById(R.id.textCartMessage);
        txtTotalPrice = (TextView) findViewById(R.id.totalPrice);
        btnCheckout =(Button) findViewById(R.id.btnCkeckout);
        btnBack =(Button) findViewById(R.id.btnBackHome);
        cartAdapter = new CartAdapter(Summary.this,MainActivity.arrayCart);
        toolbarMessage = (Toolbar) findViewById(R.id.toolbarCart);
        lvcart.setAdapter(cartAdapter);
    }
}