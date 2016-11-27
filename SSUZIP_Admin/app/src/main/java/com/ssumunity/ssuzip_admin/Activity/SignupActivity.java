package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Model.AsteriskPasswordTransformationMethod;
import com.ssumunity.ssuzip_admin.Model.DialogUtil;
import com.ssumunity.ssuzip_admin.Controller.MySpinnerAdapter;
import com.ssumunity.ssuzip_admin.R;
import com.ssumunity.ssuzip_admin.Data.SignupUserData;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

import java.util.Arrays;

public class SignupActivity extends AppCompatActivity {

    private ActionBar actionBar        = null;
    private TextView tvEmail           = null;
    private TextView tvPassword        = null;
    private TextView tvPasswordConfirm = null;
    private TextView tvName            = null;
    private TextView tvGender          = null;
    private TextView tvStudentNumber   = null;
    private TextView tvMajor           = null;

    private EditText etEmail           = null;
    private EditText etPassword        = null;
    private EditText etPasswordConfirm = null;
    private EditText etName            = null;
    private EditText etStudentNumber   = null;

    private RadioButton manButton      = null;
    private RadioButton womanButton    = null;

    private Button   continueButton    = null;
    private Spinner  majorSpinner      = null;

    private int selectedMajorIndex     = 0;
    private String TAG = "SignupActvity :";
    public String[] str;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TypefaceUtil.loadTypeface(SignupActivity.this);
        setContentView(R.layout.activity_signup);

        setContent();

        actionBar = getSupportActionBar();

        TextView TextViewNewFont = new TextView(SignupActivity.this);
        FrameLayout.LayoutParams layoutparams = new FrameLayout.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        TextViewNewFont.setLayoutParams(layoutparams);
        TextViewNewFont.setText(getString(R.string.signup_title));

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

        continueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(accountValidation()) {
                    //Log.d(TAG, "onClick: userInfo : " + SignupUserData.userName + ", " + SignupUserData.userStudentNumber);
                    //Log.d(TAG, "onClick: userInfo : " + SignupUserData.userPassword + ", " + SignupUserData.userPasswordConfirm);
                    //Log.d(TAG, "onClick: userInfo : " + SignupUserData.userGender + ", " + SignupUserData.userMajor + ", " + SignupUserData.userEmail);

                    startActivity(new Intent(SignupActivity.this, AuthenticationActivity.class));
                    overridePendingTransition(R.anim.fade, R.anim.hold);
                    finish();
                }
            }
        });

        // Major Spinner
        str = getResources().getStringArray(R.array.major_list);
        majorSpinner = (Spinner)findViewById(R.id.spinner);
        majorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {

                selectedMajorIndex = position;
                //Toast.makeText(SignupActivity.this, "position : " + position + parent.getItemAtPosition(position), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });

        MySpinnerAdapter adapter = new MySpinnerAdapter(
                this,
                R.layout.support_simple_spinner_dropdown_item,
                Arrays.asList(getResources().getStringArray(R.array.major_list))
        );

        majorSpinner.setAdapter(adapter);
    }

    // Email Format Validation Code
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    // Validation Check
    private boolean accountValidation() {
        SignupUserData.userEmail = etEmail.getText().toString();

        // Email Empty Check
        if(SignupUserData.userEmail.equals("")) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_emailinput_title), getString(R.string.signup_dialog_emailinput_content), 1, 2);
            return false;
        }

        // Email Format Check
        if(!isValidEmail(SignupUserData.userEmail)) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_emailformat_title), getString(R.string.signup_dialog_emailformat_content), 1, 2);
            return false;
        }

        // Password Empty Check
        SignupUserData.userPassword = etPassword.getText().toString();
        if(SignupUserData.userPassword.equals("")) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_password_title), getString(R.string.signup_dialog_password_content), 1, 2);
            return false;
        }

        // Password Confirm Empty Check
        SignupUserData.userPasswordConfirm = etPasswordConfirm.getText().toString();
        if(SignupUserData.userPasswordConfirm.equals("")) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_passwordconfirm_title), getString(R.string.signup_dialog_passwordconfirm_content), 1, 2);
            return false;
        }

        // Password Equal Check
        if(!SignupUserData.userPassword.equals(SignupUserData.userPasswordConfirm)) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_password_error_title), getString(R.string.signup_dialog_password_error_content), 1, 2);
            return false;
        }

        SignupUserData.userName = etName.getText().toString();
        if(SignupUserData.userName.equals("")) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_name_title), getString(R.string.signup_dialog_name_content), 1, 2);
            return false;
        }

        // Major
        SignupUserData.userMajor = str[selectedMajorIndex];

        // Gender Empty Check
        if(!manButton.isChecked() && !womanButton.isChecked()) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_gender_title), getString(R.string.signup_dialog_gender_title), 1, 2);
            return false;
        } else {
            if(manButton.isChecked()) {
                SignupUserData.userGender = "man";
            } else if(womanButton.isChecked()) {
                SignupUserData.userGender = "woman";
            }
        }

        // Student Number
        SignupUserData.userStudentNumber = etStudentNumber.getText().toString();
        if(SignupUserData.userStudentNumber.equals("")) {
            DialogUtil.showDialog(SignupActivity.this, getString(R.string.signup_dialog_studentnumber_title), getString(R.string.signup_dialog_studentnumber_content), 1, 2);
            return false;
        }

        return true;
    }

    private void setContent() {
        tvEmail = (TextView) findViewById(R.id.tv_email);
        etEmail = (EditText) findViewById(R.id.et_email);
        tvEmail.setTypeface(TypefaceUtil.typeface_m);
        etEmail.setTypeface(TypefaceUtil.typeface);

        tvPassword = (TextView) findViewById(R.id.tv_password);
        etPassword = (EditText) findViewById(R.id.et_password);
        tvPassword.setTypeface(TypefaceUtil.typeface_m);
        etPassword.setTypeface(TypefaceUtil.typeface);

        tvPasswordConfirm = (TextView) findViewById(R.id.tv_password_confirm);
        etPasswordConfirm = (EditText) findViewById(R.id.et_password_confirm);
        tvPasswordConfirm.setTypeface(TypefaceUtil.typeface_m);
        etPasswordConfirm.setTypeface(TypefaceUtil.typeface);

        tvName = (TextView) findViewById(R.id.tv_name);
        etName = (EditText) findViewById(R.id.et_name);
        tvName.setTypeface(TypefaceUtil.typeface_m);
        etName.setTypeface(TypefaceUtil.typeface);

        tvStudentNumber = (TextView) findViewById(R.id.tv_student_number);
        etStudentNumber = (EditText) findViewById(R.id.et_student_number);
        tvStudentNumber.setTypeface(TypefaceUtil.typeface_m);
        etStudentNumber.setTypeface(TypefaceUtil.typeface);

        tvGender = (TextView) findViewById(R.id.tv_gender);
        tvGender.setTypeface(TypefaceUtil.typeface_m);

        tvMajor = (TextView) findViewById(R.id.tv_major);

        manButton = (RadioButton) findViewById(R.id.op_man);
        womanButton = (RadioButton) findViewById(R.id.op_woman);

        manButton.setTypeface(TypefaceUtil.typeface);
        womanButton.setTypeface(TypefaceUtil.typeface);

        continueButton = (Button) findViewById(R.id.continue_button);
        continueButton.setTypeface(TypefaceUtil.typeface_m);

        etPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        etPasswordConfirm.setTransformationMethod(new AsteriskPasswordTransformationMethod());
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
