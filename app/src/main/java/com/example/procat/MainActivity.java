package com.example.procat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //База данных
    SQLiteDatabase db;

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

        //Инициализация бд
        db = getBaseContext().openOrCreateDatabase("positions.db", MODE_PRIVATE, null);

        //Создание таблицы с позициями, если она не существует
        db.execSQL("CREATE TABLE IF NOT EXISTS Positions(_id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, image BLOB, description TEXT, time INTEGER NOT NULL)");

        //Инициализация переменных для эл. разметки
        addPos = findViewById(R.id.add_position_btn);
        listPos = findViewById(R.id.positions_list);

        //Назначение шрифта
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/KyivTypeSans.ttf");
        addPos.setTypeface(font);

        //Заполнение списка с информацией для адаптера
        Cursor positionsDB = db.rawQuery("SELECT * FROM Positions", null);
        while (positionsDB.moveToNext()){
            String name = positionsDB.getString(1);
            int time = positionsDB.getInt(4);
            adapterData.add(new PositionData(R.mipmap.ic_launcher, name, time));
        }

        //Инициализация адаптера
        adapter = new PositionsListAdapter(adapterData, this);

        //Заполнение списка услуг
        listPos.setAdapter(adapter);
    }
}