package com.example.sliderimageretrofit;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public SliderView sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderView = findViewById(R.id.slider);

        apiSlider();
    }

    @Override
    protected void onResume() {
        super.onResume();
        apiSlider();
    }

    public void apiSlider() {
        Slider slider = new Slider();
        Call<List<Slider>> call = RetrofitClient.getInstance().getMyApi().sliderApi(slider);
        call.enqueue(new Callback<List<Slider>>() {
            @Override
            public void onResponse(@NonNull Call<List<Slider>> call, @NonNull Response<List<Slider>> response) {
                AdapterSlider adapterSliderBanner = new AdapterSlider(response);
                sliderView.setSliderAdapter(adapterSliderBanner);
                sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
                sliderView.setScrollTimeInMillis(2000);
                sliderView.startAutoCycle();
            }
            @Override
            public void onFailure(@NonNull Call<List<Slider>> call, @NonNull Throwable t) {

            }
        });
    }
}