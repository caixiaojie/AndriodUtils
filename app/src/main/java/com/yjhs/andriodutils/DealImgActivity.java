package com.yjhs.andriodutils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.yjhs.cbsd_commonlibrary.widget.img.FastBlur;


public class DealImgActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deal_img);
        ImageView img1 = findViewById(R.id.img1);
        Bitmap originBitmap = BitmapFactory.decodeResource(getResources(), R.mipmap._1);


        int scaleRatio = 10;
        int blurRadius = 8;
        Bitmap scaledBitmap = Bitmap.createScaledBitmap(originBitmap,
                originBitmap.getWidth() / scaleRatio,
                originBitmap.getHeight() / scaleRatio,
                false);
        Bitmap blurBitmap = FastBlur.doBlur(scaledBitmap, blurRadius, true);
        img1.setScaleType(ImageView.ScaleType.CENTER_CROP);
        img1.setImageBitmap(blurBitmap);

    }


}
