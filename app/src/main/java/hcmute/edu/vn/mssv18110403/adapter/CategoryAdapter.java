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

import java.util.List;

import hcmute.edu.vn.mssv18110403.All_Product_in_Category;
import hcmute.edu.vn.mssv18110403.MainActivity;
import hcmute.edu.vn.mssv18110403.R;
import hcmute.edu.vn.mssv18110403.model.Category2;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    All_Product_in_Category all_product_in_category;
    MainActivity context;
    List<Category2> categoryList;

    public CategoryAdapter(MainActivity context, List<Category2> categoryList) {
        this.context = context;
        this.categoryList =categoryList;
    }
    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items,parent, false);

        return  new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category2 category = categoryList.get(position);

        byte[] img = category.getImage();
        Bitmap bitmap1  = BitmapFactory.decodeByteArray(img, 0, img.length);

        holder.categoryImageView.setImageBitmap(bitmap1);
        holder.categoryNameView.setText(category.get_name());



        holder.categoryImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, All_Product_in_Category.class);
                intent.putExtra("key_product",category.get_id());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryList.size();
    }

    public  static  class CategoryViewHolder extends  RecyclerView.ViewHolder{

        ImageView categoryImageView;
        TextView categoryNameView;
        public  CategoryViewHolder(@NonNull View itemView){

            super((itemView));
            categoryNameView=itemView.findViewById(R.id.text_category_name);
            categoryImageView = itemView.findViewById(R.id.categoryImage);
        }


    }

}
