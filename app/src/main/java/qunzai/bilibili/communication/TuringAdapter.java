package qunzai.bilibili.communication;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by ZhangRui on 16/11/26.
 */
public class TuringAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private static final int LEFT = 0;
    private static final int RIGHT = 1;
    ArrayList<TuringBean> mArrayList;

    public void setArrayList(ArrayList<TuringBean> arrayList) {
        mArrayList = arrayList;
        notifyDataSetChanged();
    }

    public TuringAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        Log.d("TuringAdapter", "mArrayList.get(position).getType():" + mArrayList.get(position).getType());
        if (mArrayList.get(position).getType() == LEFT) {
            return LEFT;
        }

        return RIGHT;


    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case LEFT:
                CommonViewHolder leftViewHolder = CommonViewHolder.getViewHolder(parent,
                        R.layout.item_communication_dialogue_left);
                return leftViewHolder;
            case RIGHT:
                CommonViewHolder rightViewHolder = CommonViewHolder.getViewHolder(parent,
                        R.layout.item_communication_dialogue_right);
                return rightViewHolder;
        }

        return null;

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        if (getItemViewType(position) == LEFT) {
            viewHolder.setText(R.id.item_left_tv, mArrayList.get(position).getDialogue());
            viewHolder.setImage(R.id.item_left_img, R.mipmap.ic_launcher);
        }

        if (getItemViewType(position) == RIGHT) {
            viewHolder.setText(R.id.item_right_tv, mArrayList.get(position).getDialogue());
            viewHolder.setImage(R.id.item_right_img, R.mipmap.ic_launcher);

        }
    }


    @Override
    public int getItemCount() {
        return mArrayList == null ? 0 : mArrayList.size();
    }
}
