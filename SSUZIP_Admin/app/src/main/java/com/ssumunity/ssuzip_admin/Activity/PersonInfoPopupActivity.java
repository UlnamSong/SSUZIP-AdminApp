package com.ssumunity.ssuzip_admin.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;
import com.ssumunity.ssuzip_admin.R;

/**
 * Created by Ulnamsong on 2016. 11. 27.
 */

public class PersonInfoPopupActivity extends Activity {

    private Button okButton;
    private TextView tvNameTitle = null;
    private TextView tvIdTitle = null;
    private TextView tvMajorTitle = null;
    private TextView tvGenderTitle = null;
    private TextView tvPhoneTitle = null;

    private TextView tvNameContent = null;
    private TextView tvIdContent = null;
    private TextView tvMajorContent = null;
    private TextView tvGenderContent = null;
    private TextView tvPhoneContent = null;
    private TextView tvMainTitle = null;

    private String rankString = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        TypefaceUtil.loadTypeface(this);
        setContentView(R.layout.person_info_popup);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();

        int width = (int) (display.getWidth() * 0.8); //Display 사이즈의 70%
        int height = (int) (display.getHeight() * 0.7);  //Display 사이즈의 70%

        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        Intent intent = getIntent();
        rankString = intent.getStringExtra("rank");

        setContent();

        // Rank Info -> Main Title
        tvMainTitle.setText(rankString);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }

    private void setContent() {
        okButton = (Button) findViewById(R.id.ok_button);
        tvNameTitle = (TextView) findViewById(R.id.tv_name);
        tvNameTitle.setTypeface(TypefaceUtil.typeface_m);

        tvIdTitle = (TextView) findViewById(R.id.tv_id);
        tvIdTitle.setTypeface(TypefaceUtil.typeface_m);

        tvMajorTitle = (TextView) findViewById(R.id.tv_major);
        tvMajorTitle.setTypeface(TypefaceUtil.typeface_m);

        tvGenderTitle = (TextView) findViewById(R.id.tv_gender);
        tvGenderTitle.setTypeface(TypefaceUtil.typeface_m);

        tvPhoneTitle = (TextView) findViewById(R.id.tv_phone);
        tvPhoneTitle.setTypeface(TypefaceUtil.typeface_m);

        tvNameContent = (TextView) findViewById(R.id.tv_name_content);
        tvNameContent.setTypeface(TypefaceUtil.typeface);

        tvIdContent = (TextView) findViewById(R.id.tv_id_content);
        tvIdContent.setTypeface(TypefaceUtil.typeface);

        tvMajorContent = (TextView) findViewById(R.id.tv_major_content);
        tvMajorContent.setTypeface(TypefaceUtil.typeface);

        tvGenderContent = (TextView) findViewById(R.id.tv_gender_content);
        tvGenderContent.setTypeface(TypefaceUtil.typeface);

        tvPhoneContent = (TextView) findViewById(R.id.tv_phone_content);
        tvPhoneContent.setTypeface(TypefaceUtil.typeface);

        tvMainTitle = (TextView) findViewById(R.id.textView11);
        tvMainTitle.setTypeface(TypefaceUtil.typeface_m);
    }
}

