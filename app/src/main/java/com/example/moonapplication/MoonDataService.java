package com.example.moonapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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

    class MoonDataRunnable implements Runnable {

        @Override
        public void run() {

        }
    }


    public MoonDataService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    String getXmlData(String year, String month, String day){
        StringBuffer stringBuffer = new StringBuffer();

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
            MoonDTO moonDTO = new MoonDTO();

            while (eventType != XmlPullParser.END_DOCUMENT){
                switch (eventType){
                    case XmlPullParser.START_TAG:
                        TAG = xmlPullParser.getName();

                        if (TAG.equals("item"));
                        else if (TAG.equals("lunAge")){
                            xmlPullParser.next();
                            moonDTO.setLunAge(xmlPullParser.getText());
                        } else if (TAG.equals("solDay")){
                            xmlPullParser.next();
                            moonDTO.setLunAge(xmlPullParser.getText());
                        } else if (TAG.equals("solMonth")){
                            xmlPullParser.next();
                            moonDTO.setLunAge(xmlPullParser.getText());
                        } else if (TAG.equals("solWeek")){
                            xmlPullParser.next();
                            moonDTO.setLunAge(xmlPullParser.getText());
                        } else if (TAG.equals("solYear")) {
                            xmlPullParser.next();
                            moonDTO.setLunAge(xmlPullParser.getText());
                        }
                        break;

                    case XmlPullParser.TEXT:
                        break;

                    case XmlPullParser.END_TAG:
                        TAG = xmlPullParser.getName();

                        if (TAG.equals("item")){

                        }
                }

                eventType = xmlPullParser.next();
            }



        } catch (IOException | XmlPullParserException e) {
            e.printStackTrace();
        }


        return "";

    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        MoonDataRunnable moonDataRunnable = new MoonDataRunnable();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
