package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Data.EventData;
import com.ssumunity.ssuzip_admin.Controller.EventItemAdapter;
import com.ssumunity.ssuzip_admin.R;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class EventListActivity extends ActionBarActivity {

    private ArrayList<EventData> eventData = new ArrayList<>();

    private ListView listView = null;
    private ActionBar actionBar = null;

    private Button createButton = null;
    private Button refreshButton = null;

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

    private EventItemAdapter itemAdapter;
    private EventData[] eventArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventListActivity.this);
        setContentView(R.layout.activity_event_list);
        actionBar = getSupportActionBar();

        final TextView TextViewNewFont = new TextView(EventListActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_actionbar_title));
        TextViewNewFont.setTextColor(getResources().getColor(R.color.actionbar_text_color));
        TextViewNewFont.setTextSize(18);

        // Load Typeface font url String ExternalFontPath
        Typeface FontLoaderTypeface = Typeface.createFromAsset(getAssets(), this.getString(R.string.typeface_bold));
        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(TextViewNewFont);
        actionBar.setDisplayHomeAsUpEnabled(true);

        setContent();

        this.generateDummyData();
        eventArray = eventData.toArray(new EventData[] { });

        itemAdapter = new EventItemAdapter(this, R.layout.event_list_item, eventArray);
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

        refreshButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                runOnUiThread(new Runnable(){
                    @Override
                    public void run() {
                        loadListView();
                    }
                });
            }
        });

    }

    // ListView 새로고침하는 부분
    private void loadListView() {
        this.generateDummyData();
        eventArray = eventData.toArray(new EventData[] { });
        itemAdapter = new EventItemAdapter(this, R.layout.event_list_item, eventArray);
        listView.setAdapter(itemAdapter);
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

        refreshButton = (Button) findViewById(R.id.refresh_list_button);
        refreshButton.setTypeface(TypefaceUtil.typeface);
    }

    // 현재는 미사용 ( 데이터 정렬 기능 제공할 경우 사용 )
    // 리스트 정렬 후 리스트 뷰 갱신
    private void sortDummyData() {
        Collections.sort(eventData, new NoAscCompare());
        itemAdapter = new EventItemAdapter(this, R.layout.event_list_item, eventArray);
        listView.setAdapter(itemAdapter);
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

    @Override
    protected void onResume() {
        loadListView();
        super.onResume();
    }

    // 더미 데이터 수집하는 부분 : 추후 서버로부터 받은 데이터를 처리하
    private void generateDummyData() {
        eventData.clear();

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
            tempArr.content = "BlaBlaBlaContent : " + (14 - i) + "\n";
            eventData.add(tempArr);
            //Log.d(TAG, "generateDummyData : " + i + " : " + eventData.get(i).title);
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
