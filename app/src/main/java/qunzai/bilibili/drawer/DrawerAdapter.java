package qunzai.bilibili.drawer;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;

/**
 * Created by QunZai on 16/11/24.
 */
public class DrawerAdapter extends BaseAdapter {

    String title[] = {"首页","我的大会员","会员积分","离线缓存","我的收藏","历史记录"
            ,"我的关注","我的钱包","更新下载", "设置与帮助"};

    int icon[] = {R.mipmap.ic_homepage,R.mipmap.ic_nav_vip,R.mipmap.ic_nav_point,
            R.mipmap.ic_download_grey_24dp,R.mipmap.ic_collection,R.mipmap.ic_history,
            R.mipmap.ic_follow,R.mipmap.ic_wallet,R.mipmap.ic_theme,R.mipmap.ic_set};
    @Override
    public int getCount() {
        return title.length;
    }

    @Override
    public Object getItem(int i) {
        return title[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        CommonViewHolder viewHolder = CommonViewHolder.getViewHolder(viewGroup, R.layout.item_drawer_menu);
        viewHolder.setText(R.id.item_drawer_menu_title_tv,title[i]);
        viewHolder.setImage(R.id.item_drawer_menu_icon_img,icon[i]);

        if (i == 0){
            viewHolder.setTextAttribute(R.id.item_drawer_menu_title_tv).setTextColor(0XFFFC6F97);
        }

        if (i == 3 || i == 7 ){
            viewHolder.setTextAttribute(R.id.item_drawer_menu_line_tv).setVisibility(View.VISIBLE);
        }else {
            viewHolder.setTextAttribute(R.id.item_drawer_menu_line_tv).setVisibility(View.GONE);
        }
        return viewHolder.getItemView();
    }
}
