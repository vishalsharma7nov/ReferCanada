package com.allumez.refercanada;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiCities {
    String BASE_URL="http://refercanada.com/api/";
    @GET("getCityList.php?stateId=1")
    Call<JsonHolderCities> getCities();

}