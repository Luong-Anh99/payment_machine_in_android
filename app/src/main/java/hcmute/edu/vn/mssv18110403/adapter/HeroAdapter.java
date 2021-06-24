package hcmute.edu.vn.mssv18110403.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import hcmute.edu.vn.mssv18110403.R;
import hcmute.edu.vn.mssv18110403.model.Hero;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    Context context;
    List<Hero> heroList;

    public HeroAdapter(Context context, List<Hero> hreosList) {
        this.context = context;
        this.heroList =hreosList;
    }

    @NonNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.category_row_items,parent, false);
        return  new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HeroViewHolder holder, int position) {
        Hero hero = heroList.get(position);
        holder.heroImageView.setImageResource(heroList.get(position).getImage());
        holder.heroNameView.setText(hero.getName());
    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }


    public  static  class HeroViewHolder extends  RecyclerView.ViewHolder{

        ImageView heroImageView;
        TextView heroNameView;
        public  HeroViewHolder(@NonNull View itemView){

            super((itemView));
            heroNameView=itemView.findViewById(R.id.text_category_name);
            heroImageView = itemView.findViewById(R.id.categoryImage);
        }
    }
}
