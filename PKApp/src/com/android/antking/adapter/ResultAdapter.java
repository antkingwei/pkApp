package com.android.antking.adapter;

import android.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ResultAdapter extends BaseAdapter{
    
    private Context mContext;
    
    private List<String> list;
    public ResultAdapter(Context context,List<String> list){
        this.mContext = context;
        this.list = list;
    }
    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // TODO Auto-generated method stub
        if(convertView==null){
            convertView = new TextView(mContext);           
        }
        if(position==0)
            convertView.setBackgroundColor(Color.RED);
        ((TextView)convertView).setTextColor(Color.BLACK);
        ((TextView)convertView).setText(list.get(position));
        return convertView;
    }

}
