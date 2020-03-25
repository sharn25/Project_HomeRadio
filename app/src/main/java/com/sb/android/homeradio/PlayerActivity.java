package com.sb.android.homeradio;

import android.app.Activity;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
import android.media.AudioManager;
import android.media.Image;
import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.IOException;

import static android.R.attr.src;

/**
 * Created by singhsh on 10/2/2019.
 */

public class PlayerActivity extends Activity {
    private String murl;
    private MediaPlayer mediaplayer;
    private Button mplay;
    private Image image;
    private ImageView pcover;
    private TextView ptitle;
    private TextView pdesc;
    private ImageView activity_bg;
    private ProgressBar pprogressbar;
    private int colora, colorb;

    @Override
    protected void onPause() {
        super.onPause();
        if(mediaplayer.isPlaying()){
            mediaplayer.reset();
            Log.d("PlayerActivity", "Pause : " + murl);
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(mediaplayer.isPlaying()){
            mediaplayer.reset();
            mediaplayer.release();
            Log.d("PlayerActivity", "Destory : " + murl);
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);



        mplay = (Button) findViewById(R.id.mplay);
        mplay.setVisibility(View.GONE);
        pprogressbar = (ProgressBar) findViewById(R.id.pprogressbar);
        pcover = (ImageView) findViewById(R.id.customcover);
        ptitle = (TextView) findViewById(R.id.ptitletv);
        pdesc = (TextView) findViewById(R.id.pdesctv);
        activity_bg = (ImageView) findViewById(R.id.activity_bg);
        colora= 0xFF014B85;
        colorb= 0xFF0D243B;

        AssetManager Amr = getAssets();
        Typeface facebold = Typeface.createFromAsset(Amr,"fonts/AEB.ttf");
        Typeface facelight = Typeface.createFromAsset(Amr,"fonts/AEL.ttf");
        ptitle.setTypeface(facebold);
        pdesc.setTypeface(facelight);
        getintent();

        GradientDrawable g = new GradientDrawable(
                GradientDrawable.Orientation.TL_BR,
                new int[] {colora,colorb});
        g.setGradientType(GradientDrawable.RADIAL_GRADIENT);
        g.setGradientRadius(600.0f);
        g.setGradientCenter(0.5f, 0.5f);
        activity_bg.setImageDrawable(g);
        Log.d("PlayerActivity", "Radio Url : " + murl);
        mediaplayer = new MediaPlayer();
        pcover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaplayer.isPlaying()) {
                    startplaying mstartplaying = new startplaying();
                    mstartplaying.execute();

                }else {
                    mediaplayer.stop();
                    mediaplayer.reset();

                }
            }
        });
        mplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!mediaplayer.isPlaying()) {
                    startplaying mstartplaying = new startplaying();
                    mstartplaying.execute();

                }else {
                    mediaplayer.stop();
                    mediaplayer.reset();
                    mplay.setText("Play");
                }
            }
        });

    }

    private void getintent(){
        Intent intent = getIntent();
        murl = intent.getStringExtra("murl");

        pcover.setImageResource(intent.getIntExtra("imgico",0));
        ptitle.setText(intent.getStringExtra("title"));
        pdesc.setText(intent.getStringExtra("desc"));
        creategraident(intent.getStringExtra("title"));

    }
    private void creategraident(String Title){
        switch(Title){
            case "Air1":
                colora= 0xFFCF387D;
                colorb= 0xFF793892;
            break;
            case "Heart UK":
                colora= 0xFFee1b3b;
                colorb= 0xFF980118;
                break;
            case "SomaFM: Folk Forward":
                colora= 0xFFB6D6C9;
                colorb= 0xFF58907a;
                break;
            case "Red FM":
                colora= 0xFFc71c22;
                colorb= 0xFF900409;
                break;
            case "Radio City":
                colora= 0xFF0169be;
                colorb= 0xFF054274;
                break;
            case "Radio Mirchi":
                colora= 0xFFffd531;
                colorb= 0xFF9c7c00;
                break;
            case "Big FM":
                colora= 0xFF5695CB;
                colorb= 0xFF375BA9;
                break;
            case "Dhol Radio":
                colora= 0xFFFFCD00;
                colorb= 0xFFB38F00;
                break;

        }
    }

    class startplaying extends AsyncTask<String, Void, Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mplay.setText("Stop");
            mplay.setEnabled(false);
            pcover.setEnabled(false);
            pprogressbar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(!mediaplayer.isPlaying()){
                mplay.setText("Play");

            }
            mplay.setEnabled(true);
            pcover.setEnabled(true);
            pprogressbar.setVisibility(View.GONE);
        }

        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            mediaplayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
                @Override
                public void onBufferingUpdate(MediaPlayer mediaPlayer, int i) {
                    Log.d("PlayerActivity","Total : " + mediaPlayer.getDuration() + "Buffered : " + i);
                }
            });
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean prepared;
            try {
                mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
                mediaplayer.setDataSource(murl);
                mediaplayer.prepare();
                mediaplayer.start();
                prepared=true;
            }catch (IOException e){
                Log.d("PlayerActivity", "Mediaplayer Error : " + e);
                prepared=false;
            }
            return prepared;
        }
    }
}
