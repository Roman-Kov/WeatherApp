package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherActivity extends AppCompatActivity {

    private TextView cityTemperatureView;
    private TextView dateTextView;
    private TextView cityNameView;
    private Button showYandexWeatherButton;
    private Button showWikiButton;
    private String cityName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        initViews();
        getValuesFromMainActivity();
        fillForm();
        getYandexWeather();
        getWikiArticle();
    }

    void initViews(){
        cityTemperatureView = findViewById(R.id.cityTemperature);
        dateTextView = findViewById(R.id.date);
        cityNameView = findViewById(R.id.cityName);
        showWikiButton = findViewById(R.id.showWikiButton);
        showYandexWeatherButton = findViewById(R.id.showYandexWeatherButton);
    }

    void getValuesFromMainActivity(){
        if (getIntent().getExtras() != null) {
            cityName = getIntent().getExtras().getString("CITY_NAME");
        }
        else cityName = "";
    }

    void fillForm(){
        cityNameView.setText(cityName);
        cityTemperatureView.setText(CityValues.getInstance(getBaseContext()).getTemperatureByCity(cityName));
        String today = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).format(new Date());
        dateTextView.setText(today);
    }

    void getYandexWeather(){
        showYandexWeatherButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = CityValues.getInstance(getBaseContext()).getYandexWeatherUrlByCity(cityName);
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }

    void getWikiArticle(){
        showWikiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = CityValues.getInstance(getBaseContext()).getWikiUrlByCity(cityName);
                Uri uri = Uri.parse(url);
                Intent browser = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(browser);
            }
        });
    }
}
