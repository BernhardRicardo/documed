package com.android.documed.Class;

public class SpinnerItem {
    private String mName;
    private int mImage;

    public SpinnerItem(String name, int image){
        mName = name;
        mImage = image;
    }

    public String getName(){
        return mName;
    }

    public int getImage(){
        return mImage;
    }
}
