package com.fly.footprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/9 0009.
 */
public class PublicAdapter extends BaseAdapter{

    ArrayList<String> list;
    Context context;

    public PublicAdapter(Context context, ArrayList<String> list){
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null){
            holder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.public_row,parent,false);
            holder.tv_title = (TextView) convertView.findViewById(R.id.title);
            holder.tv_time = (TextView) convertView.findViewById(R.id.time);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tv_title.setText(list.get(position));
        holder.tv_time.setText(list.get(position));
        return convertView;
    }

    private static class ViewHolder{
        TextView tv_title;
        TextView tv_time;
        //
    }
}
