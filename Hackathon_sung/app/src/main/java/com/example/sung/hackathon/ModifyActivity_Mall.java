package com.example.sung.hackathon;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.example.sung.hackathon.mall.Croll;
import com.example.sung.hackathon.mall.Mall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

/**
 * Created by 희연 on 2016-08-18.
 */
public class ModifyActivity_Mall extends Activity {


    static final int NMALL = 4;
    private static final int ServerPort = 9010;
    private static final String ServerIP = "pmw.iptime.org";
    private static Socket socket;
    private static BufferedReader networkReader;
    private static BufferedWriter networkWriter;


    Button bt[] = new Button[NMALL];
    static Mall mall[] = new Mall[NMALL] ;//= new Mall[NMALL];
    //private static Croll croll;



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_mall);
        mall[0] = new Mall();
        mall[1] = new Mall();
        mall[2] = new Mall();
        mall[3] = new Mall();
        bt[0] = (Button)findViewById(R.id.button1);
        for(Mall i : mall){
            if(i.isSync()) {
                bt[0].setText("동기중");

            }
            else{


                bt[0].setOnClickListener(listener(bt[0]));
            }

        }

    }
    private static View.OnClickListener listener(View v){

        View.OnClickListener my_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialog = LayoutInflater.from(v.getContext());
                final View dialogLayout = dialog.inflate(R.layout.mall_login_dialog, null);
                final Dialog addDialog = new Dialog(v.getContext());

                addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                addDialog.setContentView(dialogLayout);
                addDialog.show();
                Button login = (Button)dialogLayout.findViewById(R.id.login);
                Button cancel = (Button)dialogLayout.findViewById(R.id.cancel);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        switch(v.getId()){
                            case R.id.button1:
                                mall[0].setId(((EditText)dialogLayout.findViewById(R.id.input_id)).getText().toString());
                                mall[0].setPw(((EditText)dialogLayout.findViewById(R.id.input_pw)).getText().toString());
                                break;
                            case R.id.button2:
                                break;
                            case R.id.button3:
                                break;
                            case R.id.button4:
                                break;
                        }

                        try{
                            socket = new Socket(ServerIP, ServerPort);
                            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                            PrintWriter out = new PrintWriter(networkWriter, true);
                            out.println("test");
                        }catch(Exception e){

                        }

                        addDialog.cancel();
                    }
                });
                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        addDialog.cancel();
                    }
                });
            }
        };




        return my_listener;
    }
}
