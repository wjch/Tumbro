package com.mywjch.tumbro.view.zhihu;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;

import com.mywjch.tumbro.MainActivity;
import com.mywjch.tumbro.R;

import java.io.File;

public class SplashActivity extends Activity {

    private ImageView iv_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        iv_start = (ImageView) findViewById(R.id.iv_start);
        initImage();
    }

    private void initImage() {
        File dir = getFilesDir();
        final File imagefile = new File(dir, "start.jpg");

        if (imagefile.exists()) {
            iv_start.setImageBitmap(BitmapFactory.
                    decodeFile(imagefile.getAbsolutePath()));
        } else {
            iv_start.setImageResource(R.mipmap.mm);
        }


        final ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.2f, 1.0f, 1.2f
                , Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        scaleAnimation.setFillAfter(true);
        scaleAnimation.setDuration(3000);

        scaleAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {

                startActivityto();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        iv_start.setAnimation(scaleAnimation);
    }

    private void startActivityto() {

        Intent intent = new Intent(SplashActivity.this, ZhiHuMainActivity.class);
        startActivity(intent);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        finish();
    }


}
