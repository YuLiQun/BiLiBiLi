package qunzai.bilibili.live.view;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.internet.LoadingImageView;
import qunzai.bilibili.utils.CircleImageView;

/**
 * Created by dllo on 16/12/10.
 */
public class ConcernAnchorActivity extends BaseActivity{

    private LoadingImageView mViewThree;
    private LoadingImageView mViewFore;
    private String url = "http://ww1.sinaimg.cn/large/006y8mN6jw1faa44t4th5g305k05k0t7.gif";
    private String circleUrl = "http://pic.souxuexiao.com/KindEditor/upload/2016-03-24/2016-03-24-09-26-20-457.jpg";
    private CircleImageView mCircleImage;

    @Override
    protected int getLayout() {
        return R.layout.activity_concern_categories;
    }

    @Override
    protected void initViews() {
        mViewFore = bindView(R.id.activity_concern_categories_loading_image_view_fore);
        mCircleImage = bindView(R.id.activity_concern_categories_circle_image_view);
    }

    @Override
    protected void initData() {
        mViewFore.loadImageDefault(url);
        mCircleImage.loadImageDefault(circleUrl);
    }
}
