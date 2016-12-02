package qunzai.bilibili.internet;

import android.content.Context;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.drawable.Drawable;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.io.InputStream;
import java.util.Random;

import qunzai.bilibili.R;
import qunzai.bilibili.utils.CircleImageView;

/**
 * Created by XingMingDa on 16/11/26.
 * LoadingImageView
 */

public class LoadingImageView extends ImageView {

    private Movie mMovie;
    private int mImageWidth;
    private int mImageHeight;
    private long mMovieStart;
    private boolean hasNetImg = false;

    public LoadingImageView(Context context) {
        super(context);
    }

    public LoadingImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResLoad(attrs);
    }

    private void setImageResLoad(AttributeSet attrs) {
        int resourceId = getResourcesID(attrs);
        if (resourceId != 0) {
            InputStream inputStream = getResources().openRawResource(resourceId);
            mMovie = Movie.decodeStream(inputStream);
            if (mMovie != null) {
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                mImageWidth = bitmap.getWidth();
                mImageHeight = bitmap.getHeight();
                bitmap.recycle();
            }
        }
    }

    private int getResourcesID(AttributeSet attrs) {
        for (int i = 0; i < attrs.getAttributeCount(); i++) {
            if (attrs.getAttributeName(i).equals("src")) {
                return attrs.getAttributeResourceValue(i, 0);
            }
        }
        Random random = new Random();
        return returnResourcesID(random.nextInt(4));
    }

    private int returnResourcesID(int i) {
        switch (i) {
            case 0:
                return R.mipmap.bilibililoading_1;
            case 1:
                return R.mipmap.bilibililoading_2;
            case 2:
                return R.mipmap.bilibililoading_3;
            default:
                return R.mipmap.bilibililoading_4;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (!hasNetImg) {
            long now = SystemClock.uptimeMillis();
            if (mMovieStart == 0) {
                mMovieStart = now;
            }
            if (mMovie != null) {
                int duration = mMovie.duration();
                int loadTime = (int) ((now - mMovieStart) % duration);
                mMovie.setTime(loadTime);
                mMovie.draw(canvas, 0, 0);
                postInvalidateDelayed(50);
            }
        }
        super.onDraw(canvas);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (mMovie != null) {
            setMeasuredDimension(mImageWidth, mImageHeight);
        }
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

    public void loadImageNofade(String url, int placeholder) {
        this.loadImage(url, false, placeholder);
    }

    public void loadImageNoPlaceHolder(String url, boolean fade) {
        this.loadImage(url, fade, 0);
    }

    public void loadImageDefault(String url) {
        this.loadImageNofade(url, 0);
    }

    @Override
    public void setImageBitmap(Bitmap bm) {
        super.setImageBitmap(bm);
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        hasNetImg = true;
        invalidate();
        super.setImageDrawable(drawable);
    }

    public void loadLocalImage(int res) {
        try {
            Glide.with(getContext()).load(res).asGif().into(this);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
