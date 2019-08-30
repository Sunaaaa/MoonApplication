package com.example.moonapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public class MoonService extends Service {

    private class MoonRunnable implements Runnable{
        private String solYear;
        private String solMonth;
        private String solDay;

        public MoonRunnable(String solYear, String solMonth, String solDay) {
            this.solYear = solYear;
            this.solMonth = solMonth;
            this.solDay = solDay;
        }

        @Override
        public void run() {
            // 전달된 키워드를 이용해서 네트워크 처리함 (API 호출)
            String mykey = "rcYZ2cy9xIAfe1LzhTIOwGmFTTXCp%2BGv7NZ2GZ%2FMsTYOHbmHIJarmPJkKFB%2FELz3uwhH36N%2BvzmMs1o%2F%2Bnz8og%3D%3D";
            String  url = "http://apis.data.go.kr/B090041/openapi/service/LunPhInfoService/getLunPhInfo?solYear=" + solYear + "&solMonth=" + solMonth + "&solDay=" + solDay + "&ServiceKey=" + mykey;

            try {
                // 네트워크 연결
                URL urlObj = new URL(url);
                HttpURLConnection con = (HttpURLConnection)urlObj.openConnection();

                // request 방식을 지정
                con.setRequestMethod("GET");

                // 정상적으로 설정을 하면, API 호출이 성공하고, 결과를 받아 올 수 있어요
                // 연결 통로인 Stream 을 통해서 결과를 문자열로 얻어내요.
                // 기본적인 Stream을 BufferedReader 형태로 생성 -> ReadLine으로 결과를 가져올 수 있다.
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                String line = null;
                StringBuffer sb = new StringBuffer();

                while ((line = br.readLine())!= null){
                    sb.append(line);
                }
                Log.i("Moon_Service", line);

                // 데이터를 다 읽어 들였으니 통로 (Stream)를 닫아요
                br.close();

                Log.i("Moon_Service", sb.toString());

                // Jackson Library를 이용해서 JSON 데이터를 처리
                // { document : [요기] } => 요기 데이터가 필요함
                ObjectMapper mapper = new ObjectMapper();

                // Json을 Key-Value로 { document : [요기] }를 가져옴
                Map<String, Object> map = mapper.readValue(sb.toString(), new TypeReference<Map<String, Object>>(){} );

                // [요기]를 빼옴
                Object obj = map.get("records");
                Log.i("Moon_Service", obj.toString());

                // [요기]를 다시 Json으로 변형
                String resultJsonData = mapper.writeValueAsString(obj);
                Log.i("Moon_Service", resultJsonData);

                // Json을 ArrayList 객체로 바로 뽑을 수 있다.
                MoonDTO mymoon = mapper.readValue(resultJsonData, new TypeReference<MoonDTO>() {});
                Log.i("Moon_Service", mymoon.toString());
                // 정상적으로 객체화가 되었으면, Intent에 해당 데이터를 붙여서 Activity에게 전달하게 되요.
                // Thread가 inner Class 안에 있기 때문에 getApplicationContext()를 사용할 수 있다.
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                // 전달할 데이터를 Intent에 붙이기
                i.putExtra("result_data", mymoon.getLunAge());
                Log.i("Moon_Service", mymoon.getLunAge());
                startActivity(i);

            }catch (Exception e){
                Log.i("Moon_Service", e.toString());
            }
        }
    }

    public MoonService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String year = intent.getExtras().getString("year");
        String month = intent.getExtras().getString("month");
        String day = intent.getExtras().getString("day");

        MoonRunnable moonRunnable = new MoonRunnable(year, month, day);
        Thread t = new Thread(moonRunnable);
        t.start();

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
