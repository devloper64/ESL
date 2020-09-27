package com.example.englishessencelimited.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishessencelimited.R;
import com.example.englishessencelimited.model.ecom.Product;
import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

public class CheckOutAdapter extends RecyclerView.Adapter<CheckOutAdapter.CustomViewHolder>{
    private List<Cart> cartList;
    private Context context;

    public CheckOutAdapter( List<Cart> cartList,Context context){
        this.cartList=cartList;
        this.context=context;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.checkout_adapter, parent, false);
        return new CustomViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {

        final Cart cart = cartList.get(position);
        holder.id.setText(cart.getProducId());
        holder.name.setText(cart.getTittle().substring(0,15));
        holder.price.setText(cart.getPrice());

    }

    @Override
    public int getItemCount()
    {

        return cartList.size();
    }

    public class CustomViewHolder extends RecyclerView.ViewHolder {

        TextView id,name,price;

        public CustomViewHolder(View itemView) {
            super(itemView);
            id=itemView.findViewById(R.id.ckID);
            name=itemView.findViewById(R.id.ckName);
            price=itemView.findViewById(R.id.ckPrice);


        }


    }
}
