package com.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridLayout;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(7);
        MainCalendar calendar = new MainCalendar(getApplicationContext());
        for(int i=1;i<=31;i++)
            calendar.mAdapter.addItem(new CalItem(i));
        gridView.setAdapter(calendar.mAdapter);
    }
}
