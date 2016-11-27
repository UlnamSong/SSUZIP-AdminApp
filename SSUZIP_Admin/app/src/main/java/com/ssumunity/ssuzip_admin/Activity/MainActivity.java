package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.ssumunity.ssuzip_admin.Model.DialogUtil;
import com.ssumunity.ssuzip_admin.R;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

public class MainActivity extends AppCompatActivity {

    private TextView tvName          = null;
    private TextView tvMajor         = null;
    private TextView tvStudentId     = null;
    private TextView tvCurEventText  = null;
    private TextView tvCurEventCount = null;
    private TextView tvTotalEvent    = null;

    private Button adminEventButton  = null;
    private Button settingButton     = null;

    private int currentEventCount = 72;
    private int totalEventCount = 130;

    private String[] majorStr = null;
    private int majorIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(MainActivity.this);
        setContentView(R.layout.activity_main);

        setContent();

        majorStr = getResources().getStringArray(R.array.major_list);
        tvMajor.setText(majorStr[majorIndex]);

        tvCurEventCount.setText(currentEventCount + "");
        tvTotalEvent.setText("/" + totalEventCount + "");

        adminEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, EventListActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

        settingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Settings", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void setContent() {
        tvName = (TextView) findViewById(R.id.tv_profile_name);
        tvMajor = (TextView) findViewById(R.id.tv_profile_major);
        tvStudentId = (TextView) findViewById(R.id.tv_profile_studentid);
        tvCurEventText = (TextView) findViewById(R.id.tv_cur_text);
        tvCurEventCount = (TextView) findViewById(R.id.tv_cur_event);
        tvTotalEvent = (TextView) findViewById(R.id.tv_tot_event);

        tvName.setTypeface(TypefaceUtil.typeface_m);
        tvMajor.setTypeface(TypefaceUtil.typeface);
        tvStudentId.setTypeface(TypefaceUtil.typeface_number_normal);

        tvCurEventCount.setTypeface(TypefaceUtil.typeface_number_bold);
        tvCurEventText.setTypeface(TypefaceUtil.typeface);
        tvTotalEvent.setTypeface(TypefaceUtil.typeface_number_normal);

        adminEventButton = (Button) findViewById(R.id.admin_event_btn);
        settingButton = (Button) findViewById(R.id.setting_btn);

        adminEventButton.setTypeface(TypefaceUtil.typeface);
        settingButton.setTypeface(TypefaceUtil.typeface);

        // Lollipop 이상 버전에서의 상단바 아이콘 색상 문제
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            // Do Nothing
        } else {
            // StatusBar Set
            Window window = getWindow();

            // clear FLAG_TRANSLUCENT_STATUS flag:
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

            // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

            // finally change the color
            window.setStatusBarColor(getResources().getColor(R.color.my_statusbar_color));
        }
    }

    public void onBackPressed() {
        DialogUtil.showDialog(MainActivity.this, getString(R.string.main_logout_dialog_title), getString(R.string.main_logout_dialog_content), 2, 3);
    }
}
