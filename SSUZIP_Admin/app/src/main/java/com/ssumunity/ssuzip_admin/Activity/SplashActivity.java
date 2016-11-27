package com.ssumunity.ssuzip_admin.Activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.ssumunity.ssuzip_admin.R;

public class SplashActivity extends ActionBarActivity {

    private ImageView iv_toplogo    = null;
    private ImageView iv_bottomlogo = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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

        iv_toplogo = (ImageView) findViewById(R.id.imageView);
        iv_bottomlogo = (ImageView) findViewById(R.id.imageView2);

        fadeIn(SplashActivity.this, iv_toplogo, iv_bottomlogo);
    }


    // For Marshmellow Version
    private void checkPermission() {
        final Handler handler =  new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                fadeOut(SplashActivity.this, iv_toplogo, iv_bottomlogo);
            }
        }, 2000);
    }

    private void fadeOut(Context context, final ImageView imageView, final ImageView imageView2) {
        final Animation fadeOutAnimation = AnimationUtils.loadAnimation( context, R.anim.fade_out );
        fadeOutAnimation.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                iv_toplogo.setVisibility(View.INVISIBLE);
                iv_bottomlogo.setVisibility(View.INVISIBLE);

                startActivity(new Intent(SplashActivity.this, LoginActivity.class));
                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();
            }
        });
        imageView.startAnimation( fadeOutAnimation );
        imageView2.startAnimation( fadeOutAnimation );
    }

    private void fadeIn(Context context, final ImageView imageView, final ImageView imageView2){
        final Animation fadeInAnimation = AnimationUtils.loadAnimation( context, R.anim.fade_in );
        fadeInAnimation.setAnimationListener( new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                //imageView.setImageResource( resID );
                //imageView.startAnimation( fadeInAnimation );
                checkPermission();
            }
        });
        imageView.startAnimation( fadeInAnimation );
        imageView2.startAnimation( fadeInAnimation );
    }
}
