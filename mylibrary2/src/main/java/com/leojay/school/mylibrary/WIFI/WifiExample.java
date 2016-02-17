package com.leojay.school.mylibrary.WIFI;

import android.content.Context;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Project:com.leojay.school.mylibrary.WIFI
 * <p/>
 * Author:Crazy_LeoJay
 * Time:下午3:28
 */
public class WifiExample {

    private Context context;
    WifiAdmin wifiAdmin = new WifiAdmin(context);

    //添加一个网络
    private void addWifiWork() {
        wifiAdmin.addNetwork(wifiAdmin.createWifiConfiguration("TP", "1964061500Fch", 3));
    }

    //获得WIFI的configuration配置列表
    private void getConfiguration() {
        try {
            wifiAdmin.startScan();
            int networkId = wifiAdmin.getNetworkId();
            List<WifiConfiguration> configurations = wifiAdmin.getConfiguration();
            List<HashMap<String, String>> list = new ArrayList<>();
            HashMap<String, String> map;
            for (WifiConfiguration configuration : configurations) {
                map = new HashMap<>();
                map.put("SSID", "SSID :" + configuration.SSID);
                map.put("BSSID", "BSSID:" + configuration.BSSID);
                map.put("networkId", "networkId:" + configuration.networkId);
                map.put("preSharedKey", "preSharedKey:" + configuration.preSharedKey);
                map.put("wepKeys", "wepKeys:" + configuration.wepKeys + "");
                map.put("allowedKeyManagement", "allowedKeyManagement:" + configuration.allowedKeyManagement.toString());
                list.add(map);
            }

//            SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.wifi_list_item,
//                    new String[]{"SSID", "BSSID", "networkId", "preSharedKey", "wepKeys", "allowedKeyManagement"},
//                    new int[]{
//                            R.id.name_wifi, R.id.wifi_message_1, R.id.wifi_message_2,
//                            R.id.wifi_message_3, R.id.wifi_message_4, R.id.wifi_message_5
//                    });
//            messageListView.setAdapter(adapter);
//            testTextView.setText(networkId + "");
        } catch (Exception e) {
            Log.e("错误:", e.getMessage());
        }
    }

    //获得WIFI状态
    private void wifiState() {
        int i = wifiAdmin.checkState();
        String wifiInfo = wifiAdmin.getWifiInfo();
//        stateTextView.setText("当前状态：" + i + "\n" + wifiInfo);
    }

    //获得wifi的ScanResult信息列表
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

//        SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.wifi_list_item,
//                new String[]{"SSID", "BSSID", "capabilities", "frequency", "level", "timestamp"},
//                new int[]{
//                        R.id.name_wifi, R.id.wifi_message_1, R.id.wifi_message_2,
//                        R.id.wifi_message_3, R.id.wifi_message_4, R.id.wifi_message_5
//                });
//        messageListView.setAdapter(adapter);
    }

    //关闭WIFI
    private String closeWifi() {
        String s;
        if (wifiAdmin.closeWifi()) {
            s = "WIFI 关闭成功";
        } else {
            s = "Wifi 关闭失败";
        }
        return s;
    }

    //开启WIFI
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
