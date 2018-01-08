package com.example.recyclerviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.adapter.DragListRecyclerViewAdapter;
import com.example.recyclerviewdemo.utils.ItemTouchHelperAdapter;
import com.example.recyclerviewdemo.utils.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragListActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view_3)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draglist);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
        mRecyclerView.setAdapter(mAdapter = new DragListRecyclerViewAdapter(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//动画效果，更多效果使用参考：https://github.com/gabrielemariotti/RecyclerViewItemAnimators

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) mAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
    }
}
