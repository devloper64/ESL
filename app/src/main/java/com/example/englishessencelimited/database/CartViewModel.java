package com.example.englishessencelimited.database;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

public class CartViewModel extends AndroidViewModel {

    private CartRepository mRepository;
    private LiveData<List<Cart>> mAllCarts;
    private LiveData<Cart> cartByPId;

    public CartViewModel(Application application ) {
        super(application);
        mRepository = new CartRepository(application);
        mAllCarts = mRepository.getAllCart();

    }

    public LiveData<List<Cart>> getAllCart() {
        return mAllCarts;
    }

    public LiveData<Cart> getCartByPId(String id) {
        cartByPId=mRepository.getCartByProId(id);
        return cartByPId;
    }
    public  void deleteBypId(String pId){
        mRepository.deleteByPId(pId);
    }

    public void insert(Cart cart) {
        mRepository.insert(cart);
    }
}
