package qunzai.bilibili.live.allcategories;

import android.support.v7.widget.RecyclerView;
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
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_all_categories, parent, false);
        CommonViewHolder viewHolder = new CommonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_all_categories_loading_iv
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getSrc());
    }

    @Override
    public int getItemCount() {
        return mAllCategoriesBean != null ? mAllCategoriesBean.getData().size() : 0;
    }
}
