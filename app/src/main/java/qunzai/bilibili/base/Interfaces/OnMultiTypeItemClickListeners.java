package qunzai.bilibili.base.Interfaces;

import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by XingMingDa on 16/12/8.
 */

public interface OnMultiTypeItemClickListeners<T> {
    void onItemClick(CommonViewHolder viewHolder, T data, int position, int viewType);
}
