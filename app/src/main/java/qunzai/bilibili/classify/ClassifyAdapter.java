package qunzai.bilibili.classify;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by QunZai on 16/11/26.
 */
public class ClassifyAdapter extends RecyclerView.Adapter {
    int icon[] = {R.mipmap.ic_category_live, R.mipmap.ic_category_t13, R.mipmap.ic_category_t1,
            R.mipmap.ic_category_t3, R.mipmap.ic_category_t129, R.mipmap.ic_category_t4,
            R.mipmap.ic_category_t36, R.mipmap.ic_category_t160, R.mipmap.ic_category_t119,
            R.mipmap.ic_category_t155, R.mipmap.ic_category_t165, R.mipmap.ic_category_t5,
            R.mipmap.ic_category_t23, R.mipmap.ic_category_t11, R.mipmap.ic_category_game_center
    };

    String title[] = {"直播","番剧","动画","音乐","舞蹈","游戏",
            "科技","生活","鬼畜","时尚","广告","娱乐","电影","电视剧","游戏中心"};

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(parent,R.layout.item_classify_rv);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        CommonViewHolder viewHolder = (CommonViewHolder) holder;
        viewHolder.setImage(R.id.item_classify_icon_img,icon[position]);
        viewHolder.setText(R.id.item_classify_title_tv,title[position]);
    }

    @Override
    public int getItemCount() {
        return icon.length;
    }
}
