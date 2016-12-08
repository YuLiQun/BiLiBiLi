package qunzai.bilibili.live.allcategories.model;

import qunzai.bilibili.live.allcategories.presenter.OnGetAllCategoriesBeanListener;

/**
 * Created by XingMingDa on 16/12/3.
 * MVP的ModelInterface
 */

public interface AllCategoriesModel {
    void getAllCategoriesRequest(String url, Class clazz, OnGetAllCategoriesBeanListener listener);
}
