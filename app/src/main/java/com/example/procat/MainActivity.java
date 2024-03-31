package com.example.procat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Адаптер
    PositionsListAdapter adapter;

    //Список с информацией для адаптера
    ArrayList<PositionData> adapterData = new ArrayList<>();

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

        //Заполнение списка с информацией для адаптера
        adapterData.add(new PositionData(R.mipmap.ic_launcher, "Услуга 1", 5));

        //Инициализация адаптера
        adapter = new PositionsListAdapter(adapterData, this);

        //Заполнение списка услуг
        listPos.setAdapter(adapter);
    }
}