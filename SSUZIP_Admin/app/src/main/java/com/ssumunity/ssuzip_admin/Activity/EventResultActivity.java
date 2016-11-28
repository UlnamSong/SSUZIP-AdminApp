package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.ssumunity.ssuzip_admin.Controller.RankItemAdapter;
import com.ssumunity.ssuzip_admin.Data.CheckboxItem;
import com.ssumunity.ssuzip_admin.Data.RankData;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;
import com.ssumunity.ssuzip_admin.R;

import java.util.ArrayList;

public class EventResultActivity extends AppCompatActivity {

    private ArrayList<RankData> rankList = new ArrayList<>();
    private RankData[] rankArray;
    private ActionBar actionBar = null;

    private TextView tvFirstName;
    private TextView tvFirstInfo;
    private TextView tvPerson;
    private TextView tvFirstRank;
    private TextView tvMemCount;
    private TextView tvTitle;
    private ListView listView;
    private TextView tvRankListTitle;
    private TextView tvRankListhint;

    private Button backButton;

    private String title = "";
    private String curPerson = "";
    private String content = "";
    private String year = "";
    private String month = "";
    private String day = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(EventResultActivity.this);
        setContentView(R.layout.activity_event_result);

        actionBar = getSupportActionBar();

        final TextView TextViewNewFont = new TextView(EventResultActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.event_results_actionbar_title));

        // TextView Color
        TextViewNewFont.setTextColor(getResources().getColor(R.color.actionbar_text_color));
        TextViewNewFont.setTextSize(18);

        // Load Typeface font url String ExternalFontPath
        Typeface FontLoaderTypeface = Typeface.createFromAsset(getAssets(), this.getString(R.string.typeface_bold));
        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(TextViewNewFont);
        actionBar.setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        title = intent.getStringExtra("title");
        curPerson = intent.getStringExtra("curperson");
        year = intent.getStringExtra("year");
        month = intent.getStringExtra("month");
        day = intent.getStringExtra("day");
        content = intent.getStringExtra("content");

        setContent();

        generateDummyData(Integer.parseInt(curPerson));
        rankArray = rankList.toArray(new RankData[] { });

        tvMemCount.setText(curPerson);

        RankItemAdapter rankItemAdapter = new RankItemAdapter(this, R.layout.rank_item, rankArray);
        listView.setAdapter(rankItemAdapter);
        
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //Toast.makeText(EventResultActivity.this, "Position : " + i, Toast.LENGTH_SHORT).show();

                int rank = i + 1;
                String rankString;

                switch(rank) {
                    case 1:
                        rankString = (rank + getString(R.string.event_results_rank_first_token));
                        break;
                    case 2:
                        rankString = (rank + getString(R.string.event_results_rank_second_token));
                        break;
                    case 3:
                        rankString = (rank + getString(R.string.event_results_rank_third_token));
                        break;
                    default:
                        rankString = (rank + getString(R.string.event_results_rank_extra_token));
                        break;
                }

                Intent intent = new Intent(EventResultActivity.this, PersonInfoPopupActivity.class);
                intent.putExtra("rank", rankString);
                startActivity(intent);
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

        tvTitle.setText(title);

        tvFirstName.setText(rankArray[0].rankName);
        tvFirstInfo.setText(rankArray[0].rankID + ", " + rankArray[0].rankMajor);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setContent() {
        listView = (ListView) findViewById(R.id.listView_Rank);

        tvFirstName = (TextView) findViewById(R.id.tvFirstRankName);
        tvFirstName.setTypeface(TypefaceUtil.typeface_m);

        tvFirstInfo = (TextView) findViewById(R.id.tvFirstDetail);
        tvFirstInfo.setTypeface(TypefaceUtil.typeface);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        tvTitle.setTypeface(TypefaceUtil.typeface_m);

        tvMemCount = (TextView) findViewById(R.id.tvCurPerson);
        tvMemCount.setTypeface(TypefaceUtil.typeface_m);

        tvRankListTitle = (TextView) findViewById(R.id.tvRankListTitle);
        tvRankListTitle.setTypeface(TypefaceUtil.typeface_m);

        tvRankListhint = (TextView) findViewById(R.id.tvRankListHint);
        tvRankListhint.setTypeface(TypefaceUtil.typeface);

        tvPerson = (TextView) findViewById(R.id.tvPerson);
        tvPerson.setTypeface(TypefaceUtil.typeface_m);

        tvFirstRank = (TextView) findViewById(R.id.tvFirstRank);
        tvFirstRank.setTypeface(TypefaceUtil.typeface_m);

        backButton = (Button) findViewById(R.id.back_button);
        backButton.setTypeface(TypefaceUtil.typeface);
    }

    private void generateDummyData(int max) {

        for(int i = 0; i < max; ++i) {
            // randomVal,major : temp variable
            int randomVal = (int)(Math.random() * 44);
            String[] major = getResources().getStringArray(R.array.major_list);
            RankData temp = new RankData("NAME" + i, major[randomVal], "2014" + (max < 10 ? 10 : (max > 99 ? 99 : max)) + (randomVal < 10 ? 10 : randomVal));
            rankList.add(temp);
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
