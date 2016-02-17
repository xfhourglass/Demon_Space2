package com.leojay.demon_space2;

import android.net.wifi.ScanResult;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.leojay.mytools.MyDialog;
import com.leojay.mytools.WIFI.WifiAdmin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class WifiActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView messageListView;
    private TextView openTextView;
    private Button openWifiButton;
    private TextView closeTextView;
    private Button closeWifiButton;
    private TextView searchTextView;
    private Button searchWifiButton;
    private TextView stateTextView;
    private Button wifiStateButton;

    private WifiAdmin wifiAdmin;
    private Button openDialogButton;
    private Button testButton;
    private TextView testTextView;
    private Button test2Button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wifi);
        this.test2Button = (Button) findViewById(R.id.test2Button);
        this.testTextView = (TextView) findViewById(R.id.testTextView);
        this.testButton = (Button) findViewById(R.id.testButton);
        this.openDialogButton = (Button) findViewById(R.id.openDialogButton);
        this.wifiStateButton = (Button) findViewById(R.id.wifiStateButton);
        this.stateTextView = (TextView) findViewById(R.id.stateTextView);
        this.searchWifiButton = (Button) findViewById(R.id.searchWifiButton);
        this.searchTextView = (TextView) findViewById(R.id.searchTextView);
        this.closeWifiButton = (Button) findViewById(R.id.closeWifiButton);
        this.closeTextView = (TextView) findViewById(R.id.closeTextView);
        this.openWifiButton = (Button) findViewById(R.id.openWifiButton);
        this.openTextView = (TextView) findViewById(R.id.openTextView);
        this.messageListView = (ListView) findViewById(R.id.messageListView);
        openWifiButton.setOnClickListener(this);
        closeWifiButton.setOnClickListener(this);
        searchWifiButton.setOnClickListener(this);
        wifiStateButton.setOnClickListener(this);
        openDialogButton.setOnClickListener(this);
        testButton.setOnClickListener(this);
        test2Button.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String s = null;
        wifiAdmin = new WifiAdmin(this);
        switch (v.getId()) {
            case R.id.openWifiButton:
                s = openWifi();
                openTextView.setText(s);
                break;
            case R.id.closeWifiButton:
                s = closeWifi();
                closeTextView.setText(s);
                break;
            case R.id.searchWifiButton:
                searchWifi();
                break;
            case R.id.wifiStateButton:
                wifiState();
                break;
            case R.id.openDialogButton:
                MyDialog dialog = new MyDialog(this);
                dialog.show();
                break;
            case R.id.testButton:
                break;
            case R.id.test2Button:
                break;
            default:
                break;
        }
    }

    private void wifiState() {
        int i = wifiAdmin.checkState();
        String wifiInfo = wifiAdmin.getWifiInfo();
        stateTextView.setText("当前状态：" + i + "\n" + wifiInfo);
    }

    private void searchWifi() {
        wifiAdmin.startScan();
        List<ScanResult> wifiList = wifiAdmin.getWifiList();
        List<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> map;
        for (ScanResult result : wifiList) {
            map = new HashMap<>();
            map.put("SSID", "SSID :" + result.SSID);
            map.put("BSSID", "BSSID:" + result.BSSID);
            map.put("capabilities", "capabilities:" + result.capabilities);
            map.put("frequency", "frequency:" + result.frequency + "");
            map.put("level", "level:" + result.level + "");
            map.put("timestamp", "timestamp:" + result.timestamp + "");
            list.add(map);
        }

        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.wifi_list_item,
                new String[]{"SSID", "BSSID", "capabilities", "frequency", "level", "timestamp"},
                new int[]{
                        R.id.name_wifi, R.id.wifi_message_1, R.id.wifi_message_2,
                        R.id.wifi_message_3, R.id.wifi_message_4, R.id.wifi_message_5
                });
        messageListView.setAdapter(adapter);
    }

    private String closeWifi() {
        String s;
        if (wifiAdmin.closeWifi()) {
            s = "WIFI 关闭成功";
        } else {
            s = "Wifi 关闭失败";
        }
        return s;
    }

    private String openWifi() {
        String s = null;
        if (wifiAdmin.openWifi()) {
            s = "wifi启动成功";
        } else {
            s = "wifi已启动或者启动失败";
        }
        return s;
    }


}
