package com.example.englishessencelimited.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.englishessencelimited.R;
import com.example.englishessencelimited.api.APIClient;
import com.example.englishessencelimited.model.ecom.ProductDetail;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailProduct extends AppCompatActivity {

    private TextView des,gender,price,name;
    private ProgressBar progressBar;
    private ImageView pic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_product);
        des=findViewById(R.id.des);
        progressBar=findViewById(R.id.progressBar2);
        pic=findViewById(R.id.pic);
        gender=findViewById(R.id.gender);
        price=findViewById(R.id.price);
        name=findViewById(R.id.name);
        Intent intent = getIntent();
        String productId = intent.getStringExtra("productId");
        String img = intent.getStringExtra("picture");
        String priceText = intent.getStringExtra("price");
        price.setText("Price:"+priceText);
        Glide.with(this)
                .load("http://"+img)
                .centerCrop()
                .placeholder(R.drawable.placeholder)
                .into(pic);

        APIClient.getClient().getDetails(productId).enqueue(new Callback<ProductDetail>() {
            @Override
            public void onResponse(Call<ProductDetail> call, Response<ProductDetail> response) {
                des.setText(Html.fromHtml("Description:"+response.body().getDescription()));
                gender.setText("Gender:"+response.body().getGender());
                name.setText(response.body().getName());
                progressBar.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(Call<ProductDetail> call, Throwable t) {

            }
        });

    }
}
