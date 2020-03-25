package com.sb.android.homeradio.Model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by singhsh on 9/28/2019.
 */

public class SongModel {
    private String Img;
    private int Imgico;
    private String Title;
    private String Singer;
    private String Songurl;


    public SongModel(String img, int imgico, String title, String singer, String songurl){
        Img=img;
        Imgico=imgico;
        Title=title;
        Singer=singer;
        Songurl=songurl;
    }

    public int getImgico() {
        return Imgico;
    }

    public void setImgico(int imgico) {
        Imgico = imgico;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSinger() {
        return Singer;
    }

    public void setSinger(String singer) {
        Singer = singer;
    }

    public String getSongurl() {
        return Songurl;
    }

    public void setSongurl(String songurl) {
        Songurl = songurl;
    }


}
