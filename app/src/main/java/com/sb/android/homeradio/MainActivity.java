package com.sb.android.homeradio;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ListView;

import com.sb.android.homeradio.Adapter.SongAdapter;
import com.sb.android.homeradio.Model.SongModel;

import java.util.ArrayList;

public class MainActivity extends Activity {

    ArrayList<SongModel> songModels;
    ListView songlv;
    private static SongAdapter adapter;
    RecyclerView songrv;
    RecyclerView.LayoutManager mLayoutManager;
    RecyclerView.Adapter mAdapter;
    ArrayList<String> alName;
    ArrayList<Integer> alImage;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
      //songlv = (ListView) findViewById(R.id.songlv);
      songModels = new ArrayList<SongModel>();

      songModels.add(new SongModel("United States",R.drawable.air1_1, "Air1", "","http://emf.streamguys1.com/sa008_mp3_high_web"));
      songModels.add(new SongModel("United Kingdom",R.drawable.heartuk_1, "Heart UK", "","http://media-ice.musicradio.com/HeartUKMP3"));
      songModels.add(new SongModel("United States",R.drawable.somafmfolkforward_1, "SomaFM: Folk Forward", "","http://ice3.somafm.com/folkfwd-128-mp3"));
      songModels.add(new SongModel("India",R.drawable.redfm_1, "Red FM", "","http://14013.live.streamtheworld.com/CKYRFM_SC"));
      songModels.add(new SongModel("India",R.drawable.radiocity_1, "Radio City", "","http://prclive1.listenon.in:9302"));
      songModels.add(new SongModel("India",R.drawable.radiomirchi_1, "Radio Mirchi", "","http://peridot.streamguys.com:7150/Mirchi"));
      songModels.add(new SongModel("India",R.drawable.bigfm_1, "Big FM", "","http://sc-bb.1.fm:8017"));
      songModels.add(new SongModel("India",R.drawable.dhol_1, "Dhol Radio", "","http://gill.sukhpal.net:8000"));

      songrv = (RecyclerView) findViewById(R.id.songrv);
      songrv.setHasFixedSize(true);
      mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
      songrv.setLayoutManager(mLayoutManager);
      adapter= new SongAdapter(songModels,getApplicationContext());
      songrv.setAdapter(adapter);




  }
}