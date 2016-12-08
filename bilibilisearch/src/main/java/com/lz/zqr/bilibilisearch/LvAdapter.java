package com.lz.zqr.bilibilisearch;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by QunZai on 16/12/7.
 */
public class LvAdapter extends BaseAdapter{
    private ArrayList<String> mArrayList;

    public LvAdapter(ArrayList<String> arrayList) {
        mArrayList = arrayList;
    }

    @Override
    public int getCount() {
        return mArrayList == null ? 0 :mArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LvViewHolder viewHolder = null;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lv,parent,false);
            viewHolder = new LvViewHolder(convertView);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (LvViewHolder) convertView.getTag();
        }
        viewHolder.tv.setText(mArrayList.get(position));
        return convertView;
    }

    class LvViewHolder{

        private TextView tv;

        public LvViewHolder(View convertView) {
            tv = (TextView) convertView.findViewById(R.id.item_lv_tv);
        }
    }

}
