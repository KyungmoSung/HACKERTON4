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
    int idcheck;
    EditText _id,_password,_name,_phone,_address,_password2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_1);
        idcheck=2;
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;

        _id= (EditText) findViewById(R.id.id);
        _password= (EditText) findViewById(R.id.password);
        _password2=(EditText)findViewById(R.id.password2);
        _phone= (EditText) findViewById(R.id.phone);
        _address= (EditText) findViewById(R.id.address);
        _name= (EditText) findViewById(R.id.name);
        Button sign_ok = (Button) findViewById(R.id.sign_ok);
        Button idcheckbtn =(Button)findViewById(R.id.idcheckbtn);
        idcheckbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                idcheck=db.idcheck(_id.getText().toString());
                if(idcheck ==1)
                {
                    Toast.makeText(getApplicationContext(), "사용하실수 있는 ID 입니다.", Toast.LENGTH_SHORT).show();
                }
                else if(idcheck==0)
                {
                    Toast.makeText(getApplicationContext(), "사용하실수 없는 ID 입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        sign_ok.setOnClickListener(new View.OnClickListener() {
            @Override



            public void onClick(View v) {
                String id,password,name,phone,address,password2;
                id=_id.getText().toString();
                password=_password.getText().toString();
                name=_name.getText().toString();
                phone=_phone.getText().toString();
                address=_address.getText().toString();
                password2=_password2.getText().toString();


                if(!password.equals(password2) || password.length()<6)
                {
                    Toast.makeText(getApplicationContext(),"패스워드 확인을 제대로 입력하십시오",Toast.LENGTH_SHORT).show();

                }
                else if(name.equals("") ||phone.equals("") || address.equals(""))
                {
                    Toast.makeText(getApplicationContext(),"회원정보란을 제대로 입력하십시오.",Toast.LENGTH_SHORT).show();
                }
                else{




                    if(idcheck ==2){
                        Toast.makeText(getApplicationContext(),"아이디 중복체크를 해주세요",Toast.LENGTH_SHORT).show();
                    }
                    else if(idcheck==0)
                    {
                        Toast.makeText(getApplicationContext(), "아이디 중복체크를 해주세요. ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        if(idcheck ==1) {
                            long check =db.addMember(id,password,name,phone,address);
                            if (check == -1) {
                                Toast.makeText(getApplicationContext(), "회원가입 오류입니다", Toast.LENGTH_SHORT).show(); //패스워드 및 중복체크안함.
                            } else {
                                Toast.makeText(getApplicationContext(), "회원가입이 완료됐습니다.", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(SignActivity.this, LoginActivity.class);
                                dbOpen = false;
                                startActivity(intent);
                            }
                        }

                    }

                }}
        });

    }
}
