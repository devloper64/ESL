package com.example.englishessencelimited.model.ecom;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Price {

    @SerializedName("current")
    private Current currents;

    public Current getCurrents() {
        return currents;
    }

    public void setCurrents(Current currents) {
        this.currents = currents;
    }
}
