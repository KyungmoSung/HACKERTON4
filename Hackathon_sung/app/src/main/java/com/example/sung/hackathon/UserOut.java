package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by user1 on 2016-08-18.
 */
public class UserOut extends Activity {
    DBAdapter db;
    boolean dbOpen;
    static public String password,name,phone,address;
    static public String id,password_mo,name_mo,phone_mo,address_mo;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_00);
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;

        User us = db.change_iden(id,name,phone,address);

        final EditText id = (EditText)findViewById(R.id.id);
        final EditText password = (EditText)findViewById(R.id.password);
        final EditText name = (EditText)findViewById(R.id.name);
        final EditText phone = (EditText)findViewById(R.id.phone);
        final EditText address = (EditText)findViewById(R.id.address);

        id.setText(us.getId());

        name.setText(us.getName());
        phone.setText(us.getPhone());
        address.setText(us.getAddress());

        Button user_modi = (Button)findViewById(R.id.user_modi);
        user_modi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                password_mo = password.getText().toString();
                name_mo = name.getText().toString();
                phone_mo = phone.getText().toString();
                address_mo = address.getText().toString();

                db.Update(password_mo,name_mo,phone_mo,address_mo);
                Toast.makeText(getApplicationContext(),"수정하였습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserOut.this,MainActivity.class);
                startActivity(intent);

            }
        });

        final Button userout = (Button)findViewById(R.id.iden);
        userout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.delData(MainActivity.session);
                Toast.makeText(getApplicationContext(),"회원 탈퇴하였습니다.",Toast.LENGTH_LONG).show();
                Intent intent = new Intent(UserOut.this,LoginActivity.class);
                startActivity(intent);
            }
        });


    }
}

