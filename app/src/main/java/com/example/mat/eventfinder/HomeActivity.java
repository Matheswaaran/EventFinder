package com.example.mat.eventfinder;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.mat.eventfinder.Fragments.AddEventFragment;
import com.example.mat.eventfinder.Fragments.EditProfileFragment;
import com.example.mat.eventfinder.Fragments.HomeFragment;
import com.example.mat.eventfinder.Fragments.UserProfileFragment;
import com.example.mat.eventfinder.Fragments.ViewEventsFragment;
import com.example.mat.eventfinder.Fragments.ViewMyEventsFragment;
import com.google.firebase.auth.FirebaseAuth;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private FirebaseAuth firebaseAuth;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        firebaseAuth = FirebaseAuth.getInstance();

        if (firebaseAuth.getCurrentUser() == null){
            finish();
            startActivity(new Intent(getApplicationContext(),SignupActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        setTitle("Home");
        ft.replace(R.id.homeFrameLayout,new HomeFragment());
        ft.commit();
        navigationView.setCheckedItem(R.id.homeFragment);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                setTitle("Add a new Event");
                ft.replace(R.id.homeFrameLayout,new AddEventFragment());
                ft.commit();
                navigationView.setCheckedItem(R.id.addEvents);
            }
        });
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
        getMenuInflater().inflate(R.menu.home, menu);
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
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();

        if (id == R.id.homeFragment){
            setTitle("Home");
            ft.replace(R.id.homeFrameLayout, new HomeFragment());
        } else if (id == R.id.addEvents) {
            setTitle("Add a new Event");
            ft.replace(R.id.homeFrameLayout,new AddEventFragment());
        } else if (id == R.id.viewEvents) {
            setTitle("All Events");
            ft.replace(R.id.homeFrameLayout, new ViewEventsFragment());
        } else if(id == R.id.viewMyEvents){
            setTitle("My Events");
            ft.replace(R.id.homeFrameLayout, new ViewMyEventsFragment());
        } else if (id == R.id.userProfile) {
            setTitle("User Profile");
            ft.replace(R.id.homeFrameLayout, new UserProfileFragment());
        } else if (id == R.id.editProfile){
            setTitle("Edit User Profile");
            ft.replace(R.id.homeFrameLayout, new EditProfileFragment());
        } else if (id == R.id.userLogout) {
            firebaseAuth.signOut();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }

        ft.commit();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void LogoutClick(View view){
        firebaseAuth.signOut();
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void openEditProfile(View view){
        android.support.v4.app.FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.homeFrameLayout,new EditProfileFragment());
        ft.commit();
        navigationView.setCheckedItem(R.id.editProfile);
        setTitle("Edit User Profile");
    }
}