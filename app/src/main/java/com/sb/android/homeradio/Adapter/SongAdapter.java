package com.sb.android.homeradio.Adapter;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.DrawableUtils;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;

import android.widget.ImageView;
import android.widget.TextView;

import com.sb.android.homeradio.MainActivity;
import com.sb.android.homeradio.Model.SongModel;
import com.sb.android.homeradio.PlayerActivity;
import com.sb.android.homeradio.R;

import java.util.ArrayList;

/**
 * Created by singhsh on 9/28/2019.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.ViewHolder>{

    private ArrayList<SongModel> songModel;
    private Context mContext;

    public SongAdapter( ArrayList<SongModel> songmodel, Context context){
        songModel = songmodel;
        mContext = context;
    }

    @Override
    public SongAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        SongAdapter.ViewHolder viewHolder = new SongAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SongAdapter.ViewHolder holder, int position) {
        holder.bindRestaurant(songModel.get(position));
    }

    @Override
    public int getItemCount() {
        return songModel.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
         ImageView img;
        TextView titletv;
        TextView singertv;
        private Context mContext;

        public ViewHolder(View itemView) {
            super(itemView);
            mContext = itemView.getContext();
            AssetManager Amr = mContext.getAssets();
            Typeface facebold = Typeface.createFromAsset(Amr,"fonts/AEB.ttf");
            titletv = (TextView) itemView.findViewById(R.id.titletv);
            singertv = (TextView) itemView.findViewById(R.id.singertv);
            titletv.setTypeface(facebold);
            titletv.setTypeface(facebold);
            img = (ImageView) itemView.findViewById(R.id.coveriv);

        }

        public void bindRestaurant(final SongModel songmodel) {
            titletv.setText(songmodel.getTitle());
            singertv.setText(songmodel.getSinger());
            img.setImageResource(songmodel.getImgico());
            Log.d("SongAdapter","added : " + songmodel.getTitle());

            img.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    switch (event.getAction()) {
                        case MotionEvent.ACTION_DOWN: {
                           /* ImageView view = (ImageView ) v;
                            view.getBackground().setColorFilter(0x77ffffff, PorterDuff.Mode.SRC_ATOP);
                            v.invalidate();
*/
                            break;
                        }
                        case MotionEvent.ACTION_UP:
                            Log.d("songAdapter","ACTION_UP");
                            Intent intent = new Intent(mContext , PlayerActivity.class);
                            intent.putExtra("murl",songmodel.getSongurl());
                            intent.putExtra("imgico",songmodel.getImgico());
                            intent.putExtra("title",songmodel.getTitle());
                            intent.putExtra("desc",songmodel.getImg());
                            mContext.startActivity(intent);

                            // Your action here on button click

                        case MotionEvent.ACTION_CANCEL: {
                           /* ImageView view = (ImageView) v;
                            view.getBackground().clearColorFilter();
                            view.invalidate();
*/
                            break;
                        }
                    }
                    return true;
                }
            });

        }

    }
}
