package qunzai.bilibili.live.allcategories.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.bean.CategoriesBean;
import qunzai.bilibili.live.allcategories.view.OnClickLiveEnter;

import static qunzai.bilibili.base.BilibiliApp.getContext;

/**
 * Created by XingMingDa on 16/12/6.
 *
 */
public class CategoriesAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private CategoriesBean mCategoriesBean;
    private OnClickLiveEnter mOnClickLiveEnter;

    public void setCategoriesBean(CategoriesBean categoriesBean) {
        mCategoriesBean = categoriesBean;
        notifyDataSetChanged();
    }

    public void setOnClickLiveEnter(OnClickLiveEnter onClickLiveEnter) {
        mOnClickLiveEnter = onClickLiveEnter;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view  = LayoutInflater.from(getContext()).inflate(R.layout.item_categories,parent,false);
        CommonViewHolder viewHolder = new CommonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.setImage(R.id.item_categories_cover_iv,mCategoriesBean.getData().get(position).getCover().getSrc()
                ,mCategoriesBean.getData().get(position).getCover().getWidth()
                ,mCategoriesBean.getData().get(position).getCover().getHeight())
                .setText(R.id.item_categories_title_tv,mCategoriesBean.getData().get(position).getTitle())
                .setText(R.id.item_categories_name_tv,mCategoriesBean.getData().get(position).getOwner().getName())
                .setText(R.id.item_categories_online_tv,String.valueOf(mCategoriesBean.getData().get(position).getOnline()))
                .setImage(R.id.item_categories_online_iv,R.mipmap.ic_watching)
                .setItemClick(new onItemClick(position));
    }

    @Override
    public int getItemCount() {
        return mCategoriesBean != null ? mCategoriesBean.getData().size() : 0;
    }

    private class onItemClick implements View.OnClickListener{

        private int position;

        private onItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            mOnClickLiveEnter.onClick(mCategoriesBean,position);
        }
    }
}
