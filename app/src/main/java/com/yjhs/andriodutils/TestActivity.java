package com.yjhs.andriodutils;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.yjhs.cbsd_commonlibrary.ui.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * author: Administrator
 * date: 2018/11/2 0002
 * desc:
 */
public class TestActivity extends BaseActivity {
    @BindView(R.id.btn)
    Button btn;
    @Override
    protected int getContentView() {
        return R.layout.activity_test;
    }

    @OnClick(R.id.btn)
    void click(View view) {
        switch (view.getId()) {
            case R.id.btn:
                showLoadingMsg("加载中...");
                break;
        }
    }
}
