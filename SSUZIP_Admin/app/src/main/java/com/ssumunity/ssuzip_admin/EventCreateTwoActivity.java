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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class EventCreateTwoActivity extends AppCompatActivity {

    private ActionBar actionBar = null;
    private ListView listView_P = null;
    private TextView tvMajor = null;
    private ArrayList<CheckboxItem> partArray = new ArrayList<>();
    private Button createButton = null;
    private String title = "";
    private String maxPerson = "";
    private int year;
    private int month;
    private int day;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventCreateTwoActivity.this);
        setContentView(R.layout.activity_event_create_two);

        actionBar = getSupportActionBar();

        TextView TextViewNewFont = new TextView(EventCreateTwoActivity.this);
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

        CheckboxListviewAdapter itemAdapter_P = new CheckboxListviewAdapter(this, R.layout.checkbox_listitem, partArray.toArray(new CheckboxItem[] { }));
        listView_P.setAdapter(itemAdapter_P);

        Log.d(TAG, "onCreate: listView_P setAdapter");

        createButton.setTypeface(TypefaceUtil.typeface);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EventCreateTwoActivity.this, EventCreateThreeActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("maxperson", maxPerson);
                intent.putExtra("year", year);
                intent.putExtra("month", month);
                intent.putExtra("day", day);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }
        });
    }

    private void generateData() {
        String[] parts = getResources().getStringArray(R.array.major_list);

        Log.d(TAG, "generateData: parts size : " + parts.length);

        for(int i = 0; i < parts.length; ++i) {
            CheckboxItem item = new CheckboxItem();
            item.ischecked = false;
            item.name = parts[i];

            partArray.add(item);
        }

        for(int i = 0; i < parts.length; ++i) {
            Log.d(TAG, "generateData: partArrayname : " + partArray.get(i).name);
        }

    }

    private void setContent() {
        listView_P = (ListView) findViewById(R.id.listView_participate);
        createButton = (Button) findViewById(R.id.select_building_button);
        tvMajor = (TextView) findViewById(R.id.tv_participate);
        tvMajor.setTypeface(TypefaceUtil.typeface_m);
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
