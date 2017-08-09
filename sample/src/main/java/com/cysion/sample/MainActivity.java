package com.cysion.sample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cysion.adjointlib.utils.ALog;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.single().init(this);
        setContentView(R.layout.activity_main);
        ALog.single().toast("hello world");
    }
}
