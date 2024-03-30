package com.example.procat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //Переменные для эл. разметки
    Button addPos;
    RecyclerView listPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Инициализация переменных для эл. разметки
        addPos = findViewById(R.id.add_position_btn);
        listPos = findViewById(R.id.positions_list);

        //Назначение шрифта
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/KyivTypeSans.ttf");
        addPos.setTypeface(font);
    }
}