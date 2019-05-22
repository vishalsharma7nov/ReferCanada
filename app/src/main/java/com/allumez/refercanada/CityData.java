package com.allumez.refercanada;

public class CityData {

    // Store the id of the  movie poster
    private int mImageDrawable;
    // Store the name of the movie
    private String mCityName;
    // Store the release date of the movie
    private String mCityImage;

    // Constructor that is used to create an instance of the Movie object
    public CityData(int mImageDrawable, String mCityName, String mCityImage) {
        this.mImageDrawable = mImageDrawable;
        this.mCityName = mCityName;
        this.mCityImage = mCityImage;
    }

    public int getmImageDrawable() {
        return mImageDrawable;
    }

    public void setmImageDrawable(int mImageDrawable) {
        this.mImageDrawable = mImageDrawable;
    }

    public String getmCityName() {
        return mCityName;
    }

    public void setmCityName(String mCityName) {
        this.mCityName = mCityName;
    }

    public String getmCityImage() {
        return mCityImage;
    }

    public void setmCityImage(String mCityImage) {
        this.mCityImage = mCityImage;
    }
}