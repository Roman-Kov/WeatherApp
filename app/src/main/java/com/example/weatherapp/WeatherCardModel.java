package com.example.weatherapp;

class WeatherCardModel {
    private String name;
    private int image_drawable;
    private String temperatura;

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getTemperature() {
        return temperatura;
    }

    void setTemperature(String temperatura) {
        this.temperatura = temperatura;
    }

    int getImage_drawable() {
        return image_drawable;
    }

    void setImage_drawable(int image_drawable) {
        this.image_drawable = image_drawable;
    }
}
