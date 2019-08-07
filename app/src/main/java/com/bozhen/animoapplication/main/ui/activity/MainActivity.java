package com.bozhen.animoapplication.main.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.bozhen.animoapplication.R;
import com.bozhen.animoapplication.main.ui.fragment.fragmentDoctors;
import com.bozhen.animoapplication.main.ui.fragment.fragmentPharmacy;
import com.bozhen.animoapplication.main.ui.fragment.fragmentPlans;
import com.bozhen.animoapplication.main.ui.fragment.fragmentProfile;

import com.bozhen.animoapplication.main.ui.fragment.fragmentWorkWithPlans;
import com.google.android.material.navigation.NavigationView;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends MvpAppCompatActivity implements fragmentDoctors.OnFragmentInteractionListener
        ,NavigationView.OnNavigationItemSelectedListener {
    private static final String PHONE_KEY="PHONE_EXTRAS";
    public static Intent newInstance(Context context) {
        return new Intent(context,MainActivity.class);
    }


    @Override
    public void onFragmentInteraction(Fragment link, String tag) {
        replaceFragment(link,tag);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_profie) {
            fragment = fragmentProfile.newInstance();
        } else if (id == R.id.nav_doctors_list) {
            fragment = fragmentDoctors.newInstance();
        }
        else if (id == R.id.nav_pharmacy_list){
            fragment = fragmentPharmacy.newInstance();
        }
        else if(id==R.id.nav_plans_list){
            fragment =  fragmentPlans.newInstance();
        }
        else if(id == R.id.nav_plans_work)
            fragment = fragmentWorkWithPlans.newInstance();

        replaceFragment(fragment,fragment.getClass().getCanonicalName());
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void replaceFragment(Fragment fragment, String fragmentTag) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean isPopped = fragmentManager.popBackStackImmediate(fragmentTag, 0);
        if (!isPopped && fragmentManager.findFragmentByTag(fragmentTag) == null){
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.fragment_container, fragment, fragmentTag);
            ft.addToBackStack(fragmentTag);
            ft.commit();
        }
    }
}
