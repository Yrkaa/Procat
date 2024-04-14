package com.example.procat;

import android.graphics.Bitmap;

public class PositionData {
    int time;
    Bitmap  image;
    String text, description;

    public PositionData(Bitmap image, String text, int time, String description){
        this.image = image;
        this.text = text;
        this.time = time;
        this.description = description;
    }
}
