package com.example.englishessencelimited.activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import com.example.englishessencelimited.R;
import com.example.englishessencelimited.adapter.ProductAdapter;
import com.example.englishessencelimited.api.APIClient;
import com.example.englishessencelimited.database.CartViewModel;
import com.example.englishessencelimited.model.ecom.ProductResponse;
import com.example.englishessencelimited.model.entity.Cart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private RecyclerView recyclerView;
    private ProductAdapter productAdapter;
    private ProgressBar progressBar;
    private CartViewModel cartViewModel;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            switch (item.getItemId()) {
                case R.id.checkOut:
                    CheckOut.start(MainActivity.this);
                    return true;
                case R.id.createpost:
                    PostActivity.start(MainActivity.this);
                    return true;
                case  R.id.viewPost:
                    ViewPost.start(MainActivity.this);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.productList);
        progressBar = findViewById(R.id.progressBar1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        cartViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        bottomNavigationView = findViewById(R.id.navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        cartViewModel.getAllCart().observe(this, new Observer<List<Cart>>() {
            @Override
            public void onChanged(@Nullable final List<Cart> carts) {
                Log.d("ssss","abc:"+carts.size());
            }
        });


        APIClient.getClient().getProduct().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                productAdapter = new ProductAdapter(response.body().getProductList(), MainActivity.this,cartViewModel,MainActivity.this);
                Log.d("aaaaa", "aaaa");
                recyclerView.setAdapter(productAdapter);
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {

            }
        });
    }
}
