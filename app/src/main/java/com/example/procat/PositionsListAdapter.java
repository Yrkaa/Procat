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
        TextView tv;
        View v;

        public ViewHolder(View v){
            super(v);
            iv = v.findViewById(R.id.position_list_item_iv);
            tv = v.findViewById(R.id.position_list_item_tv);
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
        holder.iv.setImageResource(obj.image);
        holder.tv.setText(obj.text);

        //Назначение шрифта
        Typeface font = Typeface.createFromAsset(c.getAssets(), "fonts/KyivTypeSans.ttf");
        holder.tv.setTypeface(font);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
