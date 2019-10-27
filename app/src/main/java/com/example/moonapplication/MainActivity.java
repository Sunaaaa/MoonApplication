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

        double lunAgeDouble = Math.floor(Double.parseDouble(lunAge));
        int num = (int)lunAgeDouble;
        String lunStr = String.valueOf(num);

        Log.i("split ::  ", lunAgeDouble + "");
        Log.i("split ::  ", num + "");
        Log.i("split ::  ", lunStr + "");

        setImgSrc(lunStr);

    }

    public void setImgSrc(String lunStr) {

        Toast.makeText(getApplicationContext(), lunStr, Toast.LENGTH_SHORT).show();
        switch (lunStr){
            case "3":
                imageView.setImageResource(R.drawable.m_3);
                break;
            case "4":
                imageView.setImageResource(R.drawable.m_4);
                break;
            case "5":
                imageView.setImageResource(R.drawable.m_5);
                break;
            case "6":
                imageView.setImageResource(R.drawable.m_6);
                break;
            case "7":
                imageView.setImageResource(R.drawable.m_7);
                break;
            case "8":
                imageView.setImageResource(R.drawable.m_8);
                break;
            case "9":
                imageView.setImageResource(R.drawable.m_9);
                break;
            case "10":
                imageView.setImageResource(R.drawable.m_10);
                break;
            case "11":
                imageView.setImageResource(R.drawable.m_11);
                break;
            case "12":
                imageView.setImageResource(R.drawable.m_12);
                break;
            case "13":
                imageView.setImageResource(R.drawable.m_13);
                break;
            case "14":
                imageView.setImageResource(R.drawable.m_14);
                break;
            case "15":
                imageView.setImageResource(R.drawable.m_15);
                break;
            case "16":
                imageView.setImageResource(R.drawable.m_16);
                break;
            case "17":
                imageView.setImageResource(R.drawable.m_17);
                break;
            case "18":
                imageView.setImageResource(R.drawable.m_18);
                break;
            case "19":
                imageView.setImageResource(R.drawable.m_19);
                break;
            case "20":
                imageView.setImageResource(R.drawable.m_20);
                break;
            case "21":
                imageView.setImageResource(R.drawable.m_21);
                break;
            case "22":
                imageView.setImageResource(R.drawable.m_22);
                break;
            case "23":
                imageView.setImageResource(R.drawable.m_23);
                break;
            case "24":
                imageView.setImageResource(R.drawable.m_24);
                break;
            case "25":
                imageView.setImageResource(R.drawable.m_25);
                break;
            case "26":
                imageView.setImageResource(R.drawable.m_26);
                break;
            case "27":
                imageView.setImageResource(R.drawable.m_27);
                break;
            default:
                imageView.setVisibility(View.GONE);

        }
    }

}
