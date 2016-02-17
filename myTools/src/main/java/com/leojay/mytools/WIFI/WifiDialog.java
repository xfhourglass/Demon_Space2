package com.leojay.mytools.WIFI;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.leojay.mytools.R;

/**
 * Project:com.leojay.school.mylibrary
 * <p/>
 * Author:Crazy_LeoJay
 * Time:下午8:06
 */
public class WifiDialog extends Dialog implements View.OnClickListener {
    private Context context;
    private TextView titleTextView;
    private EditText SSIDEditText;
    private Spinner passwordWaySpinner;
    private Button addWifiButton;
    private Button cancalButton;
    private Button connectWifiButton;

    private String SSID = null;
    private String password = null;
    private RecyclerView recyclerView;

    public WifiDialog(Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_connect_dialog);
        this.connectWifiButton = (Button) findViewById(R.id.connectWifiButton);
        this.cancalButton = (Button) findViewById(R.id.cancalButton);
        this.addWifiButton = (Button) findViewById(R.id.addWifiButton);
        this.passwordWaySpinner = (Spinner) findViewById(R.id.passwordWaySpinner);
        this.SSIDEditText = (EditText) findViewById(R.id.SSIDEditText);
        this.titleTextView = (TextView) findViewById(R.id.titleTextView);
        FrameLayout isPsdFrame = (FrameLayout)findViewById(R.id.isPsdFrame);
        ListView isPsdList = (ListView) findViewById(R.id.isPsdListView);
        connectWifiButton.setOnClickListener(this);
        cancalButton.setOnClickListener(this);
        addWifiButton.setOnClickListener(this);

        
    }

    @Override
    public void onClick(View v) {
        SSID = SSIDEditText.getText().toString();

        int i = v.getId();
        if (i == R.id.addWifiButton) {
        } else if (i == R.id.connectWifiButton) {
        } else if (i == R.id.cancalButton) {
        } else {
        }
    }

    public interface OnClickListener{
        void addWifi(String SSID, String password);
        void connectWifiButton();
    }

}
