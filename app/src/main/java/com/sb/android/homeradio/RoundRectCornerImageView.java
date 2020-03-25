package com.sb.android.homeradio;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by singhsh on 10/4/2019.
 */

public class RoundRectCornerImageView extends ImageView {

    private float radius = 18.0f;
    private Path path;
    private RectF rect;

    public RoundRectCornerImageView(Context context) {
        super(context);
        init();
    }

    public RoundRectCornerImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RoundRectCornerImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        path = new Path();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        rect = new RectF(0, 0, this.getWidth(), this.getHeight());
        path.addRoundRect(rect, radius, radius, Path.Direction.CW);
        canvas.clipPath(path);
        //

        Drawable d = getDrawable();
        if (d != null) {
            Log.d("RoundRect","Drawable Found");
            Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
            Paint mShadow = new Paint();
// radius=10, y-offset=2, color=black
            mShadow.setShadowLayer(50.0f, 0.0f, 1.0f, 0xFF000000);
// in onDraw(Canvas)
            canvas.drawBitmap(bitmap, 0.0f, 0.0f, mShadow);
        }else {
            Log.d("RoundRect","Drawable Not Found");
            super.onDraw(canvas);
        }
    }
}