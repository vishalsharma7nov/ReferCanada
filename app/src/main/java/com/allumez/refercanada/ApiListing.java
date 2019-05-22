package com.allumez.refercanada;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiListing {
    String BASE_URL="http://refercanada.com/api/";
    @GET("getStateList.php")
    Call<JsonHolderListing> getListing();

}