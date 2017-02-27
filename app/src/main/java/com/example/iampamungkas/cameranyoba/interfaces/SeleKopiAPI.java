package com.example.iampamungkas.cameranyoba.interfaces;

import com.example.iampamungkas.cameranyoba.models.ImageRequest;
import com.example.iampamungkas.cameranyoba.models.ImageResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by iampamungkas on 2/26/17.
 */

public interface SeleKopiAPI {
    @POST("KopiSelection/")
    Call<ImageResponse> getImage(@Body ImageRequest imageRequest);
}
