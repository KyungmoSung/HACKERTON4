package com.example.sung.hackathon;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    Intent intent;
    SharedPreferences pref;
    private String cashID;
    private BackPressCloseHandler backPressCloseHandler;
    private PendingIntent pendingIntent;
    public static String session = null;
    AlarmManager alarmManager;

    DBAdapter db;
    boolean dbOpen;
    Cursor currentcursor;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        pref=getSharedPreferences("PreFer",0);
        String cashID=pref.getString("ID","");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent=getIntent();
        db = new DBAdapter(this);
        db.open();
        dbOpen = true;
        currentcursor=db.fetchAllRefrigerator(cashID);
        currentcursor.moveToFirst();

        int i=0;
        for(int j=0;j<currentcursor.getCount();j++)
        {
            AlarmHATT a = new AlarmHATT(this);
            String day = currentcursor.getString(3);
            int Y,M,D;
            Y= Integer.parseInt(day.substring(0,4));
            M= Integer.parseInt(day.substring(5,7));
            D= Integer.parseInt(day.substring(8,10));

            a.Alarm(i,Y,M,D);
            i++;
        }
        currentcursor=db.fetchAllfreezer(cashID);
        currentcursor.moveToFirst();
        for(int j=0;j<currentcursor.getCount();j++)
        {
            AlarmHATT a = new AlarmHATT(this);
            String day = currentcursor.getString(3);
            int Y,M,D;
            Y= Integer.parseInt(day.substring(0,4));
            M= Integer.parseInt(day.substring(5,7));
            D= Integer.parseInt(day.substring(8,10));

            a.Alarm(i,Y,M,D);
            i++;
        }
        backPressCloseHandler = new BackPressCloseHandler(this);



        // Intent i = new Intent(MainActivity.this,MyService.class);
        //startService(i);


        setCashID(intent.getStringExtra("cashID"));

        pref = getSharedPreferences("PreFer", 0);
        //pref에 불려진 자료를 수정할 수 있게 불러옴
        SharedPreferences.Editor editor = pref.edit();
        //값 수정
        editor.putString("ID", cashID);
        //저장
        editor.commit();
        //Toast.makeText(this,cashID,Toast.LENGTH_SHORT).show();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("냉장고"));
        tabLayout.addTab(tabLayout.newTab().setText("냉동실"));
        tabLayout.addTab(tabLayout.newTab().setText("레시피"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        final PagerAdapter Padapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(Padapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        setupTabIcons();
    }
    public void stopServiceMethod(){
        Intent Service = new Intent(this, MyService.class);
        stopService(Service);
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cold);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_flozen);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recipe);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        /*if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }*/
        backPressCloseHandler.onBackPressed();
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView session=(TextView)findViewById(R.id.nav_id);
        session.setText(MainActivity.session+"  님");
        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();


        if (id == R.id.nav_account) {
            Intent in = new Intent(MainActivity.this, UserOut.class);
            startActivity(in);
        } else if (id == R.id.nav_post) {

        }  else if (id == R.id.nav_tools) {


        } else if (id == R.id.nav_logout) {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);

            SharedPreferences pref = getSharedPreferences("PreFer", 0);
            //pref에 불려진 자료를 수정할 수 있게 불러옴
            SharedPreferences.Editor editor = pref.edit();
            //값 수정
            editor.putString("ID", "");
            //저장
            editor.commit();
            startActivity(intent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public String getCashID()
    {
        return cashID;
    }
    public void setCashID(String s)
    {
        cashID=s;
    }
    public  SharedPreferences getPrefer()
    {
        return pref;
    }


    public class AlarmHATT {
        private Context context;
        public AlarmHATT(Context context) {
            this.context=context;
        }
        public void Alarm(int i,int Y,int M,int D) {
            AlarmManager am = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
            Intent intent = new Intent(MainActivity.this, MyService.class);

            PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, i, intent, 0);

            Calendar calendar = Calendar.getInstance();
            //알람시간 calendar에 set해주기

            calendar.set(Y,M-1,D-1,0,10,0);

            //알람 예약
            am.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), sender);
        }

        public void AlarmCancel()
        {
            Intent intent = new Intent(MainActivity.this, MyService.class);
            PendingIntent sender = PendingIntent.getBroadcast(MainActivity.this, 0, intent, 0);

            AlarmManager am = (AlarmManager)getSystemService(ALARM_SERVICE);

            am.cancel(sender);
        }


    }

}
