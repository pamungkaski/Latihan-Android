package com.example.iampamungkas.cameranyoba.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by iampamungkas on 2/26/17.
 */

public class ImageResponse {
    @SerializedName("success")
    @Expose
    private String success;
    @SerializedName("image")
    @Expose
    private String image_wrap;

    public String getImage() {
        return image_wrap;
    }

    public String getSuccess(){
        return success;
    }
}
