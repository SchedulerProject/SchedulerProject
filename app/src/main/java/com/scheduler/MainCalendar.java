package com.scheduler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.zip.Inflater;

/**
 * Created by admin on 2017-12-26.
 */

public class MainCalendar {
    private Context mContext;
    public static CalAdapter mAdapter;

    int cyear;
    int cmonth;
    int cday;
    int chour;
    Date cdate;

    public MainCalendar(Context context){
        mContext = context;
        mAdapter = new CalAdapter();

        long now = System.currentTimeMillis();
        cdate = new Date(now);

        SimpleDateFormat sdf_y = new SimpleDateFormat("yyyy");
        cyear = Integer.valueOf(sdf_y.format(cdate));

        SimpleDateFormat sdf_m = new SimpleDateFormat("MM");
        cmonth = Integer.valueOf(sdf_m.format(cdate));

        SimpleDateFormat sdf_d = new SimpleDateFormat("dd");
        cday = Integer.valueOf(sdf_d.format(cdate));

        SimpleDateFormat sdf_h = new SimpleDateFormat("hh");
        chour = Integer.valueOf(sdf_h.format(cdate));
        UpdateData();
    }

    class CalItemView extends LinearLayout{
        private TextView CNum_tv;
        private TextView CMemo_tv;
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
            CMemo_tv = (TextView) findViewById(R.id.Memo);
        }

        public void setCNum(int cnum){
            CNum_tv.setText(String.valueOf(cnum));
        }
        public void setCMemo(String cmemo){
            CMemo_tv.setText(cmemo);
        }
    }

    class CalAdapter extends BaseAdapter{
        ArrayList<CalItem> items = new ArrayList<CalItem>();
        public CalAdapter(){
            clearItems();
        }

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

        public void setItem(int i,CalItem item) {
            items.set(i, item);
        }
        public void clearItems(){
            items.clear();
            for(int i=0;i<42;i++)
                addItem(new CalItem(0));
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            CalItemView itemView = new CalItemView(mContext);
            CalItem item = items.get(i);

            if(item.getDay()!=0)
            itemView.setCNum(item.day);
            itemView.setCMemo(item.memo);

            return itemView;
        }
    }

    public void NextMonth(){
        cmonth ++;
        if(cmonth>12){
            cmonth = 1;
            cyear++;
        }
    }
    public void PreviousMonth(){
        cmonth --;
        if(cmonth<1){
            cmonth = 12;
            cyear--;
        }
    }
    public void UpdateData(){
        String month= String.valueOf(cmonth);
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        try {
            if(cmonth<10){
                month = "0"+ String.valueOf(cmonth);
            }
            Date d1 = sdf.parse(String.valueOf(cyear)+month+"01");
            Log.d("log",String.valueOf(cyear)+month+"01" );
            cal.setTime(d1);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int dayNum = cal.get(Calendar.DAY_OF_WEEK);
        Log.d("log","요일 값 : " + dayNum);

        int i = 0;

        mAdapter.clearItems();
        for(i=0;i<dayNum;i++)
            mAdapter.setItem(i,new CalItem(0));
        for(i=1;i<=31;i++)
            mAdapter.setItem(dayNum+i-2,new CalItem(i));

        mAdapter.notifyDataSetChanged();
    }

}
