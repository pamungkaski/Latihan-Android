package com.example.iampamungkas.cameranyoba;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.iampamungkas.cameranyoba.interfaces.SeleKopiAPI;
import com.example.iampamungkas.cameranyoba.models.ImageRequest;
import com.example.iampamungkas.cameranyoba.models.ImageResponse;

import java.io.File;
import java.net.Socket;
import java.net.URL;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.POST;

public class MainActivity extends Activity {
    Button button;
    ImageView imageView;
    static final int CAM_REQUEST =1;
    private SeleKopiAPI service;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button = (Button) findViewById(R.id.button);
        imageView =(ImageView) findViewById(R.id.image_view);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(getString(R.string.api_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(SeleKopiAPI.class);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent camera_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (camera_intent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(camera_intent, CAM_REQUEST);
                }
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAM_REQUEST && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            final Bitmap imageBitmap = (Bitmap) extras.get("data");
            //Sending Post Request to server
            ImageRequest imageRequest = new ImageRequest();
            imageRequest.setImage(imageBitmap);
            Call<ImageResponse> imageResponseCall = service.getImage(imageRequest);
            imageResponseCall.enqueue(new Callback<ImageResponse>() {
                @Override
                public void onResponse(Call<ImageResponse> call, Response<ImageResponse> response) {
                    int statusCode = response.code();
                    ImageResponse imageResponse = response.body();
                    byte[] decodedString = Base64.decode(imageResponse.getImage(), Base64.DEFAULT);
                    Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
                    imageView.setImageBitmap(decodedByte);
                    
                }

                @Override
                public void onFailure(Call<ImageResponse> call, Throwable t) {
                    imageView.setImageBitmap(imageBitmap);
                    Log.d("MainActivity", "onFailure:"+ t.getMessage());
                }
            });
        }
    }
}
