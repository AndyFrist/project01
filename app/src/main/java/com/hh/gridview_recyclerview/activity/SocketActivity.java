package com.hh.gridview_recyclerview.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.hh.gridview_recyclerview.R;
import com.hh.gridview_recyclerview.utils.ToastUtil;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

//极客学院
public class SocketActivity extends BaseActivity implements View.OnClickListener {

    private EditText ip;
    private EditText edittext;
    private Button connect;
    private TextView text;
    private Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socket);
        ip = (EditText) findViewById(R.id.ip);
        edittext = (EditText) findViewById(R.id.edittext);
        text = (TextView) findViewById(R.id.text);
        connect = (Button) findViewById(R.id.connect);
        connect.setOnClickListener(this);
        send = (Button) findViewById(R.id.send);
        send.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        if (v == connect) {
            connect();
        }

        if (v == send) {
            send();

        }
    }

    Socket socket = null;
    BufferedWriter writer = null;
    BufferedReader reader = null;


    private void connect() {
        AsyncTask<Void, String, Void> read = new AsyncTask<Void, String, Void>() {

            @Override
            protected Void doInBackground(Void... voids) {

                try {
                    socket = new Socket(ip.getText().toString(), 12345);
                    writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                    reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    publishProgress("success");
                } catch (IOException e) {
                    e.printStackTrace();
                    publishProgress("fail");
                }


                try {
                    String line = null;
                    while ((line = reader.readLine()) != null) {
                        publishProgress(line);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("success")) {
                    ToastUtil.showToast("链接成功");
                } else if (values[0].equals("fail")){
                    ToastUtil.showToast("链接失败");
                }
                text.append(values[0]);
                super.onProgressUpdate(values);
            }
        };
        read.execute();

    }

    private void send() {

        AsyncTask<Void,String,Void> send = new AsyncTask<Void, String, Void>() {
            @Override
            protected Void doInBackground(Void... voids) {
                try {
                    writer.write(edittext.getText().toString() + "\n");
                    writer.flush();
                    publishProgress("success");
                } catch (IOException e) {
                    e.printStackTrace();
                    publishProgress("fail");
                }
                return null;
            }

            @Override
            protected void onProgressUpdate(String... values) {
                if (values[0].equals("fail")) {
                    ToastUtil.showToast("发送失败");
                } else if ("success".equals(values[0])) {
                    ToastUtil.showToast("已发送");
                }
                super.onProgressUpdate(values);
            }
        };


        send.execute();



        edittext.setText("");

    }

}
