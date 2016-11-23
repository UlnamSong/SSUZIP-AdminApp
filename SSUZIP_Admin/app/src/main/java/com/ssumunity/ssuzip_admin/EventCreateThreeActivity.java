package com.ssumunity.ssuzip_admin;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EventCreateThreeActivity extends AppCompatActivity {

    private ActionBar actionBar = null;
    private ListView listView_B = null;
    private ArrayList<CheckboxItem> buildArray = new ArrayList<>();
    private Button createButton = null;
    private TextView tvBuilding = null;

    private String title = "";
    private String maxPerson = "";
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventCreateThreeActivity.this);
        setContentView(R.layout.activity_event_create_three);

        actionBar = getSupportActionBar();

        TextView TextViewNewFont = new TextView(EventCreateThreeActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_create_actionbar_title));

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
        actionBar.setDisplayHomeAsUpEnabled(true);


        setContent();

        Intent intent = getIntent();
        year = intent.getIntExtra("year", 2016);
        month = intent.getIntExtra("month", 11);
        day = intent.getIntExtra("day", 23);
        title = intent.getStringExtra("title");
        maxPerson = intent.getStringExtra("maxperson");

        generateData();

        CheckboxListviewAdapter2 itemAdapter_B = new CheckboxListviewAdapter2(this, R.layout.checkbox_listitem, buildArray.toArray(new CheckboxItem[] { }));
        listView_B.setAdapter(itemAdapter_B);

        Log.d(TAG, "onCreate: listView_B setAdapter");

        createButton.setTypeface(TypefaceUtil.typeface);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                for(int i = 0; i < 45; ++i) {
                    if(CheckedArray.checkArrayParticipate[i]) {
                        Log.d(TAG, "onClick: part true! i : " + i);
                    }
                }

                for(int i = 0; i < 18; ++i) {
                    if(CheckedArray.checkArrayBuilding[i]) {
                        Log.d(TAG, "onClick: build true! i : " + i);
                    }
                }
            }
        });
    }

    private void generateData() {
        String[] buildings = getResources().getStringArray(R.array.building_list);

        Log.d(TAG, "generateData: buildings size : " + buildings.length);

        for(int i = 0; i < buildings.length; ++i) {
            CheckboxItem item = new CheckboxItem();
            item.ischecked = false;
            item.name = buildings[i];

            buildArray.add(item);
        }

        for(int i = 0; i < buildings.length; ++i) {
            Log.d(TAG, "generateData: buildArrayname : " + buildArray.get(i).name);
        }

    }

    private void setContent() {
        listView_B = (ListView) findViewById(R.id.listView_building);
        createButton = (Button) findViewById(R.id.create_button);
        tvBuilding = (TextView) findViewById(R.id.tv_building);
        tvBuilding.setTypeface(TypefaceUtil.typeface_m);
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
