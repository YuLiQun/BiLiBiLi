package qunzai.bilibili.live.allcategories.presenter;

import qunzai.bilibili.bean.CategoriesBean;
import qunzai.bilibili.live.allcategories.model.CategoriesModelImpl;
import qunzai.bilibili.live.allcategories.view.CategoriesActivity;

/**
 * Created by XingMingDa on 16/12/6.
 */

public class CategoriesPresenterImpl implements CategoriesPresenter, OnGetCategoriesBeanListener {

    private CategoriesActivity mActivity;
    private CategoriesModelImpl mModel;

    public CategoriesPresenterImpl(CategoriesActivity activity) {
        mActivity = activity;
        this.mModel = new CategoriesModelImpl();
    }

    @Override
    public void getCategories(String url, Class clazz) {
        mModel.getCategoriesRequest(url,clazz,this);
    }

    @Override
    public void onSuccess(CategoriesBean bean) {
        mActivity.getCategoriesRequest(bean);
    }
}
