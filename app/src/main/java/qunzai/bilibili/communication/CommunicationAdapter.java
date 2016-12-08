package qunzai.bilibili.communication;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.classify.OnRvClickListener;

/**
 * Created by ZhangRui on 16/11/26.
 */
public class CommunicationAdapter extends RecyclerView.Adapter {

    String comm[] = {""};
    private OnRvClickListener mOnRvClickListener;

    public void setOnRvClickListener(OnRvClickListener onRvClickListener) {
        mOnRvClickListener = onRvClickListener;
    }

    int icon[] = {R.mipmap.img_tips_error_load_error};


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(
                parent, R.layout.item_communicatiom);
        return commonViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        CommonViewHolder commonViewHolder = (CommonViewHolder) holder;
        commonViewHolder.setText(R.id.item_tv, comm[position]);
        commonViewHolder.setImage(R.id.item_img, icon[position]);
        commonViewHolder.setLLText(R.id.item_comm_ll).setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        mOnRvClickListener.onClick(position);
                    }
                });


    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        return comm.length;
    }


}
