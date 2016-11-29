package qunzai.bilibili.find;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by QunZai on 16/11/28.
 */
public class FindAdapter extends RecyclerView.Adapter {
    String title[] = {"兴趣圈","话题中心","活动中心","原创排行榜","全区排行榜","游戏中心","周边商城"};
    int icon[] = {R.mipmap.ic_group,R.mipmap.ic_header_topic_center,R.mipmap.ic_header_activity_center,
            R.mipmap.ic_btn_rank_original,R.mipmap.ic_btn_rank_all,R.mipmap.ic_btn_game,
            R.mipmap.ic_btn_shop};


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent, R.layout.item_find_rv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        viewHolder.setText(R.id.item_find_title_tv,title[position]).
                setImage(R.id.item_find_icon_img,icon[position]);

        if (position == 6){
            viewHolder.setText(R.id.item_find_right_tv,"<装甲联盟>安卓版今日上线");
            viewHolder.setTextAttribute(R.id.item_find_right_tv).setVisibility(View.VISIBLE);
        }else {
            viewHolder.setTextAttribute(R.id.item_find_right_tv).setVisibility(View.GONE);
        }

        if (position == 3 ||position==5){
            viewHolder.getView(R.id.item_find_bottom_tv).setVisibility(View.VISIBLE);
            viewHolder.getView(R.id.item_find_bottom_tv).setBackgroundColor(0xFFFAFAFA);
            ViewGroup.LayoutParams layoutParams =
                    viewHolder.getView(R.id.item_find_bottom_tv).getLayoutParams();
            layoutParams.height = 40;
            viewHolder.getView(R.id.item_find_bottom_tv).setLayoutParams(layoutParams);


        }
    }

    @Override
    public int getItemCount() {
        return title.length;
    }
}
