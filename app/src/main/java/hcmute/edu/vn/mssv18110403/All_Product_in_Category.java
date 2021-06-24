package hcmute.edu.vn.mssv18110403;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110403.adapter.ProductAdapter;
import hcmute.edu.vn.mssv18110403.databaseHandler.DatabaseHelper;
import hcmute.edu.vn.mssv18110403.model.Product2;

public class All_Product_in_Category extends AppCompatActivity {
    ImageButton cartButton;
    RecyclerView productRecyclerView;
    ProductAdapter productAdapter;
    List<Product2> productList;
    EditText searchEdit;


    DatabaseHelper db = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__product_in__category);

        productRecyclerView = findViewById(R.id.productRecycler);

        Intent intent = getIntent();
        int id = intent.getIntExtra("key_product", 1);
        System.out.println(id);
        productList = new ArrayList<>();
        productList = db.getAllProductInCategry(id);
        setProductRecycler(productList);

        //Search product with name
        searchEdit = (EditText) findViewById(R.id.editSearch);

        searchEdit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });



        cartButton = (ImageButton)findViewById(R.id.cartButton);
        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(All_Product_in_Category.this, Summary.class);
                startActivity(intent);
            }
        });

    }

    private void filter(String text)
    {
        ArrayList<Product2> filterList = new ArrayList<>();
        for(Product2 product2 : productList ){
            if(product2.get_name().toLowerCase().contains(text.toLowerCase())){
                filterList.add(product2);

            }
        }
        productAdapter.filterList(filterList);
    }

    private void setProductRecycler(List<Product2> allproductModelList) {
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        productRecyclerView.setLayoutManager(layoutManager);
        productRecyclerView.addItemDecoration(new GridSpacingItemDecoration(1, dpToPx(16), true));
        productRecyclerView.setItemAnimator(new DefaultItemAnimator());
        productAdapter = new ProductAdapter(this,allproductModelList);
        productRecyclerView.setAdapter(productAdapter);
    }


    // now we need some item decoration class for manage spacing
    public class GridSpacingItemDecoration extends RecyclerView.ItemDecoration {

        private int spanCount;
        private int spacing;
        private boolean includeEdge;

        public GridSpacingItemDecoration(int spanCount, int spacing, boolean includeEdge) {
            this.spanCount = spanCount;
            this.spacing = spacing;
            this.includeEdge = includeEdge;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int position = parent.getChildAdapterPosition(view); // item position
            int column = position % spanCount; // item column

            if (includeEdge) {
                outRect.left = spacing - column * spacing / spanCount; // spacing - column * ((1f / spanCount) * spacing)
                outRect.right = (column + 1) * spacing / spanCount; // (column + 1) * ((1f / spanCount) * spacing)

                if (position < spanCount) { // top edge
                    outRect.top = spacing;
                }
                outRect.bottom = spacing; // item bottom
            } else {
                outRect.left = column * spacing / spanCount; // column * ((1f / spanCount) * spacing)
                outRect.right = spacing - (column + 1) * spacing / spanCount; // spacing - (column + 1) * ((1f /    spanCount) * spacing)
                if (position >= spanCount) {
                    outRect.top = spacing; // item top
                }
            }
        }
    }

    /**
     * Converting dp to pixel
     */
    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }
}