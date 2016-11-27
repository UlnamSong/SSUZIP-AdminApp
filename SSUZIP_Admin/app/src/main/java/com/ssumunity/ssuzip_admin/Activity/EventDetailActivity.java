package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;
import com.ssumunity.ssuzip_admin.R;

public class EventDetailActivity extends AppCompatActivity {

    private TextView tvTitle = null;
    private TextView tvDate = null;

    private Button statusBtn = null;
    private Button endEventBtn = null;

    private TextView tvMaxPerson = null;
    private TextView tvCurPerson = null;
    private TextView tvMyung = null;

    private TextView tvEventContent = null;
    private TextView tv_EventContent = null;

    private TextView tvMajor = null;
    private TextView tv_Major = null;

    private TextView tvBuilding = null;
    private TextView tv_Building = null;

    private ActionBar actionBar = null;
    private String title = "";
    private String maxPerson = "";
    private String curPerson = "";
    private String content = "";
    private String year = "";
    private String month = "";
    private String day = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventDetailActivity.this);
        setContentView(R.layout.activity_event_detail);

        actionBar = getSupportActionBar();

        final TextView TextViewNewFont = new TextView(EventDetailActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_details_actionbar_title));

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


        // Dummy test를 위한 데이터 생성 ( 네트워크 구현되면 삭제 예정 )
        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        maxPerson = intent.getStringExtra("maxperson");
        curPerson = intent.getStringExtra("curperson");
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        content = intent.getStringExtra("content");

        setContent();

        loadData();
    }

    private void loadData() {
        // Load Data from Server


        // Content Apply
        tvTitle.setText(title);
        tvDate.setText(year + getString(R.string.event_create_year) + " " + month + getString(R.string.event_create_month) + " "  + day + getString(R.string.event_create_day));
        tvCurPerson.setText(curPerson);
        tvMaxPerson.setText("/ " + maxPerson);
        tv_EventContent.setText(content);
    }

    private void setContent(){
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTypeface(TypefaceUtil.typeface);

        tvDate = (TextView) findViewById(R.id.tvDate);
        tvDate.setTypeface(TypefaceUtil.typeface);

        statusBtn = (Button) findViewById(R.id.button);
        statusBtn.setTypeface(TypefaceUtil.typeface);

        tvMyung = (TextView) findViewById(R.id.tv_myung);
        tvMyung.setTypeface(TypefaceUtil.typeface);

        tvMaxPerson = (TextView) findViewById(R.id.tvMaxPerson);
        tvMaxPerson.setTypeface(TypefaceUtil.typeface);

        tvCurPerson = (TextView) findViewById(R.id.tvCurPerson);
        tvCurPerson.setTypeface(TypefaceUtil.typeface_m);

        tvEventContent = (TextView) findViewById(R.id.tvEventContent);
        tvEventContent.setTypeface(TypefaceUtil.typeface_m);

        tv_EventContent = (TextView) findViewById(R.id.tv_EventContent);
        tv_EventContent.setTypeface(TypefaceUtil.typeface);

        tvMajor = (TextView) findViewById(R.id.tvMajor);
        tvMajor.setTypeface(TypefaceUtil.typeface_m);

        tv_Major = (TextView) findViewById(R.id.tv_Major);
        tv_Major.setTypeface(TypefaceUtil.typeface);

        tvBuilding = (TextView) findViewById(R.id.tvBuilding);
        tvBuilding.setTypeface(TypefaceUtil.typeface_m);

        tv_Building = (TextView) findViewById(R.id.tv_Building);
        tv_Building.setTypeface(TypefaceUtil.typeface);

        endEventBtn = (Button) findViewById(R.id.back_button);
        endEventBtn.setTypeface(TypefaceUtil.typeface);
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
