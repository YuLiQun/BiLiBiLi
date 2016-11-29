package qunzai.bilibili.internet;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import qunzai.bilibili.utils.CircleImageView;

/**
 * Created by XingMingDa on 16/11/26.
 * LoadingImageView
 */

public class LoadingImageView extends ImageView {

    public LoadingImageView(Context context) {
        super(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void loadImage(String imgUrl, boolean needFade, int placeholderRes) {
        if (TextUtils.isEmpty(imgUrl)) {
            return;
        }
        if (!(this instanceof CircleImageView)) {
            if (needFade) {
                if (placeholderRes == 0) {
                    Glide.with(getContext()).load(imgUrl).dontAnimate().crossFade().into(this);
                } else {
                    Glide.with(getContext()).load(imgUrl).dontAnimate().crossFade().placeholder(placeholderRes).into(this);
                }
            } else {
                if (placeholderRes == 0) {
                    Glide.with(getContext()).load(imgUrl).dontAnimate().into(this);
                } else {
                    Glide.with(getContext()).load(imgUrl).placeholder(placeholderRes).dontAnimate().into(this);
                }
            }
        } else {
            if (needFade) {
                if (placeholderRes == 0) {
                    Glide.with(getContext()).load(imgUrl).crossFade().dontAnimate().into(this);
                } else {
                    Glide.with(getContext()).load(imgUrl).crossFade().dontAnimate().placeholder(placeholderRes).into(this);
                }
            } else {
                if (placeholderRes == 0) {
                    Glide.with(getContext()).load(imgUrl).dontAnimate().into(this);
                } else {
                    Glide.with(getContext()).load(imgUrl).dontAnimate().placeholder(placeholderRes).into(this);
                }
            }
        }
    }

    public void loadImageNofade(String url,int placeholder){
        this.loadImage(url,false,placeholder);
    }

    public void loadImageNoPlaceHolder(String url,boolean fade){
        this.loadImage(url,fade,0);
    }

    public void loadImageDefault(String url){
        this.loadImageNofade(url,0);
    }

    public void loadLocalImage(int res){
        Glide.with(getContext()).load(res).into(this);
    }

}
