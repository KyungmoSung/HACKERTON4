package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

/**
 * Created by SUNG on 2016-06-06.
 */
public class ModifyActivity extends Activity {

    List_Icon_Adapter adapter;
    String icon;
    ImageView icon_view;
    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent(); //intent 불러옴
        final EditText edit_name;
        final EditText edit_exp;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.modify_food);

        edit_name = (EditText) findViewById(R.id.edit_name);
        edit_name.setText(intent.getStringExtra("name"));

        edit_exp = (EditText) findViewById(R.id.edit_exp);
        edit_exp.setText(intent.getStringExtra("exp"));

        icon_view = (ImageView) findViewById(R.id.icon_view);
        icon = intent.getStringExtra("icon");
        icon_view.setImageResource(Integer.parseInt(icon));

        String cnt = intent.getStringExtra("cnt");



        Spinner s = (Spinner) findViewById(R.id.edit_cnt);
        s.setSelection(Integer.parseInt(cnt)-1);
        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id) {
                intent.putExtra("Edit_cnt", parent.getItemAtPosition(position).toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        Button icon_bt = (Button) findViewById(R.id.edit_icon);
        icon_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(ModifyActivity.this, IconActivity.class);
                startActivityForResult(in, 1);
            }
        });
        Button button_ok = (Button) findViewById(R.id.save);
        button_ok.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //수정한 정보를 넘겨줌
                intent.putExtra("Edit_name", edit_name.getText().toString());
                intent.putExtra("Edit_exp", edit_exp.getText().toString());
                intent.putExtra("Edit_icon", icon);

                setResult(RESULT_OK, intent);//결과값 저장
                finish();
            }
        });
        Button button_cancel = (Button) findViewById(R.id.cancel);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }
//
//    @Override
//    public void onResume() {
//        super.onResume();
//        adapter.notifyDataSetChanged();
//    }
//
//    @Override
//    public void onPause() {
//        super.onPause();
//    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) { //새로운정보 추가
            if (resultCode == RESULT_OK) {
                icon_view.setImageResource(Integer.parseInt(data.getExtras().getString("Edit_icon")));
                icon = data.getExtras().getString("Edit_icon");
            }
        }

    }
}
