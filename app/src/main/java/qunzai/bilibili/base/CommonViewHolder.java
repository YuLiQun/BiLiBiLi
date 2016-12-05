package qunzai.bilibili.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import qunzai.bilibili.internet.LoadingImageView;


/**
 * Created by qunzai on 16/11/1.
 */
public class CommonViewHolder extends RecyclerView.ViewHolder {
    private SparseArray<View> views;
    private View itemView;


    /*ListView 和RecyclerView 的构造方法*/
    public CommonViewHolder(View itemView) {
        super(itemView);
        this.itemView = itemView;
        views = new SparseArray<>();
    }


    /**
     * 通过View的id来指定View,,如果该View没有赋值,就先指向findViewById,,然后放到View的集合里
     * 使用泛型来强转
     *
     * @param id
     * @returnv 指定的View
     */
    public <T extends View> T getView(int id) {
        View view = views.get(id);
        if (view == null) {
            view = itemView.findViewById(id);
            views.put(id, view);
        }
        return (T) view;
    }

    //返回行布局
    public View getItemView() {
        return itemView;
    }


    //如果不写静态的方法,,就得先new出来,,通过类名调用
    //专门给ListView使用的方法
    public static CommonViewHolder getViewHolder(View itemView, ViewGroup parent, int itemId) {
        CommonViewHolder viewHolder = null;
        Context context = parent.getContext();
        if (itemView == null) {
            itemView = LayoutInflater.from(context).inflate(itemId, parent, false);
            viewHolder = new CommonViewHolder(itemView);
            itemView.setTag(viewHolder);
        } else {
            viewHolder = (CommonViewHolder) itemView.getTag();
        }

        return viewHolder;
    }


    //重载
    //专门给reycler使用的方法
    public static CommonViewHolder getViewHolder(ViewGroup parent, int itemId) {
        return getViewHolder(null, parent, itemId);
    }

    /***********************
     * ViewHolder 设置数据的方法
     *****************************/

    //设置文字
    // 所有的set方法,,,返回值都可以这么写,,可以继续点
    public CommonViewHolder setText(int id, String text) {
        TextView textView = getView(id);
        textView.setText(text);
        //返回的是当前使用的对象
        return this;
    }

//    public CommonViewHolder setRBtnText(int id, String text){
//        RadioButton textView = getView(id);
//        textView.setText(text);
//        //返回的是当前使用的对象
//        return this;
//    }

//    public RadioButton setRBtnText(int id, String text){
//        RadioButton textView = getView(id);
//        textView.setText(text);
//        //返回的是当前使用的对象
//        return textView;
//    }


    public CommonViewHolder setImage(int id, int imgId) {
        ImageView imageView = getView(id);
        imageView.setImageResource(imgId);
        return this;
    }


    public ImageView setImageAttribute(int id) {
        ImageView imageView = getView(id);

        return imageView;
    }





    public CommonViewHolder setImage(int id, String url) {
        ImageView imageView = getView(id);
        //TODO 这里有网络请求
//        VolleySingleSimple.getInstance().getImage(url,imageView);
        return this;
    }

    public CommonViewHolder setLoadingImage(int id, int imgId){
        LoadingImageView imageView = getView(id);
        imageView.loadLocalImage(imgId);
        return this;
    }

    public CommonViewHolder setLoadingImage(int id, String url){
        LoadingImageView imageView = getView(id);
        imageView.loadImageDefault(url);
        return this;
    }

    //设置点击事件的监听
    public CommonViewHolder setViewClick(int id, View.OnClickListener listener) {
        getView(id).setOnClickListener(listener);
        return this;
    }


    //行布局设置点击事件
    public CommonViewHolder setItemClick(View.OnClickListener listener) {
        itemView.setOnClickListener(listener);
        return this;
    }


    //设置文字颜色
    public void setTextColor(int id, int color) {
        TextView textView = getView(id);
        textView.setTextColor(color);
    }

    public void setBackgroundColor(int id, int color) {
        View view = getView(id);
        view.setBackgroundColor(color);
    }


    //画园
    public void setCircleImg(int id, String url) {
        ImageView imageView = getView(id);
        //TODO 这里也有网络请求
//        VolleySingleSimple.getInstance().getCircleImg(url,imageView);
    }


    //下划线
    public TextView setTextAttribute(int id) {
        TextView textView = getView(id);
        //返回的是当前使用的对象
        return textView;
    }

    public static CommonViewHolder getHeadViewHolder(View view) {

        return new CommonViewHolder(view);
    }
}
