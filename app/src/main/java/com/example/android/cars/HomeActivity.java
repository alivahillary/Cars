package com.example.android.cars;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    TextView tvInfo;
    FloatingActionButton fab_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fab_btn= (FloatingActionButton)findViewById(R.id.fab);
        fab_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(HomeActivity.this,VehiclesActivity.class);
                startActivity(i);
            }
        });

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tvInfo = (TextView)findViewById(R.id.tv_info);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.drawer);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle  drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                toolbar, R.string.drawer_open, R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();


    }


    @Override
    public boolean onNavigationItemSelected( MenuItem menuItem) {

        String itemName =(String) menuItem.getTitle();
        tvInfo.setText(itemName);

        closeDrawer();

        switch(menuItem.getItemId()){
            case R.id.Vehicles:
             Intent i = new Intent(HomeActivity.this,VehiclesAddedActivity.class);
             startActivity(i);
                break;

            case R.id.Fuel:
                startActivity( new Intent(HomeActivity.this,FuelConsumptionActivity.class));
                break;

            case R.id.Dealerships:
                startActivity( new Intent(HomeActivity.this,WareHousesActivity.class));
                break;


            case R.id.Services:
                startActivity( new Intent(HomeActivity.this,ServiceActivity.class));
                break;

            case R.id.language:
                startActivity( new Intent(HomeActivity.this,LanguageActivity.class));
                break;


            case R.id.spareparts:
                startActivity( new Intent(HomeActivity.this,SparePartsActivity.class));
                break;

            case R.id.records:
                startActivity( new Intent(HomeActivity.this,MaintenanceActivity.class));
                break;





        }

        return true;
    }

    private void closeDrawer() {

        drawerLayout.closeDrawer(GravityCompat.START);
    }

    private void openDrawer(){

        drawerLayout.openDrawer(GravityCompat.START);
    }

    @Override
    public void onBackPressed() {

        if(drawerLayout.isDrawerOpen(GravityCompat.START)){

            closeDrawer();


        }



        super.onBackPressed();
    }
}
