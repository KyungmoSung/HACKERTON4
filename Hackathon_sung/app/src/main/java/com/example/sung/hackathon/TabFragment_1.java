package com.example.sung.hackathon;

/**
 * Created by Sung on 2016-06-01.
 */

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class TabFragment_1 extends Fragment {

    List_Food_Adapter adapter;
    final ArrayList<Item_Food> datalist = new ArrayList<>();
    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;
    int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment_food, container, false);

        Item_Food food;
        food = new Item_Food("사과", 4, "2016-12-12", R.drawable.apple);
        datalist.add(food);
        food = new Item_Food("바나나", 1, "2016-12-12", R.drawable.banana);
        datalist.add(food);
        food = new Item_Food("체리", 10, "2016-12-12", R.drawable.cherry);
        datalist.add(food);
        food = new Item_Food("수박", 2, "2016-12-12", R.drawable.watermelon);
        datalist.add(food);
        food = new Item_Food("우유", 4, "2016-12-12", R.drawable.milk_carton);
        datalist.add(food);
        food = new Item_Food("당근", 3, "2016-12-12", R.drawable.carrot);
        datalist.add(food);
        food = new Item_Food("베이컨", 5, "2016-12-12", R.drawable.bacon);
        datalist.add(food);
        food = new Item_Food("포도", 1, "2016-12-12", R.drawable.grapes_green);
        datalist.add(food);
        food = new Item_Food("체리", 1, "2016-12-12", R.drawable.cherry);
        datalist.add(food);
        food = new Item_Food("옥수수", 2, "2016-12-12", R.drawable.corn_cob);
        datalist.add(food);
        food = new Item_Food("레몬", 2, "2016-12-12", R.drawable.lemon);
        datalist.add(food);
        food = new Item_Food("호박", 2, "2016-12-12", R.drawable.pumpkin);
        datalist.add(food);
        food = new Item_Food("딸기", 2, "2016-12-12", R.drawable.strawberry);
        datalist.add(food);

        GridView listview;
        adapter = new List_Food_Adapter(getActivity(), R.layout.item_list, datalist);
        listview = (GridView) rootView.findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("식재료 수정/삭제")
                        .setCancelable(false)
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                datalist.remove(position); //정보삭제
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
                                in.putExtra("cnt", Integer.toString(datalist.get(position).getCnt()));
                                in.putExtra("exp", datalist.get(position).getExpiration().toString());
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
           public void onClick(View arg0) { //다른 액티비티를 띄움
               SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);
               String str_date = df.format(new Date());
               Intent in = new Intent(getActivity(), ModifyActivity.class);
               in.putExtra("name", ""); //값을 넘겨줌
               in.putExtra("cnt", "1");
               in.putExtra("exp",str_date);
               in.putExtra("icon", Integer.toString( R.drawable.apple));
               startActivityForResult(in, 1);
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

                datalist.add(s);
                adapter.notifyDataSetChanged();
            }
        }
        if (requestCode == 2) { //기존정보 수정
            if (resultCode == RESULT_OK) {
                Item_Food s = new Item_Food(data.getExtras().getString("Edit_name"), Integer.parseInt(data.getExtras().getString("Edit_cnt")), data.getExtras().getString("Edit_exp"), Integer.parseInt(data.getExtras().getString("Edit_icon")));

                datalist.set(index, s); //index위치의 정보 수정
                adapter.notifyDataSetChanged();
            }
        }
    }
}