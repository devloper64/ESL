package com.example.englishessencelimited.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.englishessencelimited.database.EELRoomDatabase;
import com.example.englishessencelimited.model.dao.CartDao;
import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

public class CartRepository {


    private CartDao cartDao;
    private LiveData<List<Cart>> allCart;
    private LiveData<Cart> cartByProId;

    CartRepository(Application application) {
        EELRoomDatabase db = EELRoomDatabase.getDatabase(application);
        cartDao = db.cartDao();
        allCart = cartDao.getAlCart();
    }

    void deleteByPId(String pId){
        EELRoomDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.deleteBYProductID(pId);
        });
    }

    LiveData<List<Cart>> getAllCart() {
        return allCart;
    }

    LiveData<Cart> getCartByProId(String proId){
        cartByProId=cartDao.getCatBYProductID(proId);
        return cartByProId;
    }

    void insert(final Cart cart) {
        EELRoomDatabase.databaseWriteExecutor.execute(() -> {
            cartDao.insert(cart);
        });
    }
}
