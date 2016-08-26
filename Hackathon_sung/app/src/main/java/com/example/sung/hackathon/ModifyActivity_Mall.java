package com.example.sung.hackathon;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.sung.hackathon.mall.Mall;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * Created by 희연 on 2016-08-18.
 */
public class ModifyActivity_Mall extends Activity {

    static Mall[] mall = new Mall[4];
    static Button[] bt = new Button[4];
    static public ProgressBar pbar;


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
                bt[0].setText("연동중");

            }
            else{

                try {
                    bt[0].setOnClickListener(listener(bt[0]));
                }
                catch (Exception e){
                    Log.d("park",e.toString());
                }

            }

        }

    }
    private static View.OnClickListener listener(View v) throws IOException {

        View.OnClickListener my_listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LayoutInflater dialog = LayoutInflater.from(v.getContext());
                final View dialogLayout = dialog.inflate(R.layout.mall_login_dialog, null);
                final Dialog addDialog = new Dialog(v.getContext());

                addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                addDialog.setContentView(dialogLayout);
                addDialog.show();
                Button login = (Button) dialogLayout.findViewById(R.id.login);
                Button cancel = (Button) dialogLayout.findViewById(R.id.cancel);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Log.d("park", "onClick:"+v.getId());
                        //Log.d("park", "onClick:"+R.id.button1);
                        switch (v.getId()) {
                            case R.id.button1:
                                mall[0].setId(((EditText) dialogLayout.findViewById(R.id.input_id)).getText().toString());
                                mall[0].setPw(((EditText) dialogLayout.findViewById(R.id.input_pw)).getText().toString());
                                break;
                            case R.id.button2:
                                break;
                            case R.id.button3:
                                break;
                            case R.id.button4:
                                break;
                        }

                        new ProcessCrollingTask(v.getContext()).execute(null, null, null);


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
class ProcessCrollingTask extends AsyncTask<Void, Void, Void> {
    private static final int ServerPort = 9010;
    private static final String ServerIP = "59.10.201.198";
    private static Socket socket;
    private static BufferedReader networkReader;
    private static BufferedWriter networkWriter;
    private static BufferedReader in;
    ProgressDialog mDlg;
    Context mContext;


    ProcessCrollingTask(Context mContext){
        this.mContext = mContext;

    }

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        TabFragment_1.adapter.notifyDataSetChanged();

    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        mDlg = new ProgressDialog(mContext);
        mDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        mDlg.setMessage("불러오는 중...");
        mDlg.show();

        Toast.makeText(mContext,"로그인 성공",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ModifyActivity_Mall.bt[0].setText("연동중");
        ModifyActivity_Mall.bt[0].setTextColor(ContextCompat.getColor(mContext,R.color.colorGreen));
        ModifyActivity_Mall.bt[0].setBackground(ContextCompat.getDrawable(mContext,R.drawable.round_button_green));
        mDlg.dismiss();
        Toast.makeText(mContext,"4개의 데이터 추가완료",Toast.LENGTH_LONG).show();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try{
            Log.d("park", "doInBackground: start");

            socket = new Socket(ServerIP, ServerPort);
            networkWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            networkReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter out = new PrintWriter(networkWriter, true);
            out.println("test");
            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream()));
            String str = in.readLine();


            for(String st : str.split(" ")) {

                //DBAdapter.addRefrigerator(MainActivity.session,st, 1, "2016-12-12", R.drawable.apple);
                TabFragment_1.datalist.add(new Item_Food(st,1,"2016-12-12",R.drawable.apple));
            }


            //Toast.makeText(params[0].get,"success",Toast.LENGTH_LONG).show();

            publishProgress();
            Log.d("park", "doInBackground: end"+str);
        }catch(Exception e){
            Log.d("park",e.toString());
            Log.d("park", "socket error");
        }
        return null;
    }
}