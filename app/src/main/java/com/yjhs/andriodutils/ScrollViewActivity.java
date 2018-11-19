package com.yjhs.andriodutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;

public class ScrollViewActivity extends AppCompatActivity {

    private TranslucentActionBar actionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view);
        TranslucentScrollView scrollView = (TranslucentScrollView) findViewById(R.id.tScrollView);
        ImageView img2 = (ImageView) findViewById(R.id.img2);
        actionBar = (TranslucentActionBar) findViewById(R.id.transactionbar);
        //初始actionBar
        actionBar.setData("测试", 0, null, 0, null, null);
        //开启渐变
        actionBar.setNeedTranslucent();
        //设置状态栏高度
        actionBar.setStatusBarHeight(getStatusBarHeight());
        scrollView.setTransView(actionBar);
        scrollView.setPullZoomView(img2);
        //设置ActionBar键渐变颜色
//        actionBar.setTransColor(getResources().getColor(R.color.colorPrimary));
        scrollView.setTranslucentChangedListener(new TranslucentScrollView.TranslucentChangedListener() {
            @Override
            public void onTranslucentChanged(int transAlpha) {
                actionBar.tvTitle.setVisibility(transAlpha > 48 ? View.VISIBLE : View.GONE);
            }
        });
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public int getStatusBarHeight() {
        //获取status_bar_height资源的ID
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            return getResources().getDimensionPixelSize(resourceId);
        }
        return 0;
    }
}
