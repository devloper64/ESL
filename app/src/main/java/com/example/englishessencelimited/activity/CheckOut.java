package com.example.englishessencelimited.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.englishessencelimited.R;
import com.example.englishessencelimited.adapter.CheckOutAdapter;
import com.example.englishessencelimited.database.CartViewModel;
import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

public class CheckOut extends AppCompatActivity {

    private RecyclerView recyclerView;
    private CheckOutAdapter adapter;
    private CartViewModel cartViewModel;

    public static void start(Context context) {
        Intent intent = new Intent(context, CheckOut.class);
        context.startActivity(intent);

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        recyclerView=findViewById(R.id.checkoutList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);

        cartViewModel.getAllCart().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable final List<Cart> carts) {
                adapter=new CheckOutAdapter(carts,CheckOut.this);
                recyclerView.setAdapter(adapter);
            }
        });

    }
}
