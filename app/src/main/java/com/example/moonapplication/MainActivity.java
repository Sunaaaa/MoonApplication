package com.example.moonapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.sql.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i("Main_", "요기 들어왔어용");

        // 현재 날짜
        long now = System.currentTimeMillis();
        Date date = new Date(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        String today = sdf.format(date);
        String[] today_data = today.split("-");

        Intent i = new Intent();
        ComponentName componentName = new ComponentName("com.example.moonapplication", "com.example.moonapplication.MoonDataService");
        i.putExtra("year", today_data[0]);
        Log.i("year", today_data[0]);

        i.putExtra("month", today_data[1]);
        Log.i("month", today_data[1]);

        i.putExtra("day", today_data[2]);
        Log.i("v", today_data[2]);

        i.setComponent(componentName);
        startService(i);

        imageView = (ImageView)findViewById(R.id.moon);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        MoonDTO moonDTO = intent.getParcelableExtra("moonResult");
        String lunAge = intent.getStringExtra("moonResult");
//        Toast.makeText(getApplicationContext(), moonDTO.getLunAge(), Toast.LENGTH_SHORT).show();

        Log.i("result : ", lunAge);

        setImgSrc(lunAge);

    }

    public void setImgSrc(String lunAge) {

        switch (lunAge){
            case "0.1": case "1.1": case "0.4": case "1.4": case "2.4":
                imageView.setImageResource(R.drawable.darkmoon);
            case "3.4": case "4.4": case "5.4": case "6.4": case "7.4": case "8.4":
                imageView.setImageResource(R.drawable.crecentmoon);
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
            case "24.4": case "25.4": case "26.4" : case "26.7": case "27.4": case "28.4":
                imageView.setImageResource(R.drawable.darkmoon);
        }
    }

}
