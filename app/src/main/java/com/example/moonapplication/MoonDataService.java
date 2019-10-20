package com.example.moonapplication;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class MoonDataService extends Service {

    MoonDTO moonDTO = new MoonDTO();
    String moonData="";

    class MoonDataRunnable implements Runnable {

        private String year;
        private String month;
        private String day;

        public MoonDataRunnable(String year, String month, String day) {
            this.year = year;
            this.month = month;
            this.day = day;
        }

        @Override
        public void run() {
            moonData = getXmlMoonData(year, month, day);
            Log.i("데이터 가져오기", moonData);

            Intent i = new Intent();
            ComponentName componentName = new ComponentName("com.example.moonapplication", "com.example.moonapplication.MainActivity");
            i.setComponent(componentName);
            i.putExtra("moonResult", moonData);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

            startActivity(i);

        }
    }


    public MoonDataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String getXmlMoonData(String year, String month, String day){

        String lunAge = "";

        String myYear = URLEncoder.encode(year);
        String myMonth = URLEncoder.encode(month);
        String myDay = URLEncoder.encode(day);

        String mykey = "rcYZ2cy9xIAfe1LzhTIOwGmFTTXCp%2BGv7NZ2GZ%2FMsTYOHbmHIJarmPJkKFB%2FELz3uwhH36N%2BvzmMs1o%2F%2Bnz8og%3D%3D";
        String  queryUrl = "http://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/getLunPhInfo?solYear=" + myYear + "&solMonth=" + myMonth + "&solDay=" + myDay + "&ServiceKey=" + mykey;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String TAG;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        TAG = xmlPullParser.getName();

                        if (TAG.equals("item"));
                        else if (TAG.equals("lunAge")){
                            xmlPullParser.next();
                            lunAge = xmlPullParser.getText();
                            Log.i("lunAge : ", lunAge);
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                }

                eventType = xmlPullParser.next();
            }



        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return lunAge;
    }

    MoonDTO getXmlData(String year, String month, String day){

        MoonDTO dto = new MoonDTO();

        String myYear = URLEncoder.encode(year);
        String myMonth = URLEncoder.encode(month);
        String myDay = URLEncoder.encode(day);

        String mykey = "rcYZ2cy9xIAfe1LzhTIOwGmFTTXCp%2BGv7NZ2GZ%2FMsTYOHbmHIJarmPJkKFB%2FELz3uwhH36N%2BvzmMs1o%2F%2Bnz8og%3D%3D";
        String  queryUrl = "http://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/getLunPhInfo?solYear=" + myYear + "&solMonth=" + myMonth + "&solDay=" + myDay + "&ServiceKey=" + mykey;

        try {
            URL url = new URL(queryUrl);
            InputStream inputStream = url.openStream();

            XmlPullParserFactory xmlPullParserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = xmlPullParserFactory.newPullParser();
            xmlPullParser.setInput(new InputStreamReader(inputStream, "UTF-8"));

            String TAG;

            xmlPullParser.next();
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        TAG = xmlPullParser.getName();

                        if (TAG.equals("item"));
                        else if (TAG.equals("lunAge")){
                            xmlPullParser.next();
                            dto.setLunAge(xmlPullParser.getText());
                            Log.i("lunAge : ", dto.getLunAge());
                        } else if (TAG.equals("solDay")){
                            xmlPullParser.next();
                            dto.setLunAge(xmlPullParser.getText());
                            Log.i("solDay : ", dto.getSolDay());
                            Log.i("solDay : ", xmlPullParser.getText());
                        } else if (TAG.equals("solMonth")){
                            xmlPullParser.next();
                            dto.setLunAge(xmlPullParser.getText());
                            Log.i("solMonth : ", dto.getSolMonth());
                        } else if (TAG.equals("solWeek")){
                            xmlPullParser.next();
                            dto.setLunAge(xmlPullParser.getText());
                            Log.i("solWeek : ", dto.getSolWeek());
                        } else if (TAG.equals("solYear")) {
                            xmlPullParser.next();
                            dto.setLunAge(xmlPullParser.getText());
                            Log.i("solYear : ", dto.getSolYear());
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        break;
                }

                eventType = xmlPullParser.next();
            }



        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }
        return dto;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        moonDTO = new MoonDTO();
        String year = intent.getStringExtra("year");
        String month = intent.getStringExtra("month");
        String day = intent.getStringExtra("day");

        MoonDataRunnable moonDataRunnable = new MoonDataRunnable(year, month, day);
        Thread thread = new Thread(moonDataRunnable);
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
