package com.cysion.sample;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cysion.adjointlib.SimpleLocator;
import com.cysion.adjointlib.utils.ALog;
import com.cysion.sample.adapter.GlobalAdapter;
import com.cysion.sample.model.BaseData;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private GlobalAdapter mAdapter;
    private Rect mR;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.single().init(this);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 1, LinearLayoutManager.VERTICAL, false));
        List<BaseData> imgList = Provider.single().getImgs();
        List<BaseData> spanlist = Provider.single().getSpans();
        BaseData hori = Provider.single().getHori1();
        BaseData hori2 = Provider.single().getHori2();
        imgList.add(hori);
        imgList.addAll(spanlist);
        imgList.add(hori2);
        mAdapter = new GlobalAdapter(this, imgList);
        mR = new Rect();
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.getGlobalVisibleRect(mR);
                SimpleLocator.single().setParentFrame(mR);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }

    public void next(View view) {
        Intent myIntent = new Intent(MainActivity.this, SecondActivity.class);
        startActivity(myIntent);
    }
}
