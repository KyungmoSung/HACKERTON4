package com.example.sung.hackathon;

import android.app.Activity;
import android.os.Bundle;

/**
 * Created by 희연 on 2016-08-18.
 */
public class ModifyActivity_Mall extends Activity {

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
                Button login = (Button)dialogLayout.findViewById(R.id.login);
                Button cancel = (Button)dialogLayout.findViewById(R.id.cancel);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        //Log.d("park", "onClick:"+v.getId());
                        //Log.d("park", "onClick:"+R.id.button1);
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

                        new ProcessCrollingTask().execute(null,null,null);


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

    }
}
class ProcessCrollingTask extends AsyncTask<Void, Void, Void> {
    private static final int ServerPort = 9010;
    private static final String ServerIP = "59.10.201.198";
    private static Socket socket;
    private static BufferedReader networkReader;
    private static BufferedWriter networkWriter;
    private static BufferedReader in;

    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
        TabFragment_1.adapter.notifyDataSetChanged();

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
