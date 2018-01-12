package com.example.recyclerviewdemo.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.view.MotionEventCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewdemo.R;
import com.example.recyclerviewdemo.utils.ItemTouchHelperAdapter;
import com.example.recyclerviewdemo.utils.ItemTouchHelperViewHolder;
import com.example.recyclerviewdemo.utils.OnStartDragListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangyuhang@evergrande.cn on 2018-1-8.
 */

public class DragGridRecyclerViewAdapter extends
        RecyclerView.Adapter<DragGridRecyclerViewAdapter.NormalTextViewHolder> implements ItemTouchHelperAdapter {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mTitles;

    private final OnStartDragListener mDragStartListener;

    public DragGridRecyclerViewAdapter(Context context, OnStartDragListener dragStartListener) {
        mTitles = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.titles)));
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);

        mDragStartListener = dragStartListener;
    }

    @Override
    public DragGridRecyclerViewAdapter.NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DragGridRecyclerViewAdapter.NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(final DragGridRecyclerViewAdapter.NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles.get(position));

        holder.mTextView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
                    mDragStartListener.onStartDrag(holder);
                }
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(mTitles, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
    }

    @Override
    public void onItemDismiss(int position) {
        mTitles.remove(position);
        notifyItemRemoved(position);
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder implements
            ItemTouchHelperViewHolder {
        @BindView(R.id.text_view)
        TextView mTextView;

        public NormalTextViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("myLog", "onClick--> position = " + getPosition());
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener(){
                @Override
                public boolean onLongClick(View v) {
                    Log.d("myLog", "onLongClick--> position = " + getPosition());
                    return true;
                }
            });
        }

        @Override
        public void onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY);
        }

        @Override
        public void onItemClear() {
            itemView.setBackgroundColor(0);
        }
    }
}
