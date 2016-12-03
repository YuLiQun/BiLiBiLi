package qunzai.bilibili.live.allcategories.model;

import qunzai.bilibili.bean.AllCategoriesBean;
import qunzai.bilibili.internet.OkHttpManager;
import qunzai.bilibili.internet.ResponseCallBack;
import qunzai.bilibili.live.allcategories.presenter.OnGetAllCategoriesBeanListener;

import static qunzai.bilibili.internet.UrlClass.URL_ALL_CATEGORIES;

/**
 * Created by XingMingDa on 16/12/3.
 * MVP的Model
 */

public class AllCategoriesModelImpl implements AllCategoriesModel{

    @Override
    public void getAllCategoriesRequest(String url, Class clazz, final OnGetAllCategoriesBeanListener listener) {
        OkHttpManager okHttpManager = new OkHttpManager();
        okHttpManager.get(url, clazz, new ResponseCallBack<AllCategoriesBean>() {
            @Override
            public void onResponse(AllCategoriesBean allCategoriesBean) {
                listener.onSuccess(allCategoriesBean);
            }

            @Override
            public void onError(Exception exception) {

            }
        });
    }
}
