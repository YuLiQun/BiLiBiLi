package qunzai.bilibili.live.allcategories.presenter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.bean.AllCategoriesBean;
import qunzai.bilibili.live.allcategories.view.OnClickCategoriesEnter;

import static qunzai.bilibili.base.BilibiliApp.getContext;
import static qunzai.bilibili.internet.UrlClass.CategoriesUrl;
import static qunzai.bilibili.live.allcategories.Values.*;

/**
 * Created by dllo on 16/11/28.
 * 直播全部分类
 */
public class AllCategoriesAdapter extends RecyclerView.Adapter<CommonViewHolder> {

    private OnClickCategoriesEnter mOnClickCategoriesEnter;
    private AllCategoriesBean mAllCategoriesBean;

    public void setAllCategoriesBean(AllCategoriesBean allCategoriesBean) {
        mAllCategoriesBean = allCategoriesBean;
        notifyDataSetChanged();
    }

    public void setOnClickCategoriesEnter(OnClickCategoriesEnter onClickCategoriesEnter) {
        mOnClickCategoriesEnter = onClickCategoriesEnter;
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
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getSrc()
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getWidth()
                ,mAllCategoriesBean.getData().get(position).getEntrance_icon().getHeight())
                .setText(R.id.item_all_categories_loading_tv
                ,mAllCategoriesBean.getData().get(position).getName())
                .setItemClick(new OnItemClick(position));
    }

    @Override
    public int getItemCount() {
        return mAllCategoriesBean != null ? mAllCategoriesBean.getData().size() : 0;
    }

    private class OnItemClick implements View.OnClickListener{

        private int position;

        private OnItemClick(int position) {
            this.position = position;
        }

        @Override
        public void onClick(View view) {
            String name = "";
            String url = "";
            switch (mAllCategoriesBean.getData().get(position).getId()) {
                case MOBILE_LIVE_TYPE:
                    url = CategoriesUrl(MOBILE_LIVE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case MOBILE_GAME_LIVE_TYPE:
                    url = CategoriesUrl(MOBILE_GAME_LIVE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case MOE_CURTILAGE_TYPE:
                    url = CategoriesUrl(MOE_CURTILAGE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case PAINTING_ZONE_TYPE:
                    url = CategoriesUrl(PAINTING_ZONE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case ONLINE_GAME_TYPE:
                    url = CategoriesUrl(ONLINE_GAME_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case PC_GAME_TYPE:
                    url = CategoriesUrl(PC_GAME_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case E_SPORTS_TYPE:
                    url = CategoriesUrl(E_SPORTS_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case SING_DANCE_TYPE:
                    url = CategoriesUrl(SING_DANCE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case LIFE_FUN_TYPE:
                    url = CategoriesUrl(LIFE_FUN_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case OTAKU_CULTURE_TYPE:
                    url = CategoriesUrl(OTAKU_CULTURE_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case SCREENING_ROOM_TYPE:
                    url = CategoriesUrl(SCREENING_ROOM_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
                case CAROUSEL_TYPE:
                    url = CategoriesUrl(CAROUSEL_TYPE);
                    name = mAllCategoriesBean.getData().get(position).getName();
                    break;
            }
            mOnClickCategoriesEnter.onClick(name,url);
        }
    }
}
