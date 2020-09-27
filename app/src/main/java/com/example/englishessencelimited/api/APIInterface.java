package com.example.englishessencelimited.api;

import com.example.englishessencelimited.model.ecom.ProductDetail;
import com.example.englishessencelimited.model.ecom.ProductResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface APIInterface {

    @GET(HttpParam.GET_PRODUCT)
    @Headers({"x-rapidapi-host:asos2.p.rapidapi.com","x-rapidapi-key:3c8a571e04mshbbc8ba9218d9d6cp19cf82jsne23fa266fdfc"})
    Call<ProductResponse> getProduct();

    @GET(HttpParam.PRODUCT_DETAILS)
    @Headers({"x-rapidapi-host:asos2.p.rapidapi.com","x-rapidapi-key:3c8a571e04mshbbc8ba9218d9d6cp19cf82jsne23fa266fdfc"})
    Call<ProductDetail> getDetails(@Query("id")String id);
}
