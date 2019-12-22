package com.example.weatherapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<WeatherCardModel> imageModelArrayList;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(WeatherCardModel item);
    }

    WeatherAdapter(Context context, ArrayList<WeatherCardModel> imageModelArrayList, OnItemClickListener listener){

        inflater = LayoutInflater.from(context);
        this.imageModelArrayList = imageModelArrayList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.recycler_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeatherAdapter.MyViewHolder holder, int position) {

        String today = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        holder.iv.setImageResource(imageModelArrayList.get(position).getImage_drawable());
        holder.name.setText(imageModelArrayList.get(position).getName());
        holder.temperature.setText(imageModelArrayList.get(position).getTemperature());
        holder.date.setText(today);
        holder.bind(imageModelArrayList.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return imageModelArrayList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView temperature;
        TextView date;
        ImageView iv;

        MyViewHolder(View itemView) {
            super(itemView);
            name = (TextView) itemView.findViewById(R.id.cityName);
            temperature = (TextView) itemView.findViewById(R.id.cityTemperature);
            date = (TextView) itemView.findViewById(R.id.date);
            iv = (ImageView) itemView.findViewById(R.id.cityEmblem);
        }

        void bind(final WeatherCardModel item, final OnItemClickListener listener) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    listener.onItemClick(item);
                }
            });
        }
    }
}