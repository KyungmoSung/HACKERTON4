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
public class LoginActivity extends Activity {
    DBAdapter db;
    boolean dbOpen;

    EditText id,password;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=(EditText)findViewById(R.id.ID);
        password=(EditText)findViewById(R.id.password);
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;
        Button login_bt = (Button)findViewById(R.id.login);
        Button sign_bt = (Button)findViewById(R.id.sign);

        login_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String _id,_password,OK;
                _id=id.getText().toString();
                _password=password.getText().toString();
                OK=db.login(_id,_password);
                if(OK.equals("false"))
                {
                    Toast.makeText(getApplicationContext(),"로그인 오류입니다",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    intent.putExtra("cashID",OK);
                    MainActivity.session=_id;
                    startActivity(intent);
                }
            }
        });
        sign_bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignActivity.class);
                startActivity(intent);
            }
        });
    }



}


