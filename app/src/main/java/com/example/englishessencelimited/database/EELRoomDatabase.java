package com.example.englishessencelimited.database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.englishessencelimited.model.dao.CartDao;
import com.example.englishessencelimited.model.entity.Cart;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Cart.class}, version = 1, exportSchema = false)
abstract class EELRoomDatabase extends RoomDatabase {

    abstract CartDao cartDao();
    // marking the instance as volatile to ensure atomic access to the variable
    private static volatile EELRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static EELRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (EELRoomDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            EELRoomDatabase.class, "word_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }




}
