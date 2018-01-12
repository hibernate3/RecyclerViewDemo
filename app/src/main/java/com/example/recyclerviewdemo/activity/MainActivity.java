package com.example.recyclerviewdemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.adapter.MultipleItemAdapter;
import com.example.recyclerviewdemo.adapter.NormalRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_1)
    RecyclerView mRecyclerView;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.removeBtn)
    Button removeBtn;
    @BindView(R.id.swipe_layout)
    SwipeRefreshLayout swipeLayout;

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

//        startActivity(new Intent(MainActivity.this, DragListActivity.class));
        startActivity(new Intent(MainActivity.this, DragGridActivity.class));

        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));// 这里用线性宫格显示 类似于瀑布流

//        mRecyclerView.setAdapter(mAdapter = new NormalRecyclerViewAdapter(this));
        mRecyclerView.setAdapter(mAdapter = new MultipleItemAdapter(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//动画效果，更多效果使用参考：https://github.com/gabrielemariotti/RecyclerViewItemAnimators

        swipeLayout.setDistanceToTriggerSync(666);//设定手指下滑距离触发刷新
        swipeLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mAdapter.notifyDataSetChanged();
                swipeLayout.setRefreshing(false);
            }
        });
    }

    @OnClick({R.id.addBtn, R.id.removeBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                ((MultipleItemAdapter) mAdapter).addData(0);
                break;
            case R.id.removeBtn:
                ((MultipleItemAdapter) mAdapter).removeData(0);
                break;
        }
    }
}
