package qunzai.bilibili.drawer;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.LinearLayout;

import qunzai.bilibili.R;
import qunzai.bilibili.base.BaseActivity;

/**
 * Created by QunZai on 16/12/9.
 */
public class DownActivity extends BaseActivity{

    private LinearLayout mDownLl;
    // Storage Permissions
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};

    @Override
    protected int getLayout() {
        return R.layout.activity_down;
    }

    @Override
    protected void initViews() {
        mDownLl = bindView(R.id.activity_down_ll);
    }

    @Override
    protected void initData() {

        mDownLl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //API23以上得版本需要手动请求权限
                verifyStoragePermissions(DownActivity.this);
                new UpDataManager(DownActivity.this).checkUpData();
            }
        });
    }



    public static void verifyStoragePermissions(Activity activity) {
        // Check if we have write permission
        //调用这个方法,是系统的,checkSelfPermission
        int permission = ActivityCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (permission != PackageManager.PERMISSION_GRANTED) {
            // We don't have permission so prompt the user
            ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE);
        }
    }
}
