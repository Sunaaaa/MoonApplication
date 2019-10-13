package com.example.moonapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ImageView mImageView = (ImageView) findViewById(R.id.loading_img);
        Glide.with(this).load(R.raw.moon_splash).into(mImageView);

        Handler mHandler = new Handler();
        mHandler.postDelayed(new SplashHandler(), 5000);
        Log.i("OpeningActivity", "OpeningActivity");

    }

    private class SplashHandler implements Runnable {

        @Override
        public void run() {
            SplashActivity.this.finish();


            // 서비스 실행
//            ComponentName sComponentName = new ComponentName("com.example.myapplication", "com.example.myapplication.BodyShopService");
//            i.setComponent(sComponentName);
//            startService(i);

//            ComponentName sComponentName = new ComponentName("com.example.myapplication", "com.example.myapplication.BodyShopService");
            startActivity(new Intent(getApplicationContext(), MainActivity.class));
            SplashActivity.this.finish();

        }
    }

    @Override
    public void onBackPressed() {
        //초반 플래시 화면에서 넘어갈때 뒤로가기 버튼 못누르게 함
    }
}
