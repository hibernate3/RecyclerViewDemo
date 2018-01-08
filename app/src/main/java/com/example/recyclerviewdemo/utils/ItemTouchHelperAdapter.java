package com.example.recyclerviewdemo.utils;

/**
 * Created by wangyuhang@evergrande.cn on 2018-1-8.
 */

public interface ItemTouchHelperAdapter {
    void onItemMove(int fromPosition, int toPosition);
    void onItemDismiss(int position);
}
