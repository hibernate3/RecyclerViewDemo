package com.example.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by wangyuhang on 2017/1/4.
 */

public class MultipleItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static enum ITEM_TYPE {
        ITEM_TYPE_IMAGE,
        ITEM_TYPE_TEXT
    }

    private final LayoutInflater mLayoutInflater;
    private final Context mContext;
    private List<String> mTitles;

    public MultipleItemAdapter(Context context) {
//        mTitles = context.getResources().getStringArray(R.array.titles);
        mTitles = new ArrayList<String>(Arrays.asList(context.getResources().getStringArray(R.array.titles)));

        mContext = context;
        mLayoutInflater = LayoutInflater.from(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal()) {
            return new ImageViewHolder(mLayoutInflater.inflate(R.layout.item_image, parent, false));
        } else {
            return new TextViewHolder(mLayoutInflater.inflate(R.layout.item_text, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof TextViewHolder) {
            ((TextViewHolder) holder).mTextView.setText(mTitles.get(position));
        } else if (holder instanceof ImageViewHolder) {
            ((ImageViewHolder) holder).mImageView.setImageResource(R.drawable.test);
            ((ImageViewHolder) holder).mTextView.setText(mTitles.get(position));
        }
    }

    @Override
    public int getItemViewType(int position) {
        return position % 2 == 0 ? ITEM_TYPE.ITEM_TYPE_IMAGE.ordinal() : ITEM_TYPE.ITEM_TYPE_TEXT.ordinal();
    }

    @Override
    public int getItemCount() {
        return mTitles == null ? 0 : mTitles.size();
    }

    public static class TextViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView mTextView;

        public TextViewHolder(View itemView) {
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

    public static class ImageViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.text_view)
        TextView mTextView;
        @BindView(R.id.image_view)
        ImageView mImageView;

        public ImageViewHolder(View itemView) {
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

    public void addData(int position) {
        mTitles.add(position, "Insert One");
        notifyItemInserted(position);
    }

    public void removeData(int position) {
        mTitles.remove(position);
        notifyItemRemoved(position);
    }
}
