package qunzai.bilibili.live.allcategories;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.utils.CircleImageView;

/**
 * Created by dllo on 16/11/24.
 */

public class AllCategoriesActivity extends BaseActivity {

    private CircleImageView mCircleImageView;

    @Override
    protected int getLayout() {
        return R.layout.activity_all_categories;
    }

    @Override
    protected void initViews() {
        mCircleImageView = bindView(R.id.profile_image);
    }

    @Override
    protected void initData() {
        mCircleImageView.loadImageDefault("http://img2.imgtn.bdimg.com/it/u=1844801748,1310549684&fm=23&gp=0.jpg");
        mCircleImageView.setBorderColor(0xFFFF0000);
    }
}
