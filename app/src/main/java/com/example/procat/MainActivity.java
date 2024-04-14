package com.example.procat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;

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
            String description = positionsDB.getString(3);
            int time = positionsDB.getInt(4);
            adapterData.add(new PositionData(BitmapFactory.decodeResource(getResources(),  R.drawable.no_image_resorce), name, time, description));
        }

        //Инициализация адаптера
        adapter = new PositionsListAdapter(adapterData, this);

        //Заполнение списка услуг
        listPos.setAdapter(adapter);

        //Вызов  окна для добавления услуги
        addPos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showPositionDialog();
            }
        });

    }

    private void showPositionDialog() {

        //Инициализация диалога
        BottomSheetDialog dialog = new BottomSheetDialog(this);

        //Показ диалога
        dialog.show();

        //Назначение разметки для окна
        dialog.setContentView(R.layout.add_position_layout);

        //Инициализация эл. разметки
        Button addPos2 = dialog.findViewById(R.id.add_position_btn2);
        ImageView image = dialog.findViewById(R.id.select_position_image_iv);
        EditText namePos = dialog.findViewById(R.id.position_name_et);
        EditText timePos = dialog.findViewById(R.id.position_time_et);
        EditText descriptionPos = dialog.findViewById(R.id.position_description_et);

        //Назначение нужных шрифтов
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/KyivTypeSans.ttf");
        addPos2.setTypeface(font);
        namePos.setTypeface(font);
        timePos.setTypeface(font);
        descriptionPos.setTypeface(font);

        //Добавление услуги
        addPos2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = namePos.getText().toString();
                String time = timePos.getText().toString();
                String description = descriptionPos.getText().toString();

                if(name.length() == 0)
                    Toast.makeText(MainActivity.this, "Введите название услуги!", Toast.LENGTH_LONG).show();
                if(time.length() == 0)
                    Toast.makeText(MainActivity.this, "Введите время услуги!", Toast.LENGTH_LONG).show();

                if(name.length() > 0 && time.length() > 0){
                    adapter.addPosition(name, time, description);
                    dialog.dismiss();
                }


            }
        });


    }
}