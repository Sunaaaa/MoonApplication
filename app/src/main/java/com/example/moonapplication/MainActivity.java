package com.example.moonapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main_", "요기 들어왔어용");

        Intent i = new Intent();
        ComponentName componentName = new ComponentName("com.example.moonapplication", "com.example.moonapplication.MoonService");
        i.setComponent(componentName);
        startService(i);

        imageView = (ImageView)findViewById(R.id.moon);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String lunAge = intent.getStringExtra("result_data");

        setImgSrc(lunAge);

    }

    public void setImgSrc(String lunAge) {

        switch (lunAge){
            case "0.1": case "1.1": case "0.4": case "1.4": case "2.4":
                imageView.setImageResource(R.drawable.newmoon);
            case "3.4": case "4.4": case "5.4": case "6.4": case "7.4": case "8.4":
                imageView.setImageResource(R.drawable.crescentmoon);
            case "9.4":
                imageView.setImageResource(R.drawable.youngmoon);
            case "10.4": case "11.4": case "12.4": case "13.4": case "14.4":
                imageView.setImageResource(R.drawable.nonamemoon2);
            case "15.4":
                imageView.setImageResource(R.drawable.fullmoon);
            case "16.4": case "17.4": case "18.4": case "19.4": case "20.4": case "21.4": case "22.4":
                imageView.setImageResource(R.drawable.nonamemoon);
            case "23.4":
                imageView.setImageResource(R.drawable.oldmoon);
            case "24.4": case "25.4": case "26.4": case "27.4": case "28.4":
                imageView.setImageResource(R.drawable.darkmoon);
        }
    }

}
