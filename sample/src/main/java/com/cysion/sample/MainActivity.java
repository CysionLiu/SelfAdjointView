package com.cysion.sample;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.cysion.adjointlib.utils.ALog;
import com.cysion.sample.adapter.GlobalAdapter;

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
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mAdapter = new GlobalAdapter(this, Provider.single().getImgs());
        mR = new Rect();
        mRecyclerView.post(new Runnable() {
            @Override
            public void run() {
                mRecyclerView.getGlobalVisibleRect(mR);
//                int[] loc = new int[2];
//                mRecyclerView.getLocationOnScreen(loc);
//                mR.set(loc[0], loc[1], loc[0] + mRecyclerView.getWidth(), loc[1] + mRecyclerView.getHeight());
                mAdapter.setParentLocation(mR);
            }
        });

        mRecyclerView.setAdapter(mAdapter);
    }
}
