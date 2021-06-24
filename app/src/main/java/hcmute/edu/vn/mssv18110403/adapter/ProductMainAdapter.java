package hcmute.edu.vn.mssv18110403.adapter;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.mssv18110403.ProductDetail;
import hcmute.edu.vn.mssv18110403.R;
import hcmute.edu.vn.mssv18110403.model.Product2;

public class ProductMainAdapter extends RecyclerView.Adapter<ProductMainAdapter.ProductMainViewHolder> {

    Context context;
    List<Product2> product2List;

    public ProductMainAdapter(Context context, List<Product2> product2List) {
        this.context = context;
        this.product2List = product2List;
    }

    @NonNull
    @Override
    public ProductMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_in_main, parent, false);
        return new ProductMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductMainViewHolder holder, final int position) {

        Product2 product2 = product2List.get(position);

        holder.name.setText(product2List.get(position).get_name());

        byte[] img = product2.getImage();
        Bitmap bitmap1  = BitmapFactory.decodeByteArray(img, 0, img.length);
        holder.image.setImageBitmap(bitmap1);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i=new Intent(context, ProductDetail.class);
                i.putExtra("key_product", product2List.get(position).get_id());
                context.startActivity(i);

            }
        });

    }

    @Override
    public int getItemCount() {
        return product2List.size();
    }

    public  static class ProductMainViewHolder extends RecyclerView.ViewHolder{

        TextView name, description, price, qty, note;
        ImageView image;
        ConstraintLayout bg;

        public ProductMainViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.product_name);
            image = itemView.findViewById(R.id.productMainImage);

        }
    }

}
