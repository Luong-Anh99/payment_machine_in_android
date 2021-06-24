package hcmute.edu.vn.mssv18110403;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import hcmute.edu.vn.mssv18110403.databaseHandler.DatabaseHelper;
import hcmute.edu.vn.mssv18110403.model.Cart;
import hcmute.edu.vn.mssv18110403.model.Product2;

public class ProductDetail extends AppCompatActivity {

    DatabaseHelper db = new DatabaseHelper(this);
    Product2 product;

    ImageView imageView;
    ImageButton plusquantity, minusquantity;
    TextView quantitynumber, price, productname;
    TextView description;
    Button addtocart;
    int quantity=1;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        Intent intent = getIntent();
         id = intent.getIntExtra("key_product", 1);



        product = db.getProduct(id);


        byte[] img = product.getImage();
        Bitmap bitmap1  = BitmapFactory.decodeByteArray(img, 0, img.length);


        addtocart = findViewById(R.id.summary);
        imageView = findViewById(R.id.imageViewInfo);
        plusquantity = findViewById(R.id.addquantity);
        minusquantity = findViewById(R.id.subquantity);
        quantitynumber = findViewById(R.id.quantity);
        productname = findViewById(R.id.productNameinInfo);
        price = findViewById(R.id.productPrice);
        description = findViewById(R.id.descriptioninfo);

        productname.setText(product.get_name());
        imageView.setImageBitmap(bitmap1);
        price.setText(String.valueOf(product.getPrice()));
        description.setText(product.getDescription());

        plusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // coffee price
                quantity++;
                displayQuantity();
            }
        });
        minusquantity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // because we dont want the quantity go less than 0
                if (quantity == 1) {
                    Toast.makeText(ProductDetail.this, "Cant decrease quantity = 0", Toast.LENGTH_SHORT).show();
                } else {
                    quantity--;
                    displayQuantity();

                }
            }
        });
        setAddtocart();

    }
    private void displayQuantity() {
        quantitynumber.setText(String.valueOf(quantity));
    }

    private  void setAddtocart()
    {
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(MainActivity.arrayCart.size()>=0){
                    int sl = quantity;
                    boolean exists= false;
                    for(int i=0;i<MainActivity.arrayCart.size();i++)
                    {
                        if(MainActivity.arrayCart.get(i).getId_product()==id){
                            MainActivity.arrayCart.get(i).setQuantity(MainActivity.arrayCart.get(i).getQuantity()  + sl);
                            if(MainActivity.arrayCart.size()>=10){
                                MainActivity.arrayCart.get(i).setQuantity(10);
                            }
                            MainActivity.arrayCart.get(i).setPrice(product.getPrice()*MainActivity.arrayCart.get(i).getQuantity());
                            exists =true;
                        }
                    }
                    if(exists == false){
                        int count = quantity;
                        double price = sl * product.getPrice();
                        MainActivity.arrayCart.add(new Cart(product.get_id(),product.get_name(),count,price,product.getImage() ));
                    }
                }
                else {
                    int count = quantity;
                    double price = count * product.getPrice();
                    MainActivity.arrayCart.add(new Cart(product.get_id(),product.get_name(),count,price,product.getImage() ));
                }
                Toast.makeText(ProductDetail.this, "Add to cart Success", Toast.LENGTH_SHORT).show();
                finish();
            }

        });
    }
}