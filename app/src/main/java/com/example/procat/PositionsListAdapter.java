package com.example.procat;

import android.content.Context;
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
        ImageView iv;
        TextView name, time;
        View v;

        public ViewHolder(View v){
            super(v);
            this.iv = v.findViewById(R.id.position_list_item_iv);
            this.name = v.findViewById(R.id.position_list_item_name_tv);
            this.time = v.findViewById(R.id.position_list_item_time_tv);
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
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
