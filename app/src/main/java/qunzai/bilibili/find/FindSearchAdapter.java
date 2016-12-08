package qunzai.bilibili.find;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import qunzai.bilibili.R;
import qunzai.bilibili.base.CommonViewHolder;
import qunzai.bilibili.bean.FindSearchBean;
import qunzai.bilibili.bean.FindTagBean;

/**
 * Created by QunZai on 16/12/8.
 */
public class FindSearchAdapter extends BaseAdapter {

    private FindSearchBean mFindSearchBean;
    private List<FindTagBean> mTagBeanList;


    public FindSearchAdapter(FindSearchBean findSearchBean) {
        mFindSearchBean = findSearchBean;
        mTagBeanList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            try {

                Class<? extends FindSearchBean.TagBean> aClass = mFindSearchBean.getTag().getClass();
                try {
                    Field field = aClass.getDeclaredField("value" + i);
                    field.setAccessible(true);
                    FindTagBean findTagBean = (FindTagBean) field.get(mFindSearchBean.getTag());
                    mTagBeanList.add(findTagBean);
                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            } catch (NullPointerException e) {
                continue;
            }
        }

    }

    @Override
    public int getCount() {
        return mTagBeanList == null ? 0 : mTagBeanList.size();


    }

    @Override
    public Object getItem(int i) {
        return mFindSearchBean.getArrayList().get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        CommonViewHolder commonViewHolder = CommonViewHolder.getViewHolder(viewGroup, R.layout.item_find_search_rv);
//        FindSearchBean.TagBean tagBean = mFindSearchBean.getArrayList().get(i);
//        String name = tagBean.getClass().getName();

        commonViewHolder.setText(R.id.fragment_find_search_tv, mTagBeanList.get(i).getName());
        return commonViewHolder.getItemView();
    }


}
