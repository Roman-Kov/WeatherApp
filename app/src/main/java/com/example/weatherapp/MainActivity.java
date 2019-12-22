package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.alespero.expandablecardview.ExpandableCardView;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ExpandableCardView card;
    private RecyclerView recyclerView;
    private Button learnMoreBtn;
    private TextView whatCityTextView;
    private ArrayList<WeatherCardModel> imageModelArrayList;
    private WeatherAdapter adapter;
    private String[] cities;
    private String selectedCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initActionBarTextView();
        initWeatherRecyclerView();
        initExpandableCardView();
        showWeatherHistory();
        learnMore();
    }

    private ArrayList<WeatherCardModel> getWeather(){
        ArrayList<WeatherCardModel> list = new ArrayList<>();
        for (String city : cities) {
            WeatherCardModel weatherCardModel = new WeatherCardModel();
            weatherCardModel.setName(city);
            weatherCardModel.setImage_drawable(CityValues.getInstance(getBaseContext()).getEmblemByCity(city));
            weatherCardModel.setTemperature(CityValues.getInstance(getBaseContext()).getTemperatureByCity(city));
            list.add(weatherCardModel);
        }
        return list;
    }

    void initActionBarTextView(){
        String whatCity = getApplicationContext().getText(R.string.weather) +" " + selectedCity;
        whatCityTextView.setText(whatCity);
    }
    void initExpandableCardView(){
        TextView weatherHistoryCityName = findViewById(R.id.weatherHistoryCityName);
        TextView mondayWeather = findViewById(R.id.weather_monday);
        TextView tuesdayWeather = findViewById(R.id.weather_tuesday);
        TextView wednesdayWeather = findViewById(R.id.weather_wednesday);
        TextView thursdayWeather = findViewById(R.id.weather_thursday);
        TextView fridayWeather = findViewById(R.id.weather_friday);
        TextView saturdayWeather = findViewById(R.id.weather_saturday);
        TextView sundayWeather = findViewById(R.id.weather_sunday);

        weatherHistoryCityName.setText(selectedCity);
        int weatherHistoryId = CityValues.getInstance(getBaseContext()).getWeatherHistoryByCity(selectedCity);
        String[] weatherHistory = getResources().getStringArray(weatherHistoryId);
        mondayWeather.setText(weatherHistory[0]);
        tuesdayWeather.setText(weatherHistory[1]);
        wednesdayWeather.setText(weatherHistory[2]);
        thursdayWeather.setText(weatherHistory[3]);
        fridayWeather.setText(weatherHistory[4]);
        saturdayWeather.setText(weatherHistory[5]);
        sundayWeather.setText(weatherHistory[6]);
    }
    void initViews(){
        cities = getResources().getStringArray(R.array.cityList);
        selectedCity = cities[0];
        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        card = findViewById(R.id.main_profile_card);
        learnMoreBtn = findViewById(R.id.learnMoreBtn);
        whatCityTextView = findViewById(R.id.whatCityTextView);
    }

    void initWeatherRecyclerView(){
        imageModelArrayList = getWeather();
        adapter = new WeatherAdapter(this, imageModelArrayList, new WeatherAdapter.OnItemClickListener() {
            @Override public void onItemClick(WeatherCardModel item) {
               // Toast.makeText(getApplicationContext(), item.getName(), Toast.LENGTH_LONG).show();
                selectedCity = item.getName();
                initActionBarTextView();

                initExpandableCardView();
            }
        });

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.HORIZONTAL, false));
    }

    void showWeatherHistory(){
        card.setOnExpandedListener(new ExpandableCardView.OnExpandedListener() {
            @Override
            public void onExpandChanged(View v, boolean isExpanded) {
               // Toast.makeText(getApplicationContext(),R.string.week_weather_history, Toast.LENGTH_SHORT).show();
                //Оставил на всякий случай для расширения функционала
            }
        });
    }

    void learnMore(){
        learnMoreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(learnMoreBtn, getApplicationContext().getText(R.string.are_you_sure) +" " + selectedCity + "?", Snackbar.LENGTH_LONG)
                        .setAction("OK", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Intent intent = new Intent(MainActivity.this, WeatherActivity.class);
                                intent.putExtra("CITY_NAME", selectedCity);
                                startActivity(intent);
                            }
                        }).show();
            }
        });
    }
}
