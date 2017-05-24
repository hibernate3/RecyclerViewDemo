package com.example.recyclerviewdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.recyclerviewdemo.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangyuhang on 2017/1/4.
 */

public class NormalRecyclerViewAdapter extends
        RecyclerView.Adapter<NormalRecyclerViewAdapter.NormalTextViewHolder> {

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private String[] mTitles;

    public NormalRecyclerViewAdapter(Context context) {
        mTitles = context.getResources().getStringArray(R.array.titles);
        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public NormalTextViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalTextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
    }

    @Override
    public void onBindViewHolder(NormalTextViewHolder holder, int position) {
        holder.mTextView.setText(mTitles[position]);
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.length;
    }

    public static class NormalTextViewHolder extends RecyclerView.ViewHolder {
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
    }
}
