package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by 희연 on 2016-08-19.
 */
public class recipe_detail extends Activity {
    String img;
    ImageView img_view;

    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent(); //intent 불러옴
        final TextView title, sub_title, contents;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_detail);

        title = (TextView) findViewById(R.id.title);
        title.setText(intent.getStringExtra("title"));

        sub_title = (TextView) findViewById(R.id.sub_title);
        sub_title.setText(intent.getStringExtra("sub_title"));

        contents = (TextView) findViewById(R.id.contents);
        contents.setText(intent.getStringExtra("contents"));

        img_view = (ImageView) findViewById(R.id.img);
        img = intent.getStringExtra("img");
        img_view.setImageResource(Integer.parseInt(img));

        Button button_cancel = (Button) findViewById(R.id.cancel_bt);
        button_cancel.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}