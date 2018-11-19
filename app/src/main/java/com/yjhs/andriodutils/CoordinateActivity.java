package com.yjhs.andriodutils;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.tbruyelle.rxpermissions2.RxPermissions;
import com.yjhs.cbsd_commonlibrary.imageloader.ImageLoaderV4;

public class CoordinateActivity extends AppCompatActivity {

    private ImageView img1;
    private ImageView img2;
    private ImageView img3;
    private ImageView img4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coordinate);
        img1 = (ImageView) findViewById(R.id.img1);
        img2 = (ImageView) findViewById(R.id.img2);
        img3 = (ImageView) findViewById(R.id.img3);
        img4 = (ImageView) findViewById(R.id.img4);

        //圆形图片
        ImageLoaderV4.getInstance().displayCircleImage(this,"http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg",img1,R.mipmap.ic_launcher_round);
        ImageLoaderV4.getInstance().displayImage(this,"http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg",img4,R.mipmap.ic_launcher_round);
        //圆角图片
        ImageLoaderV4.getInstance().displayRoundImage(this,"http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg",img2,R.mipmap.ic_launcher_round,20);
        //模糊图片
        ImageLoaderV4.getInstance().displayBlurImage(this,"http://imgsrc.baidu.com/imgad/pic/item/267f9e2f07082838b5168c32b299a9014c08f1f9.jpg",img3,R.mipmap.ic_launcher_round,20);

        //本地图片
//        ImageLoaderV4.getInstance().displayImageInResource(this,R.mipmap.test,mImageView_4);
//        ImageLoaderV4.getInstance().displayImageInResource(this,R.mipmap.test,mImageView_5,new BlurBitmapTransformation(this,40));
//        ImageLoaderV4.getInstance().displayImageInResource(this,R.mipmap.test,mImageView_6,new CircleBitmapTransformation(this));
//        ImageLoaderV4.getInstance().displayImageInResource(this,R.mipmap.test,mImageView_6,new RoundBitmapTransformation(this,40));

        final RxPermissions rxPermissions = new RxPermissions(this);
        rxPermissions
                .request(Manifest.permission.CAMERA)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        // I can control the camera now
                    } else {
                        // Oups permission denied
                    }
                });
    }
}
