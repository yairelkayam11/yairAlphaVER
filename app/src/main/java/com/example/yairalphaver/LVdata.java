package com.example.yairalphaver;

import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;

public class LVdata {
    private String lvd;

    public LVdata(){
    }

    public String getLvd() {
        return lvd;
    }

    public void setLvd(String lvd) {
        this.lvd = lvd;
    }

}
