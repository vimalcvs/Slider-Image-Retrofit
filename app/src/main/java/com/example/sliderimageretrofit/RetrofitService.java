package com.example.sliderimageretrofit;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface RetrofitService {

    @Headers({"Accept: application/json"})
    @POST("slider.php")
    Call<List<Slider>> sliderApi(@Body Slider slider);

}


