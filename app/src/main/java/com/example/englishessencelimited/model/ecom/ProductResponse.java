package com.example.englishessencelimited.model.ecom;

import com.example.englishessencelimited.model.ecom.Product;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ProductResponse {

    @SerializedName("categoryName")
    private String categoryName;


    @SerializedName("products")
   private List<Product> productList;

    public List<Product> getProductList() {
        return productList;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }
}
