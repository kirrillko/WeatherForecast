package com.example.weatherforecast;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

// этот класс выводит данные в список
public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {

    private Context context;
    private List<WeatherItem> weatherItemList;

    public WeatherAdapter(Context context, List<WeatherItem> weatherItemList) {
        this.context = context;
        this.weatherItemList = weatherItemList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.weather_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        WeatherItem weatherItem = weatherItemList.get(position);
        String formattedDateTime = DateUtils.formatDateTimeString(weatherItem.getDateTime());
        holder.dateTimeTextView.setText(formattedDateTime);
        holder.temperatureTextView.setText("Температура: " + weatherItem.getTemperature() + "°C");
        holder.pressureTextView.setText("Давление: " + weatherItem.getPressure() + " гПа");
        holder.humidityTextView.setText("Влажность: " + weatherItem.getHumidity() + "%");
        holder.windSpeedTextView.setText("Скорость ветра: " + weatherItem.getWindSpeed() + " м/с");
        Picasso.get().load(weatherItem.getWeatherIconUrl()).into(holder.weatherIconImageView);
    }

    @Override
    public int getItemCount() {
        return weatherItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView dateTimeTextView, temperatureTextView, pressureTextView, humidityTextView, windSpeedTextView;
        ImageView weatherIconImageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateTimeTextView = itemView.findViewById(R.id.dateTimeTextView);
            temperatureTextView = itemView.findViewById(R.id.temperatureTextView);
            pressureTextView = itemView.findViewById(R.id.pressureTextView);
            humidityTextView = itemView.findViewById(R.id.humidityTextView);
            windSpeedTextView = itemView.findViewById(R.id.windSpeedTextView);
            weatherIconImageView = itemView.findViewById(R.id.weatherIconImageView);
        }
    }
}