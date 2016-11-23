package com.ssumunity.ssuzip_admin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static android.content.ContentValues.TAG;

public class EventListActivity extends ActionBarActivity {

    //private EventData[] eventData = new EventData[6];
    private ArrayList<EventData> eventData = new ArrayList<>();

    private ListView listView = null;
    private ActionBar actionBar = null;

    private Button createButton = null;

    private TextView listTitle = null;

    private TextView totEventcount = null;
    private TextView curEventcount = null;

    private TextView totEventcountText = null;
    private TextView curEventcountText = null;

    private TextView tvNow = null;
    private TextView tvAlmost = null;
    private TextView tvEnd = null;

    private int totalCount = 6;
    private int currentCount = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventListActivity.this);
        setContentView(R.layout.activity_event_list);

        actionBar = getSupportActionBar();

        TextView TextViewNewFont = new TextView(EventListActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_actionbar_title));

        // TextView Color
        TextViewNewFont.setTextColor(Color.WHITE);

        // TextView Gravity : 일단 비활성화 (Center Alignment가 안됨)
        //TextViewNewFont.setGravity(Gravity.CENTER_HORIZONTAL);
        TextViewNewFont.setTextSize(18);

        // Load Typeface font url String ExternalFontPath
        Typeface FontLoaderTypeface = Typeface.createFromAsset(getAssets(), this.getString(R.string.typeface_bold));
        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(TextViewNewFont);
//      actionBar.setElevation(0);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContent();

        this.generateDummyData();
        //this.sortDummyData();

        EventItemAdapter itemAdapter = new EventItemAdapter(this, R.layout.event_list_item, eventData.toArray(new EventData[] { }));
        listView.setAdapter(itemAdapter);

        totEventcount.setText(totalCount + "");
        curEventcount.setText(currentCount + "");

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EventListActivity.this, EventCreateActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

    }

    private void setContent() {
        listView = (ListView) findViewById(R.id.eventListview);

        listTitle = (TextView) findViewById(R.id.textView5);
        listTitle.setTypeface(TypefaceUtil.typeface_m);

        totEventcount = (TextView) findViewById(R.id.tvTotEventCount);
        totEventcount.setTypeface(TypefaceUtil.typeface_m);
        curEventcount = (TextView) findViewById(R.id.tvCurEventCount);
        curEventcount.setTypeface(TypefaceUtil.typeface_m);

        totEventcountText = (TextView) findViewById(R.id.tvTotEventText);
        totEventcountText.setTypeface(TypefaceUtil.typeface_m);
        curEventcountText = (TextView) findViewById(R.id.tvCurEventText);
        curEventcountText.setTypeface(TypefaceUtil.typeface_m);

        tvNow = (TextView) findViewById(R.id.tv_now);
        tvNow.setTypeface(TypefaceUtil.typeface);

        tvAlmost = (TextView) findViewById(R.id.tv_almost);
        tvAlmost.setTypeface(TypefaceUtil.typeface);

        tvEnd = (TextView) findViewById(R.id.tv_end);
        tvEnd.setTypeface(TypefaceUtil.typeface);

        createButton = (Button) findViewById(R.id.select_major_button);
        createButton.setTypeface(TypefaceUtil.typeface);
    }

    private void sortDummyData() {
        Collections.sort(eventData, new NoAscCompare());
    }

    static class NoAscCompare implements Comparator<EventData> {

        @Override
        public int compare(EventData arg0, EventData arg1) {
            // TODO Auto-generated method stub
            if(Integer.parseInt(arg0.year) > Integer.parseInt(arg1.year)) {
                return -1;
            } else if(Integer.parseInt(arg0.month) < Integer.parseInt(arg1.month)) {
                return 1;
            } else {
                if(Integer.parseInt(arg0.month) > Integer.parseInt(arg1.month)) {
                    return -1;
                } else if(Integer.parseInt(arg0.month) < Integer.parseInt(arg1.month)) {
                    return 1;
                } else {
                    if(Integer.parseInt(arg0.day) > Integer.parseInt(arg1.day)) {
                        return -1;
                    } else if(Integer.parseInt(arg0.day) < Integer.parseInt(arg1.day)) {
                        return 1;
                    } else {
                        return 0;
                    }
                }
            }
        }

    }

    private void generateDummyData() {
        for (int i = 0; i < 15; ++i) {
            EventData tempArr = new EventData();

            tempArr.year = "2016";
            int tempInt = (int) ((Math.random() * 11) + 1);
            if(tempInt < 10) {
                tempArr.month = "0" + tempInt + "";
            } else {
                tempArr.month = tempInt + "";
            }

            tempInt = (int) ((Math.random() * 31) + 1);
            if(tempInt < 10) {
                tempArr.day = "0" + tempInt + "";
            } else {
                tempArr.day = tempInt + "";
            }
            tempArr.title = getString(R.string.event_actionbar_title) + (14 - i);

            int temp = (int) (Math.random() * 300);

            tempArr.totNumber = temp + "";
            tempArr.curNumber = (int) (Math.random() * temp) + "";
            tempArr.eventStatus = (int) (Math.random() * 3) + "";
            //eventData[i] = tempArr;
            eventData.add(tempArr);
            //Log.d(TAG, "generateDummyData : " + i + " : " + eventData[i].title);
            for(int j = 0; j <= i; ++j) {
                //Log.d(TAG, "generateDummyData2 : " + j + " : " + eventData[j].title);
                Log.d(TAG, "generateDummyData2 : " + j + " : " + eventData.get(j).title);
            }
        }
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
