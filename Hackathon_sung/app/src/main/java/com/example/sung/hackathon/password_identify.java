package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by 희연 on 2016-08-21.
 */
public class password_identify extends Activity{
    DBAdapter db;
    boolean dbOpen;

    EditText password;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_identify);
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;
        final Button iden = (Button)findViewById(R.id.iden);
        password=(EditText)findViewById(R.id.password);
        iden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String _password;
                _password=password.getText().toString();
                if(!(db.password_iden(_password)))
                {
                    Toast.makeText(getApplicationContext(),"비밀번호가 틀렸습니다.",Toast.LENGTH_SHORT).show();
                }else {
                    Intent intent = new Intent(password_identify.this,UserOut.class);
                    startActivity(intent);

                }
            }
        });


    }
}
