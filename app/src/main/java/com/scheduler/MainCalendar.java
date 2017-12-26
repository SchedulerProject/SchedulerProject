package com.scheduler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by admin on 2017-12-26.
 */

public class MainCalendar {
    private Context mContext;


    public CalAdapter mAdapter;

    public MainCalendar(Context context){
        mContext = context;
        mAdapter = new CalAdapter();
    }

    class CalItemView extends LinearLayout{
        private TextView CNum_tv;
        public CalItemView(Context context) {
            super(context);
            init(context);
        }

        public CalItemView(Context context, AttributeSet attrs) {
            super(context, attrs);
            init(context);
        }

        public void init(Context context){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            inflater.inflate(R.layout.activity_dateinfo,this,true);

            CNum_tv = (TextView) findViewById(R.id.DateNum);
        }

        public void setCNum(int cnum){
            CNum_tv.setText(String.valueOf(cnum));
        }
    }

    class CalAdapter extends BaseAdapter{
        ArrayList<CalItem> items = new ArrayList<CalItem>();

        @Override
        public int getCount() {
            return items.size();
        }
        public void addItem(CalItem item){
            items.add(item);
        }

        @Override
        public Object getItem(int i) {
            return items.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            CalItemView itemView = new CalItemView(mContext);
            CalItem item = items.get(i);
            itemView.setCNum(item.day);
            return itemView;
        }
    }


}
