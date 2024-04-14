package com.example.procat;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.icu.text.Transliterator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class PositionsListAdapter extends RecyclerView.Adapter<PositionsListAdapter.ViewHolder> {

    //Список с информацией
    List<PositionData> data;

    //Инфлейтер
    LayoutInflater inflater;

    //Контекст
    Context c;

    public PositionsListAdapter(List<PositionData> data, Context c){
        this.data = data;
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView iv, del;
        TextView name, time;
        View v;

        public ViewHolder(View v){
            super(v);
            this.iv = v.findViewById(R.id.position_list_item_iv);
            this.name = v.findViewById(R.id.position_list_item_name_tv);
            this.time = v.findViewById(R.id.position_list_item_time_tv);
            this.del = v.findViewById(R.id.position_list_item_del_btn);
            this.v = v;
        }
    }

    @NonNull
    @Override
    public PositionsListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = inflater.inflate(R.layout.positions_list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PositionsListAdapter.ViewHolder holder, int position) {
        //Получение конкретной информации
        PositionData obj = data.get(position);

        //Назначение информации
        holder.iv.setImageBitmap(obj.image);
        holder.name.setText(obj.text);
        if (obj.time % 10 == 1)
            holder.time.setText(obj.time+" минута");
        else if (obj.time % 10 > 1 && obj.time % 10 < 5)
            holder.time.setText(obj.time+" минуты");
        else
            holder.time.setText(obj.time+" минут");

        //Назначение шрифта
        Typeface font = Typeface.createFromAsset(c.getAssets(), "fonts/KyivTypeSans.ttf");
        holder.name.setTypeface(font);
        holder.time.setTypeface(font);

        //Удаление записи
        holder.del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePosition(position);
            }
        });

        //Переход на страницу с услугой
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toPos = new Intent(c, PositionActivity.class);
                toPos.putExtra("img", obj.image);
                toPos.putExtra("name", obj.text);
                toPos.putExtra("description", obj.description);
                c.startActivity(toPos);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    //Удаление записи
    public void deletePosition(int i){
        //Из списка
        data.remove(i);
        notifyItemRemoved(i);

        //Из бд
        SQLiteDatabase db = c.openOrCreateDatabase("positions.db",  Context.MODE_PRIVATE, null);
        Cursor cursor = db.rawQuery("SELECT * FROM Positions", null);
        int count = 0;
        while(cursor.moveToNext()){
            if(count==i){
                int id = cursor.getInt(0);
                db.execSQL("DELETE FROM Positions WHERE _id="+id);
                break;
            }
            count++;
        }
    }

    //Добавление записи
    public void addPosition(String  name, String time, String description){
        //В бд
        SQLiteDatabase db = c.openOrCreateDatabase("positions.db", Context.MODE_PRIVATE, null);
        db.execSQL("INSERT INTO Positions (name, time, description) VALUES ('" + name + "'," + Integer.parseInt(time)+ ", '" + description + "')");

        //В список
        data.add(new PositionData(BitmapFactory.decodeResource(c.getResources(), R.drawable.no_image_resorce),  name,  Integer.parseInt(time), description));
        notifyItemInserted(data.size()-1);
    }
}
