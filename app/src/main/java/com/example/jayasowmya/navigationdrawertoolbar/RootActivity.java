package com.example.jayasowmya.navigationdrawertoolbar;

import android.content.res.Configuration;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

/**
 * Created by jayasowmya on 6/8/16.
 */
public class RootActivity extends AppCompatActivity {

    protected String[] mNavigationDrawerItemTitles;
    protected DrawerLayout mDrawerLayout;
    protected ListView mDrawerList;
    protected Toolbar toolbar;
    protected android.support.v7.app.ActionBarDrawerToggle mDrawerToggle;

    protected void initialiseDrawerLayout() {

        mNavigationDrawerItemTitles= getResources().getStringArray(R.array.navigation_drawer_items_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        DataModel[] drawerItem = new DataModel[6];

        drawerItem[0] = new DataModel(R.drawable.loginicon, "Login");
        drawerItem[1] = new DataModel(R.drawable.mypageicon, "My Page");
        drawerItem[2] = new DataModel(R.drawable.scheduleicon, "Schedule");
        drawerItem[3] = new DataModel(R.drawable.pinnedicon, "Pinned Events");
        drawerItem[4] = new DataModel(R.drawable.loyaltyicon, "Loyalty");
        drawerItem[5] = new DataModel(R.drawable.paymenticon, "Payment");

        DrawerItemCustomAdaptor adapter = new DrawerItemCustomAdaptor(this, R.layout.list_view_item_row, drawerItem);
        mDrawerList.setAdapter(adapter);
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

        setupDrawerToggle();

        if (mDrawerLayout != null) {
            // Set a custom shadow that overlays the main content when the drawer opens
            mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
            // Enable ActionBar app icon to behave as action to toggle nav drawer
            getSupportActionBar().setHomeButtonEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mDrawerLayout.setDrawerListener(mDrawerToggle);
        }
    }

    protected void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                fragment = new LoginFragment();
                break;
            case 1:
                fragment = new MyPageFragment();
                break;
            case 2:
                fragment = new ScheduleFragment();
                break;
            case 3:
                fragment = new EventsFragment();
                break;
            case 4:
                fragment = new LoyaltyFragment();
                break;
            case 5:
                fragment = new PaymentFragment();
                break;

            default:
                break;
        }



        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, fragment).commit();

            mDrawerList.setItemChecked(position, true);
            mDrawerList.setSelection(position);
            setTitle(mNavigationDrawerItemTitles[position]);
            int currOrientation = getResources().getConfiguration().orientation;
            if(currOrientation == Configuration.ORIENTATION_PORTRAIT) {
                mDrawerLayout.closeDrawer(mDrawerList);
            }


        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    protected void setupToolbar(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected void setupDrawerToggle(){
        mDrawerToggle = new android.support.v7.app.ActionBarDrawerToggle(this,mDrawerLayout,toolbar,R.string.app_name, R.string.app_name);
        //This is necessary to change the icon of the Drawer Toggle upon state change.
        mDrawerToggle.syncState();
    }

    protected void updateLayout() {
        int currOrientation = getResources().getConfiguration().orientation;
        if(currOrientation == Configuration.ORIENTATION_LANDSCAPE) {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_OPEN);
        }
        else{
            mDrawerLayout.closeDrawer(mDrawerList);
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

}
