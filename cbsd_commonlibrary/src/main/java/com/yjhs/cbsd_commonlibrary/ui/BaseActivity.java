package com.yjhs.cbsd_commonlibrary.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.gyf.barlibrary.ImmersionBar;
import com.yjhs.cbsd_commonlibrary.R;

import butterknife.ButterKnife;
import butterknife.Unbinder;

public abstract class BaseActivity extends AppCompatActivity {

    private ProgressDialog progressDialog;
    private Unbinder unbinder;
    private ImmersionBar mImmersionBar;
    private InputMethodManager imm;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(getContentView());
        //绑定控件
        unbinder = ButterKnife.bind(this);
        //初始化沉浸式
        if (isImmersionBarEnabled())
            initImmersionBar();
        //初始化数据
        initData();
        //view与数据绑定
        initView();
        //设置监听
        setListener();


    }


    protected abstract int getContentView();


    public void showLoadingMsg(String message){
        if (progressDialog != null) {
            progressDialog.setMessage(message != null ? message : "处理中...");
        } else {
            progressDialog = new ProgressDialog(BaseActivity.this);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setIndeterminate(false);
            progressDialog.setCancelable(true);
            progressDialog.setMessage(message != null ? message : "处理中...");
        }
        progressDialog.show();
    }

    public void hideLoadingMsg(){
        if (progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbinder.unbind();
        this.imm = null;
        if (mImmersionBar != null)
            mImmersionBar.destroy();  //在BaseActivity里销毁
    }



    protected void initImmersionBar() {
        //在BaseActivity里初始化
        mImmersionBar = ImmersionBar.with(this);
        mImmersionBar.init();
    }

    protected void initData() {
    }

    protected void initView() {
    }

    protected void setListener() {
    }

    /**
     * 是否可以使用沉浸式
     * Is immersion bar enabled boolean.
     *
     * @return the boolean
     */
    protected boolean isImmersionBarEnabled() {
        return true;
    }

    public void finish() {
        super.finish();
        hideSoftKeyBoard();
    }

    public void hideSoftKeyBoard() {
        View localView = getCurrentFocus();
        if (this.imm == null) {
            this.imm = ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE));
        }
        if ((localView != null) && (this.imm != null)) {
            this.imm.hideSoftInputFromWindow(localView.getWindowToken(), 2);
        }
    }

}
