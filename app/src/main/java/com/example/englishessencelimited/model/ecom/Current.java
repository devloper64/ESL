package com.example.englishessencelimited.model.ecom;

import com.google.gson.annotations.SerializedName;

public class Current {

    @SerializedName("text")
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
