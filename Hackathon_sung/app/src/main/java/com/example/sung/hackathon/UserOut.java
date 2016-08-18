package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by user1 on 2016-08-18.
 */
public class UserOut extends Activity {
    DBAdapter db;
    boolean dbOpen;
    String id;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_00);
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;
        final Button userout = (Button)findViewById(R.id.userout);
        userout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delData(MainActivity.session);
                Intent intent = new Intent(UserOut.this,LoginActivity.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(),"회원 탈퇴하였습니다.",Toast.LENGTH_LONG).show();
            }
        });


    }



}

