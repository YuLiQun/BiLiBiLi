package qunzai.bilibili.main.guidepage;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;
import qunzai.bilibili.main.MainActivity;

/**
 * Created by ZhangRui on 16/12/3.
 */

public class GuidePageActivity extends BaseActivity {
    private ViewPager viewPager;
    private GuideViewPagerAdapter guideViewPagerAdapter;
    private ArrayList<View> data;
    private View guideLayout;

    public static boolean isForeground = false;
    @Override
    protected int getLayout() {
        //通过数据持久化 来判断是否引导页是不是第一个次执行
        SharedPreferences sharedPreferences1 = this.getSharedPreferences("Guide", MODE_PRIVATE);
        boolean isFirst = sharedPreferences1.getBoolean("isFirst", true);
        if (!isFirst) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
        SharedPreferences sharedPreferences = getSharedPreferences("Guide", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("isFirst", false);
        editor.commit();
        return R.layout.activity_guide;
    }

    @Override
    protected void initViews() {
        viewPager = (ViewPager) findViewById(R.id.guideViewPager);
    }

    @Override
    protected void initData() {
        data = new ArrayList<>();
        View guideOneIvView = this.getLayoutInflater().inflate(R.layout.guide_image_one, null);
        View guideTwoIvView = this.getLayoutInflater().inflate(R.layout.guide_image_two, null);
        View guideThreeIvView = this.getLayoutInflater().inflate(R.layout.guide_image_three, null);
        View guideFourIvView = this.getLayoutInflater().inflate(R.layout.guide_image_four, null);
        guideLayout = guideFourIvView.findViewById(R.id.aty_guide_layout);
        guideLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GuidePageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        data.add(guideOneIvView);
        data.add(guideTwoIvView);
        data.add(guideThreeIvView);
        data.add(guideFourIvView);

        guideViewPagerAdapter = new GuideViewPagerAdapter(data);
        viewPager.setAdapter(guideViewPagerAdapter);

    }

    @Override
    protected void onResume() {
        isForeground = true;
        super.onResume();
    }

    @Override
    protected void onPause() {
        isForeground = false;
        super.onPause();
    }

}
