package com.example.procat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class PositionActivity extends AppCompatActivity {

    //Объяввление переменных для эл. разметки
    TextView nameTv, descriptionTv;
    Button giveBtn;
    ImageView imageIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position);

        //Инициализация переменных для эл. разметки
        nameTv = findViewById(R.id.position_name_tv);
        descriptionTv = findViewById(R.id.position_description_tv);
        giveBtn = findViewById(R.id.give_position_btn);
        imageIv = findViewById(R.id.position_image_iv);

        //Получение нужной информации
        Bitmap image = getIntent().getParcelableExtra("img");
        String name = getIntent().getStringExtra("name");
        String description = getIntent().getStringExtra("description");

        //Назначение информации
        imageIv.setImageBitmap(image);
        nameTv.setText(name);
        descriptionTv.setText(description);

        //Назначение шрифта
        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/KyivTypeSans.ttf");
        nameTv.setTypeface(font);
        descriptionTv.setTypeface(font);
        giveBtn.setTypeface(font);

    }
}