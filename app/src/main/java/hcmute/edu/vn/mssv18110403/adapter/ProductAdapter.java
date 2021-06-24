package hcmute.edu.vn.mssv18110403.adapter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import hcmute.edu.vn.mssv18110403.All_Product_in_Category;
import hcmute.edu.vn.mssv18110403.ProductDetail;
import hcmute.edu.vn.mssv18110403.R;
import hcmute.edu.vn.mssv18110403.model.Product2;

public class  ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder>  {

    All_Product_in_Category context;
    List<Product2> productList;
    List<Product2> productListOld;


    public ProductAdapter(All_Product_in_Category context, List<Product2> productList) {
        this.context = context;
        this.productList =productList;
        this.productListOld = productList;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_row_items,parent, false);
        return  new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product2 product = productList.get(position);

        byte[] img = product.getImage();
        Bitmap bitmap1  = BitmapFactory.decodeByteArray(img, 0, img.length);

        holder.productImageView.setImageBitmap(bitmap1);
        holder.productNameView.setText(product.get_name());
        holder.orderView.setText("Click here to Order");
        holder.orderView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent  = new Intent(context, ProductDetail.class);
                intent.putExtra("key_product",product.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }



    public  static  class ProductViewHolder extends  RecyclerView.ViewHolder{

        ImageView productImageView;
        TextView productNameView;
        TextView orderView;
        public  ProductViewHolder(@NonNull View itemView){

            super((itemView));
            productNameView=itemView.findViewById(R.id.productName);
            productImageView = itemView.findViewById(R.id.productImage);
            orderView = itemView.findViewById(R.id.clickheretoorder);
        }
    }

    public void filterList(ArrayList<Product2> filterList){
        productList = filterList;
        notifyDataSetChanged();
    }

    /*
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if(strSearch.isEmpty()){
                    productList = productListOld;
                }else {
                    List<Product2> list  = new ArrayList<>();
                    for(Product2 product2 : productListOld){
                        if(product2.get_name().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(product2);
                        }
                    }
                    productList = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = productList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                productList = (List<Product2>) results.values;
                notifyDataSetChanged();
            }
        };
    }*/
}
