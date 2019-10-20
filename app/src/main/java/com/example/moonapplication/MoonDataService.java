package com.example.moonapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

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
