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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TabFragment_3 extends Fragment {

    List_Recipe_Adapter adapter;
    final ArrayList<Item_Recipe> datalist = new ArrayList<>();
    private static final int RESULT_OK = -1;
    private static final int RESULT_CANCELED = 0;
    int index;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.tab_fragment_recipe, container, false);

        Item_Recipe recipe;
        recipe = new Item_Recipe("육해공 백짬뽕","일본식 잔폰은 중국인이 많이 살던 나가사키 지방에서 발전한 요리로 바로 이것이 백짬뽕의 기원이다.","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe1);
        datalist.add(recipe);
        recipe = new Item_Recipe("소시지채소볶음","소시지와 채소를 케찹에 볶아 먹던 “쏘야”는 가끔씩 생각나는 추억 속의 단골 술안주이다.","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe2);
        datalist.add(recipe);
        recipe = new Item_Recipe("연어만능장 주먹밥","부드러운 맛, 매콤한 맛, 담백한 맛… 알래스카연어 하나로 다양한 맛의 쌈장을 만들어 동그란 밥 위에 올린다. ","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe3);
        datalist.add(recipe);
        recipe = new Item_Recipe("육해공 백짬뽕","일본식 잔폰은 중국인이 많이 살던 나가사키 지방에서 발전한 요리로 바로 이것이 백짬뽕의 기원이다.","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe1);
        datalist.add(recipe);
        recipe = new Item_Recipe("소시지채소볶음","소시지와 채소를 케찹에 볶아 먹던 “쏘야”는 가끔씩 생각나는 추억 속의 단골 술안주이다.","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe2);
        datalist.add(recipe);
        recipe = new Item_Recipe("연어만능장 주먹밥","부드러운 맛, 매콤한 맛, 담백한 맛… 알래스카연어 하나로 다양한 맛의 쌈장을 만들어 동그란 밥 위에 올린다. ","요리요리",new String[]{"김치","고추장","파","고기"},R.drawable.recipe3);
        datalist.add(recipe);

        ListView listview;
        adapter = new List_Recipe_Adapter(getActivity(), R.layout.recipe_list, datalist);
        listview = (ListView) rootView.findViewById(R.id.recipe_listview);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, final int position, long id) {
                //
                Intent intent =  new Intent(getActivity(),recipe_detail.class);
                startActivity(intent);

            }
        });
        ImageButton add_bt = (ImageButton)rootView.findViewById(R.id.recipe_bt);
        add_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),recipe_add.class);
                startActivity(intent);

            }
        });
        return rootView;

    }




}
