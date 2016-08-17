package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


/**
 * Created by SUNG on 2016-08-16.
 */
public class SignActivity extends Activity {
    DBAdapter db;
    boolean dbOpen;

    EditText _id,_password,_name,_phone,_address;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_1);

        db = new DBAdapter(this);
        db.open();
        dbOpen = true;

        _id= (EditText) findViewById(R.id.id);
        _password= (EditText) findViewById(R.id.password);
        _phone= (EditText) findViewById(R.id.phone);
        _address= (EditText) findViewById(R.id.address);
        _name= (EditText) findViewById(R.id.name);
        Button sign_ok = (Button) findViewById(R.id.sign_ok);
        sign_ok.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                String id,password,name,phone,address;
                id=_id.getText().toString();
                password=_password.getText().toString();
                name=_name.getText().toString();
                phone=_phone.getText().toString();
                address=_address.getText().toString();
                long check =db.addMember(id,password,name,phone,address);


                //Toast.makeText(getApplicationContext(),"id="+id+"pass="+password,Toast.LENGTH_SHORT).show();
               // Toast.makeText(getApplicationContext(),"["+check+"]",Toast.LENGTH_SHORT).show();
                if(check ==-1)
                {
                    Toast.makeText(getApplicationContext(),"회원가입 오류입니다",Toast.LENGTH_SHORT).show(); //패스워드 및 중복체크안함.
                }
                else {
                    Toast.makeText(getApplicationContext(),"회원가입이 완료됐습니다.",Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

    }
}
