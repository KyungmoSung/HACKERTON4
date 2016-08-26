package com.example.sung.hackathon;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.graphics.Bitmap;
import android.provider.MediaStore;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;


/**
 * Created by user1 on 2016-08-19.
 */
public class recipe_add extends Activity {
    final int REQ_CODE_SELECT_IMAGE=100;

    public void onCreate(Bundle savedInstanceState) {
        final Intent intent = getIntent(); //intent 불러옴
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recipe_add);
        final ImageButton recipe_img = (ImageButton)findViewById(R.id.recipe_img_bt);
        final EditText recipe_title = (EditText)findViewById(R.id.recipe_title);
        final EditText recipe_sub_title = (EditText)findViewById(R.id.recipe_sub_title);
        final  EditText recipe_food = (EditText)findViewById(R.id.food);
        final  EditText recipe_food_etc = (EditText)findViewById(R.id.food_etc);
        final  EditText recipe_contents = (EditText)findViewById(R.id.recipe_contents);

        recipe_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });
        ImageButton ok = (ImageButton)findViewById(R.id.bt_ok);
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                intent.putExtra("Title", recipe_title.getText().toString());
                intent.putExtra("SubTitle", recipe_sub_title.getText().toString());
                intent.putExtra("Food", recipe_food.getText().toString());
                intent.putExtra("Food_Etc", recipe_food_etc.getText().toString());
                intent.putExtra("Contents", recipe_contents.getText().toString());
                setResult(RESULT_OK, intent);//결과값 저장
                finish();
            }
        });
        Button cancel = (Button)findViewById(R.id.cancel_bt);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        Toast.makeText(getBaseContext(), "resultCode : "+resultCode, Toast.LENGTH_SHORT).show();

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode==Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());

                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getContentResolver(), data.getData());
                    ImageView image = (ImageView)findViewById(R.id.imageView1);

                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);


                    //Toast.makeText(getBaseContext(), "name_Str : "+name_Str , Toast.LENGTH_SHORT).show();


                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

}