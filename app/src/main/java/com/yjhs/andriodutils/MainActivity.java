package com.yjhs.andriodutils;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapRegionDecoder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void go2DealImgActivity(View view) {
        startActivity(new Intent(MainActivity.this,DealImgActivity.class));
    }

    public void go2SoftKeybordActivity(View view) {
        startActivity(new Intent(MainActivity.this,SoftKeybordActivity.class));
    }
}
