package com.example.recyclerviewdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.Button;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import it.gmariotti.recyclerview.adapter.AlphaAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.ScaleInAnimatorAdapter;
import it.gmariotti.recyclerview.adapter.SlideInBottomAnimatorAdapter;
import it.gmariotti.recyclerview.itemanimator.ScaleInOutItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideInOutLeftItemAnimator;
import it.gmariotti.recyclerview.itemanimator.SlideScaleInOutRightItemAnimator;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.addBtn)
    Button addBtn;
    @BindView(R.id.removeBtn)
    Button removeBtn;

    private RecyclerView.Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//这里用线性显示 类似于listview
//        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));//这里用线性宫格显示 类似于grid view
//        mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(2, OrientationHelper.VERTICAL));//这里用线性宫格显示 类似于瀑布流

//        mRecyclerView.setAdapter(new NormalRecyclerViewAdapter(this));
        mRecyclerView.setAdapter(mAdapter = new MultipleItemAdapter(this));

        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//动画效果，更多效果使用参考：https://github.com/gabrielemariotti/RecyclerViewItemAnimators
    }

    @OnClick({R.id.addBtn, R.id.removeBtn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.addBtn:
                ((MultipleItemAdapter)mAdapter).addData(0);
                break;
            case R.id.removeBtn:
                ((MultipleItemAdapter)mAdapter).removeData(0);
                break;
        }
    }
}
