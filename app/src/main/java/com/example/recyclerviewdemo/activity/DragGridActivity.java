package com.example.recyclerviewdemo.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.adapter.DragListRecyclerViewAdapter;
import com.example.recyclerviewdemo.utils.ItemTouchHelperAdapter;
import com.example.recyclerviewdemo.utils.OnStartDragListener;
import com.example.recyclerviewdemo.utils.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DragGridActivity extends AppCompatActivity implements OnStartDragListener {
    @BindView(R.id.recycler_view_4)
    RecyclerView mRecyclerView;

    private RecyclerView.Adapter mAdapter;

    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draggrid);

        ButterKnife.bind(this);

        initView();
    }

    private void initView() {
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
        mRecyclerView.setAdapter(mAdapter = new DragListRecyclerViewAdapter(this));
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//动画效果，更多效果使用参考：https://github.com/gabrielemariotti/RecyclerViewItemAnimators

        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback((ItemTouchHelperAdapter) mAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);
    }
}
