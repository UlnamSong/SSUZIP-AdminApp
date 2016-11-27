package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Data.CheckboxItem;
import com.ssumunity.ssuzip_admin.Controller.CheckboxListviewAdapter2;
import com.ssumunity.ssuzip_admin.Data.CheckedArray;
import com.ssumunity.ssuzip_admin.Model.DialogUtil;
import com.ssumunity.ssuzip_admin.R;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static android.content.ContentValues.TAG;

public class EventCreateThreeActivity extends AppCompatActivity {

    private ActionBar actionBar = null;
    private ListView listView_B = null;
    private ArrayList<CheckboxItem> buildArray = new ArrayList<>();
    private Button createButton = null;
    private TextView tvBuilding = null;
    private TextView tvProgress = null;

    // For ProgressBar Animation Display
    AlphaAnimation inAnimation;
    AlphaAnimation outAnimation;
    private FrameLayout progressBarHolder = null;

    private String title = "";
    private String content = "";
    private String maxPerson = "";
    private String year;
    private String month;
    private String day;

    private String[] majors;
    private String[] buildings;

    private TextView TextViewNewFont;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventCreateThreeActivity.this);
        setContentView(R.layout.activity_event_create_three);

        actionBar = getSupportActionBar();

        TextViewNewFont = new TextView(EventCreateThreeActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_create_actionbar_title));

        // TextView Color
        TextViewNewFont.setTextColor(getResources().getColor(R.color.actionbar_text_color));

        // TextView Gravity : 일단 비활성화 (Center Alignment가 안됨)
        //TextViewNewFont.setGravity(Gravity.CENTER_HORIZONTAL);
        TextViewNewFont.setTextSize(18);

        // Load Typeface font url String ExternalFontPath
        Typeface FontLoaderTypeface = Typeface.createFromAsset(getAssets(), this.getString(R.string.typeface_bold));
        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(TextViewNewFont);
        actionBar.setDisplayHomeAsUpEnabled(true);

        majors = getResources().getStringArray(R.array.major_list);
        buildings = getResources().getStringArray(R.array.building_list);

        setContent();

        Intent intent = getIntent();
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        title = intent.getStringExtra("title");
        maxPerson = intent.getStringExtra("maxperson");
        content = intent.getStringExtra("content");

        generateData();

        CheckboxListviewAdapter2 itemAdapter_B = new CheckboxListviewAdapter2(this, R.layout.checkbox_listitem, buildArray.toArray(new CheckboxItem[] { }));
        listView_B.setAdapter(itemAdapter_B);

        Log.d(TAG, "onCreate: listView_B setAdapter");

        createButton.setTypeface(TypefaceUtil.typeface);

        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int length = CheckedArray.checkArrayBuilding.length;
                int true_count = 0;
                for(int i = 0; i < length; ++i) {
                    if(CheckedArray.checkArrayBuilding[i]) {
                        true_count++;
                    }
                }

                // 아무것도 선택되지 않았을 경우
                if(true_count < 1) {
                    DialogUtil.showDialog(EventCreateThreeActivity.this, getString(R.string.event_create_dialog_building_title), getString(R.string.event_create_dialog_building_content), 1, 1);
                } else {
                    new MyTask().execute();
                }
            }
        });
    }

    private void generateData() {
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
        progressBarHolder = (FrameLayout) findViewById(R.id.progressBarHolder);
        tvProgress = (TextView) findViewById(R.id.textView6);
        tvProgress.setTypeface(TypefaceUtil.typeface_m);
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

    private class MyTask extends AsyncTask<Void, Void, Void> {

        private String ans = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            createButton.setEnabled(false);
            inAnimation = new AlphaAnimation(0f, 1f);
            inAnimation.setDuration(500);
            progressBarHolder.setAnimation(inAnimation);
            progressBarHolder.setVisibility(View.VISIBLE);
            actionBar.setDisplayHomeAsUpEnabled(false);
            TextViewNewFont.setText(getString(R.string.event_now_creating));
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            outAnimation = new AlphaAnimation(1f, 0f);
            outAnimation.setDuration(500);
            progressBarHolder.setAnimation(outAnimation);
            progressBarHolder.setVisibility(View.GONE);
            createButton.setEnabled(true);
            TextViewNewFont.setText(getString(R.string.event_create_success));

            //Toast.makeText(EventCreateThreeActivity.this, ans, Toast.LENGTH_SHORT).show();
            DialogUtil.showDialog(EventCreateThreeActivity.this, getString(R.string.event_create_success), "이벤트가 생성되었습니다. 생성된 이벤트 정보는 아래와 같습니다.\n\n" + ans, 1, 3);
        }

        @Override
        protected Void doInBackground(Void... params) {

            ans += "이벤트이름 : " + title + "\n";
            ans += "마감일 : " + year + "." + month + "." + day + "\n";
            ans += "최대인원 : " + maxPerson + "\n";
            ans += "[설명]" + "\n";
            ans += content + "\n";
            ans += "\n[참가대상]\n";
            for(int i = 0; i < 45; ++i) {
                if(CheckedArray.checkArrayParticipate[i]) {
                    ans += majors[i] + "\n";
                }
            }

            ans += "\n[대상건물]\n";

            for(int i = 0; i < 18; ++i) {
                if(CheckedArray.checkArrayBuilding[i]) {
                    ans += buildings[i] + "\n";

                }
            }

            try {
                for (int i = 0; i < 3; i++) {
                    Log.d(TAG, "Emulating some task.. Step " + i);
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
