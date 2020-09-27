package com.example.englishessencelimited.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.englishessencelimited.activity.DetailProduct;
import com.example.englishessencelimited.R;
import com.example.englishessencelimited.database.CartViewModel;
import com.example.englishessencelimited.model.ecom.Product;
import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> productList;
    private Context context;
    private CartViewModel cartViewModel;
    private LifecycleOwner lifecycleOwner;


    public ProductAdapter(List<Product> productList, Context context,CartViewModel cartViewModel,LifecycleOwner lifecycleOwner) {
        this.productList = productList;
        this.context = context;
        this.cartViewModel=cartViewModel;
        this.lifecycleOwner=lifecycleOwner;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.ecom_layout, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Product product = productList.get(position);
        holder.productName.setText(product.getName());
        holder.price.setText(product.getPrice().getCurrents().getText());
        Log.d("dddddd", "" + product.getImageUrl());
        Glide.with(context)
                .load("http://" + product.getImageUrl())
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(holder.img);

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int position, boolean isLonkClick) {
                Context context = view.getContext();
                Intent intent = new Intent(context, DetailProduct.class);
                intent.putExtra("productId", product.getId());
                intent.putExtra("picture", product.getImageUrl());
                intent.putExtra("price", product.getPrice().getCurrents().getText());
                context.startActivity(intent);
            }
        });

        Cart cart=new Cart(product.getId(),product.getName(),product.getPrice().getCurrents().getText());

        cartViewModel.getCartByPId(product.getId()).observe(lifecycleOwner, new Observer<Cart>() {
            @Override
            public void onChanged(Cart cart1) {
                try{
                 if (cart1.getProducId().equals(product.getId())){
                     holder.button.setText("InCart");
                     holder.button.setBackgroundResource(R.drawable.cart1);
                 }
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
        });

        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (holder.button.getText().equals("InCart")){
                    holder.button.setText("Add to Cart");
                    cartViewModel.deleteBypId(product.getId());
                    holder.button.setBackgroundResource(R.drawable.cart2);
                }else {
                    cartViewModel.insert(cart);
                    holder.button.setText("InCart");
                    holder.button.setBackgroundResource(R.drawable.cart1);
                }

            }
        });



    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView productName, price;
        private ImageView img;
        private ItemClickListener itemClickListener;
        private Button button;

        public ViewHolder(View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.txt_name);
            img = itemView.findViewById(R.id.image_view);
            price = itemView.findViewById(R.id.price);
            button=itemView.findViewById(R.id.cart);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

    }

    public interface ItemClickListener {

        void onClick(View view, int position, boolean isLonkClick);
    }

}
