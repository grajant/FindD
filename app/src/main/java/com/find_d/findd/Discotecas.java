package com.find_d.findd;


import android.graphics.Bitmap;

import java.io.Serializable;

public class Discotecas implements Serializable
{
    private String imageURL;
    private String name;
    private String dir;
    private String price;
    private String music;
    private String tel;
    private Bitmap image;
    private float rating;

    public void setImage(Bitmap image) {
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public Discotecas(String imageURL, String name, String dir, String price, String music, String tel, float rating) {
        this.imageURL = imageURL;
        this.name = name;
        this.dir = dir;
        this.price = price;
        this.music = music;
        this.tel = tel;
        this.rating = rating;
    }

    public Discotecas(String imageURL, String name, String dir, String tel, String music, String price)
    {
        this.name = name;
        this.imageURL = imageURL;
        this.music = music;
        this.dir = dir;
        this.price = price;
        this.tel = tel;

    }

    public String getImageURL() {
        return imageURL;
    }

    public String getName() {
        return name;
    }

    public String getDir() {
        return dir;
    }

    public String getPrice() {
        return price;
    }

    public String getMusic() {
        return music;
    }

    public String getTel() {
        return tel;
    }
}

