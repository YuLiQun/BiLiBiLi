package qunzai.bilibili.drawer;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import qunzai.bilibili.R;

/**
 * Created by QunZai on 2016/12/9.
 */
public class UpDataManager {

    private File akpFile;

    public static final String PATH = "http://api.ocarlife.cn/car/versionType/android_store_version.xml";
    private static final int DOWNLOADING = 1;//正在下载
    private static final int DOWNLOAD_FINISH = 2;//下载完成

    private boolean mIsCancel = false;//是否取消下载

    private String mVersion_code = "";
    private String mVersion_name = "";
    private String mVersion_desc = "";
    private String mVersion_path = "";

    private Context mContext;

    private ProgressBar mProgressBar;
    private LayoutInflater layoutInflater;

    private AlertDialog mDownloadDialog;//设置全局Dialog 下载

    private String mAPKSavePath;

    private int mProgress;//当前进度条位置

    public UpDataManager(Context context) {
        mContext = context;
        layoutInflater = LayoutInflater.from(mContext);
    }


    /**获取服务器的版本信息*/
    private void getServiceAppInfo(){

        new Thread(new Runnable() {
            @Override
            public void run() {

                HttpURLConnection connection = null;

                try {

                    URL url = new URL(PATH);

                    connection = (HttpURLConnection) url.openConnection();
                    //GET就是不需要加参数，就可以获取服务器的信息
                    //POST就是。需要加一些参数才能获取到
                    connection.setRequestMethod("GET");

                    InputStream is = connection.getInputStream();

                    parseXMLWithPull(is);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {

                    if (null != connection){

                        connection.disconnect();
                    }
                }
            }
        }).start();
    }


    /**XML解析*/
    private void parseXMLWithPull(InputStream response){

        try {

            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();

            XmlPullParser xmlPullParser = factory.newPullParser();
            //UTF-8字体格式
            xmlPullParser.setInput(response,"UTF-8");

            /**获取当前解析事件*/
            int eventType = xmlPullParser.getEventType();

            /**如果当前解析事件不等于 END_DOCUMENT ,说明解析还没完成,调用 next 获取下一个解析事件*/
            while (eventType != XmlPullParser.END_DOCUMENT){

                /**获取节点名字,如果等于字段,调用 netText 获取内容*/
                String nodeName = xmlPullParser.getName();

                switch (eventType){

                    /**开始解析某个节点*/
                    case XmlPullParser.START_TAG:

                        if ("version_code".equals(nodeName)){

                            mVersion_code = xmlPullParser.nextText()+"";

                        }else if ("version_name".equals(nodeName)){

                            mVersion_name = xmlPullParser.nextText()+"";

                        }else if ("url".equals(nodeName)){

                            mVersion_path = xmlPullParser.nextText()+"";
                        }

                        break;

                    /**完成解析某个节点*/
                    case XmlPullParser.END_TAG:

                        if ("root".equals(nodeName)){
                            //解析完成
                            mUpDataDownlodProgessHandler.sendEmptyMessage(3);
                        }
                        break;

                    default:
                        break;
                }

                eventType = xmlPullParser.next();
            }

        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 检测软件是否需要跟新
     */
    public void checkUpData() {

        getServiceAppInfo();

    }


    /**
     * 与本地版本比较是否需要更新
     */
    private boolean isUpData() {

        if (!"".equals(mVersion_code) && null != mVersion_code) {

            int serviceVersionCode = Integer.valueOf(mVersion_code);
            int localVersionCode = 1000;

            try {
                localVersionCode = mContext.getPackageManager()
                        .getPackageInfo("com.example.administrator.myapplication", 0)
                        .versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
            }

            return serviceVersionCode > localVersionCode;

        }
        return false;
    }


    /**
     * 有更新时显示提示对话框
     */
    private void showNoticeDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("提示");
        String message = "有新的版本,是否需要更新?" + mVersion_desc;
        builder.setMessage(message);

        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //隐藏对话框
                dialog.dismiss();
                //显示下载对话框
                showDownloadDialog();
            }
        });

        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //隐藏对话框
                dialog.dismiss();
            }
        });

        builder.create().show();

    }


    /**
     * 显示下载的对话框
     */
    private void showDownloadDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("下载中");

        View view = layoutInflater.inflate(R.layout.dialog_progress,null);

        ImageView imageView = (ImageView) view.findViewById(R.id.animation_activity_image_view);
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        AnimationDrawable animationDrawable = (AnimationDrawable) imageView.getBackground();

        builder.setView(view);

        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

                //设置下载状态为确定取消
                mIsCancel = true;

            }
        });

        mDownloadDialog = builder.create();
        mDownloadDialog.show();

        animationDrawable.start();
        dowloadAPK();
    }


    private Handler mUpDataDownlodProgessHandler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {

            switch (msg.what){

                case DOWNLOADING:
                    /**正在下载.设置进度条*/
                    mProgressBar.setProgress(mProgress);
                    break;

                case DOWNLOAD_FINISH:
                    /**下载完成,隐藏Dialog*/
                    mDownloadDialog.dismiss();

                    /**安装*/
                    installAPK();
                    break;

                case 3:

                    if (isUpData()) {
                        //Toast.makeText(mContext, "需要更新", Toast.LENGTH_SHORT).show();
                        showNoticeDialog();
                    } else {
                        Toast.makeText(mContext, "不需要更新", Toast.LENGTH_SHORT).show();
                    }

                    break;
            }
            return false;
        }
    });

    /**
     * 开启新线程下载文件
     */
    private void dowloadAPK() {

        new Thread(new Runnable() {
            @Override
            public void run() {

                /**判断SD卡是否挂载*/
                if (Environment.getExternalStorageState()
                        .equals(Environment.MEDIA_MOUNTED)){

                    /**获取SD卡路径*/
                    String sdPath = Environment.getExternalStorageDirectory().getAbsolutePath();

                    /**apk文件路径*/
                    mAPKSavePath = sdPath + "apk";

                    /**下载文件*/
                    try {

                        URL url = new URL(mVersion_path);

                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                        /**建立连接*/
                        connection.connect();

                        InputStream inputStream = connection.getInputStream();

                        /**获取文件大小*/
                        int length = connection.getContentLength();

                        /** 创建文件*/

                        akpFile = new File(sdPath,"app_name"+mVersion_name+ ".apk");

                        /**写流*/
                        FileOutputStream fos = new FileOutputStream(akpFile);

                        /**本次下载字节数*/
                        int count = 0;

                        byte[] buffer = new byte[1024];



                        while (!mIsCancel){

                            /**本次读取的字节数*/
                            int numRead = inputStream.read(buffer);
                            /**下载的总量*/
                            count += numRead;

                            /**计算当前进度条的位置*/
                            mProgress = (int) ((float)count/length * 100);

                            /**更新进度条*/
                            mUpDataDownlodProgessHandler.sendEmptyMessage(DOWNLOADING);

                            /**下载完成*/
                            if (numRead < 0){
                                mUpDataDownlodProgessHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                                break;
                            }

                            /**参数2 偏移量*/
                            fos.write(buffer,0,numRead);
                        }

                        fos.close();
                        inputStream.close();


                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        }).start();
    }

    /**
     * 下载到本地执行安装
     */
    private void installAPK() {

        File apkFile = akpFile;

        if (!apkFile.exists()){
            return;
        }

        Intent intent = new Intent(Intent.ACTION_VIEW);

        Uri uri = Uri.parse("file://" + apkFile.toString());

        intent.setDataAndType(uri,"application/vnd.android.package-archive");

        mContext.startActivity(intent);

    }






    public static final String TAG = UpDataManager.class.getSimpleName();
    /**
     * 创建文件根目录
     *
     * @param context
     * @param path
     * @return
     */
    public static File createDirFile(Context context, String path) {
        if (null == path) {
            return null;
        }
        File dirFile = null;
        if (!isSDCardExist()) {
            Log.e(TAG, "SDCard Unavailable");
            return null;
        }
        if (!isHasSDCardPermission(context)) {
            Log.e(TAG,
                    "No android.permission.WRITE_EXTERNAL_STORAGE Permission");
            return null;
        }
        if (null == path) {
            Log.e(TAG, "FilePath is null");
            return null;
        }
        dirFile = new File(path);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        return dirFile;
    }

    /**
     * 获取当前SD卡是否可用
     *
     * @return
     */
    public static boolean isSDCardExist() {
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            return true;
        }
        return false;
    }


    /**
     * SD卡操作权限
     */
    private static final String EXTERNAL_STORAGE_PERMISSION = "android.permission.WRITE_EXTERNAL_STORAGE";
    /**
     * 判断是否有读取SD权限
     *
     * @param context
     * @return
     */
    public static boolean isHasSDCardPermission(Context context) {
        int permission = context
                .checkCallingOrSelfPermission(EXTERNAL_STORAGE_PERMISSION);
        return permission == PackageManager.PERMISSION_GRANTED;
    }



}
