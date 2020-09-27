package com.example.englishessencelimited.model.entity;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "cart_table")
public class Cart {


    @PrimaryKey(autoGenerate = true)
    @NonNull
    @ColumnInfo(name = "id")
    private int id;
    @NonNull
    @ColumnInfo(name = "producId")
    private String producId;
    @NonNull
    @ColumnInfo(name = "tittle")
    private String tittle;
    @NonNull
    @ColumnInfo(name = "price")
    private String price;


    public Cart( @NonNull String producId, @NonNull String tittle, @NonNull String price) {

        this.producId = producId;
        this.tittle = tittle;
        this.price = price;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    @NonNull
    public String getProducId() {
        return producId;
    }

    public void setProducId(@NonNull String producId) {
        this.producId = producId;
    }

    @NonNull
    public String getTittle() {
        return tittle;
    }

    public void setTittle(@NonNull String tittle) {
        this.tittle = tittle;
    }

    @NonNull
    public String getPrice() {
        return price;
    }

    public void setPrice(@NonNull String price) {
        this.price = price;
    }
}
