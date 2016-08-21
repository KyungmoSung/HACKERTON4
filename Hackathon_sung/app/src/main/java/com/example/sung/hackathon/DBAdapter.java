package com.example.sung.hackathon;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBAdapter {
    private final Context context;
    static final String DB = "sample";
    static final String TABLE = "member";
    static final String TABLE2 = "refrigerator";
    static final String TABLE3 = "material";
    static final String TABLE4 = "r_material";
    static final String TABLE5 = "r_hash";
    static final String TABLE6 = "recipe";
    static final String TABLE7 = "freezer";
    // table field name
    public static final String DBTBL_ID    = "_id";	// long
    public static final String DBTBL_NAME  = "name";	// text
    public static final String DBTBL_TEL   = "tel";	// text

    //////////////////////////////////////////////////////////////////
    static final int DB_VERSION = 1;
    //////////////////////////////////////////////////////////////////
    static final String CREATE = "create table if not exists member (id text primary key not null,password text not null,name text,phone text,address text );"; //회원 테이블
    static final String CREATE3 = "create table if not exists refrigerator (id text REFERENCES member (id) not null, m_name text primary key not null,number integer,life date,icon integer);"; //냉장고 테이블
    static final String CREATE2 = "create table if not exists r_contents (r_num references recipe(r_num),contents text,image text,sequence integer);"; //재료 테이블
    static final String CREATE6 = "create table if not exists r_material (r_num references recipe(r_num) ,m_name text );"; //레시피의 해시태그 테이블
    static final String CREATE5 = "create table if not exists r_hash (r_num references recipe(r_num) ,hash text primary key);"; //레시피의재료 테이블
    static final String CREATE4 = "create table if not exists recipe (r_num integer primary key autoincrement,id text references member(id) not null,title text, food text, food2 text , content text);"; ///레시피 테이블
    static final String CREATE7 = "create table if not exists freezer (id text REFERENCES member (id) not null, m_name text primary key not null,number integer,life date,icon integer);";

    ///////////////////////////////////////////////////////////////////
    static final String DROP = "drop table ";
    private SQLiteDatabase db;
    private OpenHelper dbHelper;

    public DBAdapter(Context ctx) {
        context = ctx;
    }




    private static class OpenHelper extends SQLiteOpenHelper {
        public OpenHelper(Context c) {
            super(c, DB, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE);
            db.execSQL(CREATE2);
            db.execSQL(CREATE3);
            db.execSQL(CREATE4);
            db.execSQL(CREATE5);
            db.execSQL(CREATE6);
            db.execSQL(CREATE7);
        }

        public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
            db.execSQL(DROP + TABLE);
            db.execSQL(DROP + TABLE2);
            db.execSQL(DROP + TABLE3);
            db.execSQL(DROP + TABLE4);
            db.execSQL(DROP + TABLE5);
            db.execSQL(DROP + TABLE6);
            db.execSQL(DROP + TABLE7);
            db.execSQL(CREATE);
            db.execSQL(CREATE2);
            db.execSQL(CREATE3);
            db.execSQL(CREATE4);
            db.execSQL(CREATE5);
            db.execSQL(CREATE6);
            db.execSQL(CREATE);
            db.execSQL(CREATE7);
        }
    }

    public DBAdapter open() throws SQLException {
        dbHelper = new OpenHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }
    public Cursor fetchAllData() {
        return db.query(TABLE, null, null, null, null, null, null);
    }
    //
    public Cursor fetchAllRefrigerator(String cashID) {
        String query="select * from refrigerator where id="+cashID+";";
        return db.rawQuery("select * from refrigerator where id='"+cashID+"'",null);
    }
    public Cursor fetchAllfreezer(String cashID) {
        String query="select * from freezer where id="+cashID+";";
        return db.rawQuery("select * from freezer where id='"+cashID+"'",null);
    }
    public long addMember(String id,String password,String name,String phone,String address) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("password",password);
        values.put("name", name);
        values.put("phone", phone);
        values.put("address", address);
        long l = db.insert("member", null, values);
        return l; //실패시 -1반환
    }
    public long addRefrigerator(String id,String m_name,int number,String life,int icon) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("m_name",m_name);
        values.put("number", number);
        values.put("life", life);
        values.put("icon",icon);
        long l = db.insert("refrigerator", null, values);
        return l;
    }

    public void delRefrigerator(String id,String m_name) { //재료삭제
        String  str="delete from refrigerator where id='"+id+"'AND m_name ='"+m_name+"';";
        db.execSQL(str);
    }
    public void modifyRefrigerator(String id,String m_name,String new_name,int number,String life,int icon) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("m_name",new_name);
        values.put("number",number);
        values.put("life",life);
        values.put("icon",icon);
        db.update("refrigerator", values, "id='" + id + "'AND m_name = '"+m_name+"'", null);
    }
    public long addfreezer(String id,String m_name,int number,String life,int icon) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("m_name",m_name);
        values.put("number", number);
        values.put("life", life);
        values.put("icon",icon);
        long l = db.insert("freezer", null, values);
        return l;
    }
    public void defreezer(String id,String m_name) { //재료삭제
        String  str="delete from freezer where id='"+id+"'AND m_name ='"+m_name+"';";
        db.execSQL(str);
    }
    public void modifyfreezer(String id,String m_name,String new_name,int number,String life,int icon) {
        ContentValues values = new ContentValues();
        values.put("id", id);
        values.put("m_name",new_name);
        values.put("number",number);
        values.put("life",life);
        values.put("icon",icon);
        db.update("freezer", values, "id='" + id + "'AND m_name = '"+m_name+"'", null);
    }

    public long recipe(String title,String _food,String _food2,String _content){ //레시피 추가
        ContentValues values = new ContentValues();
        values.put("_recipetitle",title);
        values.put("_food",_food);
        values.put("_food2",_food2);
        values.put("_content",_content);
        String id;

        String  str="insert into recipe(id,title,food,food2,content) values('"+MainActivity.session+"','"+title+"','"+_food+"','"+_food2+"','"+_content+"');";
        db.execSQL(str);
        return 1;
    }

    public String login(String _id,String _password) //로그인 성공시 id반환 실패시 false 반환
    {
        String id,password;
        Cursor cursor = db.rawQuery("select * from member ",null);
        int count =cursor.getCount();
        cursor.moveToFirst();
        for(int i=0;i<count;i++)
        {
            id=cursor.getString(cursor.getColumnIndex("id"));
            password=cursor.getString(cursor.getColumnIndex("password"));
            if(_id.equals(id) && _password.equals(password))
            {
                return _id;
            }
            cursor.moveToNext();
        }

        return "false";
    }

    public int idcheck(String _id) //로그인 성공시 id반환 실패시 false 반환
    {
        String id;
        Cursor cursor = db.rawQuery("select * from member ",null);
        int count =cursor.getCount();
        cursor.moveToFirst();
        for(int i=0;i<count;i++)
        {
            id=cursor.getString(cursor.getColumnIndex("id"));

            if(_id.equals(id))
            {
                return 0;
            }
            cursor.moveToNext();
        }

        return 1;
    }
    /*
    //
    public void delData(String id) {
        db.delete(TABLE, "_id = ?", new String[] {id});
    }
    //
    public Cursor searchDataByName(String name) {
        return db.query(TABLE, null, DBTBL_NAME + " = '" + name + "'", null, null, null, null);
    }
    //
    public void modifyData(long id, String name, String tel) {
        ContentValues values = new ContentValues();
        values.put(DBTBL_NAME, name);
        values.put(DBTBL_TEL, tel);
        db.update(TABLE, values, "_id='" + id + "'", null);
    }*/

    public void delData(String id) {

        String str="delete from Member where id='"+id+"';";
        db.execSQL(str);
    }


}
