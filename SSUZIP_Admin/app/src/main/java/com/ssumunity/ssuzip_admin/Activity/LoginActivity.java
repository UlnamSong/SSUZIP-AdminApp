package com.ssumunity.ssuzip_admin.Activity;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.ssumunity.ssuzip_admin.Model.AsteriskPasswordTransformationMethod;
import com.ssumunity.ssuzip_admin.Model.DialogUtil;
import com.ssumunity.ssuzip_admin.R;
import com.ssumunity.ssuzip_admin.Model.TypefaceUtil;

import static com.ssumunity.ssuzip_admin.Model.DialogUtil.activity;

public class LoginActivity extends AppCompatActivity {

    private Button   loginBtn    = null;
    private TextView tvEmail     = null;
    private TextView tvPassword  = null;
    private TextView tvSignup    = null;
    private TextView tvCopyRight = null;
    private EditText etEmail     = null;
    private EditText etPassword  = null;

    private String   tempString  = "";
    private String   passString  = "";

    private String   TAG         = "LoginActivity: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TypefaceUtil.loadTypeface(LoginActivity.this);
        setContentView(R.layout.activity_login);

        setContent();

        etPassword.setTransformationMethod(new AsteriskPasswordTransformationMethod());
        tvSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: tvSignup clicked");
                startActivity(new Intent(LoginActivity.this, SignupActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: loginBtn clicked");

                // Email Empty Check
                if(etEmail.getText().toString().equals("")) {
                    DialogUtil.showDialog(LoginActivity.this, getString(R.string.login_dialog_email_input_title), getString(R.string.login_dialog_email_input_content), 1, 1);
                // Password Empty Check
                } else if(etPassword.getText().toString().equals("")) {
                    DialogUtil.showDialog(LoginActivity.this, getString(R.string.login_dialog_password_input_title), getString(R.string.login_dialog_password_input_content), 1, 1);
                // Not Empty
                } else {
                    //Email Format Check
                    if (isValidEmail(etEmail.getText())) {
                        //Temporary Code
                        //DialogUtil.showDialog(LoginActivity.this, "로그인 버튼 눌림", "로그인 버튼이 눌렸습니다.", 1, 1);
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        overridePendingTransition(R.anim.fade, R.anim.hold);
                        finish();

                        // Request Login Authentication to Server

                    } else {
                        DialogUtil.showDialog(LoginActivity.this, getString(R.string.login_dialog_email_format_title), getString(R.string.login_dialog_email_format_content), 1, 1);
                    }
                }
            }
        });
    }

    // Email Format Validation Code
    public final static boolean isValidEmail(CharSequence target) {
        return !TextUtils.isEmpty(target) && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    private void setContent() {
        loginBtn    = (Button)   findViewById(R.id.login_button);
        tvEmail     = (TextView) findViewById(R.id.tv_email);
        tvPassword  = (TextView) findViewById(R.id.tv_password);
        tvSignup    = (TextView) findViewById(R.id.tv_signup_click);
        tvCopyRight = (TextView) findViewById(R.id.tv_copyright);
        etEmail     = (EditText) findViewById(R.id.et_email);
        etPassword  = (EditText) findViewById(R.id.et_password);

        etEmail.setPrivateImeOptions("defaultInputmode=english;");

        loginBtn.setTypeface(TypefaceUtil.typeface_m);
        tvEmail.setTypeface(TypefaceUtil.typeface_m);
        tvPassword.setTypeface(TypefaceUtil.typeface_m);
        tvSignup.setTypeface(TypefaceUtil.typeface);
        tvCopyRight.setTypeface(TypefaceUtil.typeface_lb);

        etEmail.setTypeface(TypefaceUtil.typeface);
        etPassword.setTypeface(TypefaceUtil.typeface);

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
        DialogUtil.showDialog(LoginActivity.this, getString(R.string.dialog_appexit_title), getString(R.string.dialog_appexit_content), 2, 1);
    }
}
