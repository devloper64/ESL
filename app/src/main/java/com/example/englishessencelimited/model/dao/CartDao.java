package com.example.englishessencelimited.model.dao;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.englishessencelimited.model.entity.Cart;

import java.util.List;

@Dao
public interface CartDao {
    @Query("SELECT * from cart_table")
    LiveData<List<Cart>> getAlCart();

    @Query("SELECT * from cart_table where producId=:pId")
    LiveData<Cart> getCatBYProductID(String pId);

    @Query("delete  from cart_table where producId=:pId")
    void deleteBYProductID(String pId);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Cart word);

    @Query("DELETE FROM cart_table")
    void deleteAll();
}
