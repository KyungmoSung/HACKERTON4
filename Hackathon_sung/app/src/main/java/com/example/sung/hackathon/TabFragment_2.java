package com.example.sung.hackathon;

/**
 * Created by Sung on 2016-06-01.
 */

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TabFragment_2 extends Fragment {

    List_Food_Adapter adapter;
    final ArrayList<Item_Food> datalist = new ArrayList<>();
    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;
    int index;
    DBAdapter db;
    boolean dbOpen;
    String cashID;
    Cursor currentcursor;
    SharedPreferences xx;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment_food, container, false);
        db = new DBAdapter(getContext());
        db.open();
        dbOpen = true;
        xx=getContext().getSharedPreferences("PreFer",0);
        cashID=xx.getString("ID","");

        currentcursor=db.fetchAllfreezer(cashID);
        int cnt=currentcursor.getCount();
        currentcursor.moveToFirst();
        for(int i=0;i<cnt;i++)
        {
            Item_Food food;
            food=new Item_Food(currentcursor.getString(1),currentcursor.getInt(2),currentcursor.getString(3),currentcursor.getInt(4));
            datalist.add(food);
            currentcursor.moveToNext();
        }






        GridView listview;
        adapter = new List_Food_Adapter(getActivity(), R.layout.item_list, datalist);
        listview = (GridView) rootView.findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("식재료 수정/삭제")
//                        .setMessage(datalist.get(position).getName() + " " + datalist.get(position).getNum())
                        .setCancelable(false)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String m_name;
                                m_name=datalist.get(position).getName();
                                datalist.remove(position); //정보삭제
                                db.defreezer(cashID,m_name);
                                adapter.notifyDataSetChanged();
                            }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        })
                        .setNeutralButton("수정", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent in = new Intent(getActivity(), ModifyActivity.class); //새로운 액티비티 띄움
                                in.putExtra("name", datalist.get(position).getName().toString()); //값을 넘겨줌
                                in.putExtra("exp", datalist.get(position).getExpiration().toString());
                                in.putExtra("cnt", Integer.toString(datalist.get(position).getCnt()));
                                in.putExtra("icon", Integer.toString(datalist.get(position).getIcon()));
                                index = position; //선택된 index값 저장
                                startActivityForResult(in, 2);
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            }
        });
        ImageButton add = (ImageButton)rootView.findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {

                LayoutInflater dialog = LayoutInflater.from(getActivity());
                final View dialogLayout = dialog.inflate(R.layout.add_dialog, null);
                final Dialog addDialog = new Dialog(getActivity());

                addDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                addDialog.setContentView(dialogLayout);
                addDialog.show();

                Button add_direct = (Button)dialogLayout.findViewById(R.id.add_direct_bt);
                Button add_mall = (Button)dialogLayout.findViewById(R.id.add_mall_bt);
                add_direct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent in = new Intent(getActivity(), ModifyActivity.class);
                        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
                        String str_date = df.format(new Date());
                        in.putExtra("name", ""); //값을 넘겨줌
                        in.putExtra("cnt", "1");
                        in.putExtra("exp", str_date);
                        in.putExtra("icon", Integer.toString(R.drawable.apple));
                        startActivityForResult(in, 1);
                    }
                });
                add_mall.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    Intent in = new Intent(MainActivity.this, ModifyActivity.class);
//                    startActivity(in);
                    }
                });

            }
        });

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //새로운정보 추가
            if (resultCode == RESULT_OK) {
                Item_Food s = new Item_Food(data.getExtras().getString("Edit_name"), Integer.parseInt(data.getExtras().getString("Edit_cnt")), data.getExtras().getString("Edit_exp"), Integer.parseInt(data.getExtras().getString("Edit_icon")));
                db.addfreezer(cashID,data.getExtras().getString("Edit_name"),Integer.parseInt(data.getExtras().getString("Edit_cnt")),data.getExtras().getString("Edit_exp"), Integer.parseInt(data.getExtras().getString("Edit_icon")));
                datalist.add(s);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2) { //기존정보 수정
            if (resultCode == RESULT_OK) {
                Item_Food s = new Item_Food(data.getExtras().getString("Edit_name"), Integer.parseInt(data.getExtras().getString("Edit_cnt")), data.getExtras().getString("Edit_exp"), Integer.parseInt(data.getExtras().getString("Edit_icon")));

                String m_name = datalist.get(index).getName();
                db.modifyfreezer(cashID,m_name,data.getExtras().getString("Edit_name"),Integer.parseInt(data.getExtras().getString("Edit_cnt")), data.getExtras().getString("Edit_exp"), Integer.parseInt(data.getExtras().getString("Edit_icon")));
                datalist.set(index, s);
                adapter.notifyDataSetChanged();
            }
        }
    }
}

