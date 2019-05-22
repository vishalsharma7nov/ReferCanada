package com.allumez.refercanada;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    String BASE_URL="http://refercanada.com/api/";
    @GET("getStateList.php")
    Call<JsonHolder> getCities();
}