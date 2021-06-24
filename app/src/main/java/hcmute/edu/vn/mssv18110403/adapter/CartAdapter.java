 package hcmute.edu.vn.mssv18110403.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.util.ArrayList;

import hcmute.edu.vn.mssv18110403.MainActivity;
import hcmute.edu.vn.mssv18110403.R;
import hcmute.edu.vn.mssv18110403.Summary;
import hcmute.edu.vn.mssv18110403.model.Cart;

 public class CartAdapter extends BaseAdapter {

     Context context;
     ArrayList<Cart> arrayCart;

     public CartAdapter(Context context, ArrayList<Cart> arrayCart) {
         this.context = context;
         this.arrayCart = arrayCart;
     }

     @Override
     public int getCount() {
         return arrayCart.size();
     }

     @Override
     public Object getItem(int position) {
         return arrayCart.get(position);
     }

     @Override
     public long getItemId(int position) {
         return position;
     }

     public  class ViewHolder{
        public TextView txtNameItem,txtPriceItem;
        public ImageView imgItem;
        public Button btnminus, btnplus, btnvalues;
     }
     @Override
     public View getView(int position, View view, ViewGroup viewGroup) {
          ViewHolder  viewHolder = null;

         if(view == null){
             viewHolder = new ViewHolder();

              view = LayoutInflater.from(context).inflate(R.layout.cart_row_items,viewGroup, false);

              viewHolder.txtNameItem =(TextView) view.findViewById(R.id.itemName);
              viewHolder.btnvalues =(Button) view.findViewById(R.id.cartItemQuantity);
              viewHolder.txtPriceItem =(TextView) view.findViewById(R.id.cartItemPrice);
              viewHolder.imgItem =(ImageView) view.findViewById(R.id.cartProductImage);
              viewHolder.btnplus =(Button) view.findViewById(R.id.buttonplus);
              viewHolder.btnminus =(Button) view.findViewById(R.id.buttonminus);
              view.setTag(viewHolder);
         }else {
             viewHolder = (ViewHolder) view.getTag();
         }

         Cart cart = (Cart) getItem(position);
         viewHolder.txtNameItem.setText(cart.getName());
         viewHolder.btnvalues.setText(cart.getQuantity() +"");
         DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
         viewHolder.txtPriceItem.setText(decimalFormat.format( cart.getPrice()) +" D");

         byte[] img = cart.getImage();
         Bitmap bitmap1  = BitmapFactory.decodeByteArray(img, 0, img.length);
         viewHolder.imgItem.setImageBitmap(bitmap1);

         int quantity =Integer.parseInt( viewHolder.btnvalues.getText().toString());
         if(quantity <=1){
             viewHolder.btnminus.setVisibility(View.INVISIBLE);
         }
         else {
             viewHolder.btnminus.setVisibility(View.VISIBLE);
         }
         ViewHolder finalViewHolder = viewHolder;
         viewHolder.btnplus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 int newQuantity = Integer.parseInt(finalViewHolder.btnvalues.getText().toString())+1;
                 int nowQuantity = MainActivity.arrayCart.get(position).getQuantity();
                 double nowPrice = MainActivity.arrayCart.get(position).getPrice();

                 MainActivity.arrayCart.get(position).setQuantity(newQuantity);
                 double newPrice = (nowPrice *newQuantity) / nowQuantity;
                 MainActivity.arrayCart.get(position).setPrice(newPrice);

                 DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                 finalViewHolder.txtPriceItem.setText(decimalFormat.format(newPrice) +" D");
                 Summary.EvenUltil();
                 if(newQuantity >10)
                 {
                     finalViewHolder.btnplus.setVisibility(View.INVISIBLE);
                     finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnvalues.setText(String.valueOf(newQuantity));
                 }else {
                     finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnvalues.setText(String.valueOf(newQuantity));
                 }
             }
         });

         viewHolder.btnminus.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {

                 int newQuantity = Integer.parseInt(finalViewHolder.btnvalues.getText().toString())-1;
                 int nowQuantity = MainActivity.arrayCart.get(position).getQuantity();
                 double nowPrice = MainActivity.arrayCart.get(position).getPrice();

                 MainActivity.arrayCart.get(position).setQuantity(newQuantity);
                 double newPrice = (nowPrice *newQuantity) / nowQuantity;
                 MainActivity.arrayCart.get(position).setPrice(newPrice);

                 DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
                 finalViewHolder.txtPriceItem.setText(decimalFormat.format(newPrice) +" D");
                 Summary.EvenUltil();
                 if(newQuantity <2)
                 {
                     finalViewHolder.btnminus.setVisibility(View.INVISIBLE);
                     finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnvalues.setText(String.valueOf(newQuantity));
                 }else {
                     finalViewHolder.btnminus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnplus.setVisibility(View.VISIBLE);
                     finalViewHolder.btnvalues.setText(String.valueOf(newQuantity));
                 }
             }
         });

         return view;
     }
 }
