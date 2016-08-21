package com.example.sung.hackathon;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user1 on 2016-08-19.
 */
public class recipe_add extends Activity {

    DBAdapter db;
    boolean dbOpen;

    EditText _recipetitle,_food,_food2,_content;
    Item_Recipe new_ir;


        public void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.recipe_add);

            db = new DBAdapter(this);
            db.open();
            dbOpen = true;

        _recipetitle=(EditText) findViewById(R.id.recipetitle);
        _food=(EditText)findViewById(R.id.food);
            _food2=(EditText)findViewById(R.id.food2);
            _content=(EditText)findViewById(R.id.content);



        Button recipe_add=(Button)findViewById(R.id.recipe_add);
        recipe_add.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                String[] st = new String[1];
                st[0] = _food2.getText().toString();
                new_ir = new Item_Recipe(_recipetitle.getText().toString(),_food.getText().toString(),_content.getText().toString(),st,0);
                db.recipe(new_ir.getTitle(),new_ir.getFood()[0],new_ir.getFood()[0],new_ir.getContents());
                TabFragment_3.datalist.add(new_ir);
                TabFragment_3.adapter.notifyDataSetChanged();
                Toast.makeText(getApplicationContext(),"레시피가 추가되었습니다!",Toast.LENGTH_LONG).show();

                Intent intent = new Intent(recipe_add.this,MainActivity.class);
                startActivity(intent);

            }
        });



        }
}
