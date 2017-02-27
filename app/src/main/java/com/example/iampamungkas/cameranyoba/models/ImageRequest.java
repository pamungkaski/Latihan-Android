package com.example.iampamungkas.cameranyoba.models;

import android.graphics.Bitmap;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.File;

/**
 * Created by iampamungkas on 2/26/17.
 */

public class ImageRequest {
    private Bitmap image;

    public ImageRequest(){}

    public Bitmap getImage() {
        return image;
    }
    public void setImage(Bitmap image){
        this.image= image;
    }
}
