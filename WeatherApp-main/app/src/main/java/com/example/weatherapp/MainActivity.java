package com.example.weatherapp;

import android.os.Bundle;
import android.view.View;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.weatherapp.viewmodel.WeatherViewModel;

public class MainActivity extends AppCompatActivity {

    private WeatherViewModel weatherViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        weatherViewModel = new ViewModelProvider(this).get(WeatherViewModel.class);

        SearchView searchView = findViewById(R.id.searchView);
        TextView weatherInfo = findViewById(R.id.weatherInfo);
        TextView errorInfo = findViewById(R.id.errorInfo);
        LinearLayout weatherLayout = findViewById(R.id.weatherLayout); // Added LinearLayout


        weatherViewModel.getWeather().observe(this, weatherModel -> {
            if (weatherModel != null) {
                String mainWeather = weatherModel.getWeather()[0].getMain();
                String description = weatherModel.getWeather()[0].getDescription();
                double temp = weatherModel.getMain().getTemp();
                String country = weatherModel.getSys().getCountry(); // Fetch the country

                weatherInfo.setText(String.format("Country: %s\n\nMain: %s\n\nDescription: %s\n\nTemperature: %.2f°C",
                        country, mainWeather, description, temp));
                errorInfo.setText("");
                weatherLayout.setVisibility(View.VISIBLE); // Show layout when data is received
            }
        });

        weatherViewModel.getErrorMessage().observe(this, error -> {
            if (error != null) {
                errorInfo.setText(error);
                weatherInfo.setText("");
                weatherLayout.setVisibility(View.VISIBLE); // Show layout when an error occur
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                weatherViewModel.fetchWeather(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }
}