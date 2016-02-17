package com.leojay.mytools;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Project:com.leojay.school.mylibrary
 * <p/>
 * Author:Crazy_LeoJay
 * Time:下午1:08
 */
public class MyDialog extends Dialog implements View.OnClickListener {
    private Toolbar view;
    private Button closeDialogButton;

    private onClickCloseListener closeListener;
    private EditText ssidEdit;
    private EditText passwordEdit;

    public MyDialog(Context context) {
        super(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.wifi_dialog);
        this.passwordEdit = (EditText) findViewById(R.id.passwordEdit);
        this.ssidEdit = (EditText) findViewById(R.id.ssidEdit);
        this.closeDialogButton = (Button) findViewById(R.id.closeDialogButton);
        this.view = (Toolbar) findViewById(R.id.view);
        closeDialogButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String ssid = ssidEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        try {
            if (!ssid.equals("") && !password.equals("")) {
                closeListener.onClose(ssid, password);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        MyDialog.this.dismiss();
    }

    public interface onClickCloseListener {
        void onClose(String SSID, String password);
    }

    public void setOnCLickCLoseListener(onClickCloseListener closeListener) {
        this.closeListener = closeListener;
    }
}
