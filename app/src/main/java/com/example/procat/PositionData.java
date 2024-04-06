package com.example.procat;

import android.graphics.Bitmap;

public class PositionData {
    int time;
    Bitmap  image;
    String text;

    public PositionData(Bitmap image, String text, int time){
        this.image = image;
        this.text = text;
        this.time = time;
    }
}
