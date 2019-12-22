package com.example.weatherapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import java.util.HashMap;
import java.util.Objects;

class CityValues {
    private final HashMap<String, HashMap<String, Object>> cityValuesMap = new HashMap<>();
    private final String TEMPERATURE = "TEMPERATURE", EMBLEM = "EMBLEM", WIKI_URL = "WIKI_URL",
            YANDEX_WEATHER_URL = "YANDEX_WEATHER_URL", WEATHER_HISTORY = "WEATHER_HISTORY";
    private static CityValues instance;

    private CityValues(Context context){
        String[] cities = context.getResources().getStringArray(R.array.cityList);
        String[] actualTemperatures = context.getResources().getStringArray(R.array.actualTemperatures);
        @SuppressLint("Recycle") TypedArray cityEmblems = context.getResources().obtainTypedArray(R.array.cityEmblems);
        String[] yandexWeatherUrls = context.getResources().getStringArray(R.array.yandexWeatherUrls);
        String[] wikiUrls = context.getResources().getStringArray(R.array.wikiUrls);
        @SuppressLint("Recycle") TypedArray weatherHistory = context.getResources().obtainTypedArray(R.array.weatherHistory);

        for (int i = 0; i < cities.length ; i++) {
            cityValuesMap.put(cities[i], new HashMap<String, Object>());
            Objects.requireNonNull(cityValuesMap.get(cities[i])).put(TEMPERATURE, actualTemperatures[i]);
            Objects.requireNonNull(cityValuesMap.get(cities[i])).put(EMBLEM, cityEmblems.getResourceId(i, -1));
            Objects.requireNonNull(cityValuesMap.get(cities[i])).put(YANDEX_WEATHER_URL, yandexWeatherUrls[i]);
            Objects.requireNonNull(cityValuesMap.get(cities[i])).put(WIKI_URL, wikiUrls[i]);
            Objects.requireNonNull(cityValuesMap.get(cities[i])).put(WEATHER_HISTORY, weatherHistory.getResourceId(i, -1));
        }
    }

    static CityValues getInstance(Context context){
        if(instance == null){
            instance = new CityValues(context);
        }
        return instance;
    }

    String getWikiUrlByCity(String city) {
        return (String) Objects.requireNonNull(cityValuesMap.get(city)).get(WIKI_URL);
    }

    String getYandexWeatherUrlByCity(String city) {
        return (String) Objects.requireNonNull(cityValuesMap.get(city)).get(YANDEX_WEATHER_URL);
    }

    String getTemperatureByCity(String city) {
        return (String) Objects.requireNonNull(cityValuesMap.get(city)).get(TEMPERATURE);
    }

    Integer getEmblemByCity(String city) {
        return (Integer)Objects.requireNonNull(cityValuesMap.get(city)).get(EMBLEM);
    }

    Integer getWeatherHistoryByCity(String city) {
        return  (Integer)Objects.requireNonNull(cityValuesMap.get(city)).get(WEATHER_HISTORY);
    }
}
