package qunzai.bilibili.live.allcategories.presenter;

import qunzai.bilibili.bean.AllCategoriesBean;

/**
 * Created by XingMingDa on 16/12/3.
 * MVP的取得Bean回调监听
 */

public interface OnGetAllCategoriesBeanListener {
    void onSuccess(AllCategoriesBean bean);
}
