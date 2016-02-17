package com.leojay.school.myschoolforrenai.function;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.leojay.school.myschoolforrenai.WebSever;

import org.ksoap2.serialization.SoapObject;

/**
 * Project:com.leojay.school.myschoolforrenai.function
 * <p/>
 * Author:Crazy_LeoJay
 * Time:上午11:33
 */
public class Attendance {

    private Context context;

    private String name;
    private int absenceTimes; //缺勤
    private int lateTimes;  //迟到
    private int personalLeaveTimes; //事假
    private int sickLeaveTimes; //病假

    public Attendance(Context context) {
        this.context = context;
    }

    private void getDate(){
        WebSever ws = new WebSever(context);
        ws.onAttendance(new WebSever.RWebMessage() {
            @Override
            public void onArguments(SoapObject object) {
                String data = object.getPrimitivePropertyAsString("dmlist");
                Bundle b = new Bundle();
                b.putString("data", data);
                Message message = new Message();
                message.setData(b);
                Attendance.this.handler.handleMessage(message);
            }
        });
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String data = msg.getData().getString("data", null);
        }
    };

}
