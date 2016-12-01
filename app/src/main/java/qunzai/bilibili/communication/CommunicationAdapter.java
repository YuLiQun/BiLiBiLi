package qunzai.bilibili.communication;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by ZhangRui on 16/11/26.
 */
public class CommunicationAdapter extends BaseAdapter {
    private Context mContext;
    String comm[] = {"1", "2", "3", "4", "5"};
    int icon[] = {R.mipmap.img_tips_error_load_error,
            R.mipmap.img_tips_error_no_permission,
            R.mipmap.img_tips_error_not_foud,
            R.mipmap.img_tips_error_not_loin,
            R.mipmap.img_tips_error_space_no_data};

    public CommunicationAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return comm.length;
    }

    @Override
    public Object getItem(int position) {
        return comm[position];
    }

    @Override
    public long getItemId(int position) {

        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_communicatiom);
        commonViewHolder.setText(R.id.item_tv, comm[position]);
        commonViewHolder.setImage(R.id.item_img, icon[position]);
        return commonViewHolder.getItemView();
    }
}
