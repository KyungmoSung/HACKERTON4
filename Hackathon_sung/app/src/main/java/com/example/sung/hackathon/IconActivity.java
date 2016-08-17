package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;
import java.util.Arrays;

public class IconActivity  extends Activity {

    List_Icon_Adapter adapter;
    ArrayList<Integer> icon_list = new ArrayList<>(Arrays.asList(R.drawable.apple,
            R.drawable.asparagus,
            R.drawable.bacon,
            R.drawable.banana,
            R.drawable.beans,
            R.drawable.beer,
            R.drawable.beetroot,
            R.drawable.bell_pepper,
            R.drawable.bell_pepper_green,
            R.drawable.bell_pepper_yellow,
            R.drawable.blueberry,
            R.drawable.bread_slice,
            R.drawable.broccoli,
            R.drawable.cake,
            R.drawable.carrot,
            R.drawable.celery,
            R.drawable.cheese_slice,
            R.drawable.cherry,
            R.drawable.chicken,
            R.drawable.chili_pepper,
            R.drawable.corn_cob,
            R.drawable.crab,
            R.drawable.cranberry,
            R.drawable.cucumber,
            R.drawable.egg,
            R.drawable.eggplant,
            R.drawable.fish,
            R.drawable.grapefruit,
            R.drawable.grapes_green,
            R.drawable.grapes_red,
            R.drawable.ham,
            R.drawable.ice_cream_double_cone,
            R.drawable.kiwi2,
            R.drawable.leek,
            R.drawable.lemon,
            R.drawable.lime,
            R.drawable.lobster,
            R.drawable.milk_carton,
            R.drawable.mushrooms,
            R.drawable.onion,
            R.drawable.orange,
            R.drawable.peach,
            R.drawable.pear_yellow,
            R.drawable.pineapple,
            R.drawable.pizza,
            R.drawable.pork,
            R.drawable.potato,
            R.drawable.pumpkin,
            R.drawable.radish,
            R.drawable.raspberry,
            R.drawable.salmon_steak,
            R.drawable.sausage,
            R.drawable.shrimp,
            R.drawable.sliced_bread,
            R.drawable.soft_cream_cone,
            R.drawable.steak_raw,
            R.drawable.strawberry,
            R.drawable.sweet_potato,
            R.drawable.t_bone_steak_raw,
            R.drawable.toast,
            R.drawable.tomato,
            R.drawable.turnip,
            R.drawable.watermelon

    ));

    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;
    int index;

    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent(); //intent 불러옴

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_icon_select);

        GridView listview;
        adapter = new List_Icon_Adapter(this, R.layout.item_list, icon_list);
        listview = (GridView)findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                intent.putExtra("Edit_icon", icon_list.get(position).toString());
                setResult(RESULT_OK, intent);//결과값 저장
                finish();
            }
        });

    }
}