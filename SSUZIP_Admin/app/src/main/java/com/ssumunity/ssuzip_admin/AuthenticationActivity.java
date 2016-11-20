package com.ssumunity.ssuzip_admin;

import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.TextView;

public class AuthenticationActivity extends AppCompatActivity {

    private ActionBar actionBar     = null;
    private TextView  contentTop    = null;
    private TextView  contentMiddle = null;
    private TextView  contentBottom = null;
    private TextView  tvAccountCode = null;
    private TextView  tvAuthCode    = null;

    private EditText  etAccountCode = null;
    private EditText  etAuthCode    = null;

    private Button    signupButton  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(AuthenticationActivity.this);

        setContentView(R.layout.activity_authentication);
        setContent();

        actionBar = getSupportActionBar();

        TextView TextViewNewFont = new TextView(AuthenticationActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.auth_title));

        TextViewNewFont.setTextColor(Color.WHITE);
        TextViewNewFont.setTextSize(18);

        // Load Typeface font url String ExternalFontPath
        Typeface FontLoaderTypeface = Typeface.createFromAsset(getAssets(), this.getString(R.string.typeface_bold));
        TextViewNewFont.setTypeface(FontLoaderTypeface);

        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(TextViewNewFont);
        actionBar.setDisplayHomeAsUpEnabled(true);

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(etAccountCode.getText().toString().equals("")) {
                    DialogUtil.showDialog(AuthenticationActivity.this, getString(R.string.authentication_dialog_accountcode_title),
                            getString(R.string.authentication_dialog_accountcode_content), 1, 2);
                } else if(etAuthCode.getText().toString().equals("")) {
                    DialogUtil.showDialog(AuthenticationActivity.this, getString(R.string.authentication_dialog_authcode_title),
                            getString(R.string.authentication_dialog_authcode_content), 1, 2);
                } else {
                    // Request to Server.
                }
            }
        });


    }

    private void setContent() {
        tvAccountCode = (TextView) findViewById(R.id.tv_accountcode);
        tvAuthCode = (TextView) findViewById(R.id.tv_authcode);

        contentTop = (TextView) findViewById(R.id.textView2);
        contentMiddle = (TextView) findViewById(R.id.textView3);
        contentBottom = (TextView) findViewById(R.id.textView4);

        etAccountCode = (EditText) findViewById(R.id.et_accountcode);
        etAuthCode = (EditText) findViewById(R.id.et_authcode);

        signupButton = (Button) findViewById(R.id.signup_button);

        tvAccountCode.setTypeface(TypefaceUtil.typeface_m);
        tvAuthCode.setTypeface(TypefaceUtil.typeface_m);

        contentTop.setTypeface(TypefaceUtil.typeface);
        contentMiddle.setTypeface(TypefaceUtil.typeface);
        contentBottom.setTypeface(TypefaceUtil.typeface);

        etAccountCode.setTypeface(TypefaceUtil.typeface);
        etAuthCode.setTypeface(TypefaceUtil.typeface);

        signupButton.setTypeface(TypefaceUtil.typeface_m);
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
