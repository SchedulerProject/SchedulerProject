package com.scheduler;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button next;
    Button previous;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //gridView
        final GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setNumColumns(7);
        final MainCalendar calendar = new MainCalendar(getApplicationContext());

        ((TextView) findViewById(R.id.Year_tv)).setText(String.valueOf(calendar.cyear));
        ((TextView) findViewById(R.id.Month_tv)).setText(String.valueOf(calendar.cmonth));

        gridView.setAdapter(calendar.mAdapter);

        next = (Button) findViewById(R.id.NextMonthButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.NextMonth();
                Log.d("log","NextButton Clicked");
                calendar.UpdateData();
                ((TextView) findViewById(R.id.Year_tv)).setText(String.valueOf(calendar.cyear));
                ((TextView) findViewById(R.id.Month_tv)).setText(String.valueOf(calendar.cmonth));
            }
        });
        previous = (Button) findViewById(R.id.PreviousMonthButton);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendar.PreviousMonth();
                Log.d("log","PreviousButton Clicked");
                calendar.UpdateData();
                ((TextView) findViewById(R.id.Year_tv)).setText(String.valueOf(calendar.cyear));
                ((TextView) findViewById(R.id.Month_tv)).setText(String.valueOf(calendar.cmonth));
            }
        });
    }

}
