package qunzai.bilibili.live.allcategories.presenter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.bean.AllCategoriesBean;

import static qunzai.bilibili.base.BilibiliApp.getContext;

/**
 * Created by dllo on 16/11/28.
 * 直播全部分类
 */
public class AllCategoriesAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private AllCategoriesBean mAllCategoriesBean;

    public void setAllCategoriesBean(AllCategoriesBean allCategoriesBean) {
        mAllCategoriesBean = allCategoriesBean;
        notifyDataSetChanged();
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_all_categories, parent, false);
        CommonViewHolder viewHolder = new CommonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, final int position) {
        holder.setImage(R.id.item_all_categories_loading_iv
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getSrc()
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getWidth()
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getHeight())
                .setText(R.id.item_all_categories_loading_tv
                ,mAllCategoriesBean.getData().get(position).getName())
                .setItemClick(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        switch (mAllCategoriesBean.getData().get(position).getId()) {
                        }
                    }
                });

    }

    @Override
    public int getItemCount() {
        return mAllCategoriesBean != null ? mAllCategoriesBean.getData().size() : 0;
    }
}
