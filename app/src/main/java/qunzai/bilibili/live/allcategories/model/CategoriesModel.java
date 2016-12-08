package qunzai.bilibili.live.allcategories.model;

import qunzai.bilibili.live.allcategories.presenter.OnGetCategoriesBeanListener;

/**
 * Created by XingMingDa on 16/12/6.
 */

public interface CategoriesModel {
    void getCategoriesRequest(String url, Class clazz, OnGetCategoriesBeanListener listener);
}
