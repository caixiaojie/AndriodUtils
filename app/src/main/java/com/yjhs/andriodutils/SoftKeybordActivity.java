package com.yjhs.andriodutils;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

import com.yjhs.cbsd_commonlibrary.utils.InputMethodUtils;


public class SoftKeybordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_soft_keybord);
        EditText editText = findViewById(R.id.et);
        InputMethodUtils.openSoftKeyboard(SoftKeybordActivity.this,editText);
    }
}
