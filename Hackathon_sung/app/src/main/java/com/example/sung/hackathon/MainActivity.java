package com.example.sung.hackathon;

import android.content.Intent;
import android.content.SharedPreferences;
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
import android.view.View;
import android.view.Window;
import android.widget.Button;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import android.view.LayoutInflater;
import android.graphics.Color;
import android.os.Build;
import android.app.Dialog;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TabLayout tabLayout;
    Intent intent;
    SharedPreferences pref;
    private String cashID;
    public static String session = null;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        intent=getIntent();

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
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_cold);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_flozen);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_recipe);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        TextView session=(TextView)findViewById(R.id.nav_id);
        session.setText(MainActivity.session.toString()+"  님");
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

            // Handle the camera action
        } else if (id == R.id.nav_post) {

        }  else if (id == R.id.nav_tools) {


        } else if (id == R.id.nav_logout) {
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





}
