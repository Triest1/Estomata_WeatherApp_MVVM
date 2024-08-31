package com.example.weatherapp.model;


public class WeatherModel {
    private Main main;
    private Weather[] weather;
    private Sys sys; // Add this line to include the 'sys' field

    public Main getMain() {
        return main;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public Sys getSys() {  // Add getter for sys
        return sys;
    }

    public static class Main {
        private double temp;

        public double getTemp() {
            return temp;
        }
    }

    public static class Weather {
        private int id;
        private String main;
        private String description;
        private String icon;

        public int getId() {
            return id;
        }

        public String getMain() {
            return main;
        }

        public String getDescription() {
            return description;
        }

        public String getIcon() {
            return icon;
        }
    }
    public static class Sys {  // Add Sys class
        private String country;

        public String getCountry() {
            return country;
        }
    }
}