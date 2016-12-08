package qunzai.bilibili.live.allcategories.presenter;

import qunzai.bilibili.bean.AllCategoriesBean;
import qunzai.bilibili.live.allcategories.model.AllCategoriesModelImpl;
import qunzai.bilibili.live.allcategories.view.AllCategoriesActivity;

/**
 * Created by XingMingDa on 16/12/3.
 * MVP的Presenter
 */

public class AllCategoriesPresenterImpl implements AllCategoriesPresenter, OnGetAllCategoriesBeanListener {

    private AllCategoriesActivity mActivity;
    private AllCategoriesModelImpl mModel;

    public AllCategoriesPresenterImpl(AllCategoriesActivity activity) {
        this.mActivity = activity;
        this.mModel = new AllCategoriesModelImpl();
    }

    @Override
    public void onSuccess(AllCategoriesBean bean) {
        mActivity.getAllCategoriesRequest(bean);
    }

    @Override
    public void getAllCategories(String url, Class clazz) {
        mModel.getAllCategoriesRequest(url,clazz,this);
    }
}
