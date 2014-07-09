package com.project.kws.kotakwarnastudioui.activity;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.project.kws.kotakwarnastudioui.R;
import com.project.kws.kotakwarnastudioui.adapter.CustomDrawerAdapter;
import com.project.kws.kotakwarnastudioui.adapter.CustomDrawerAdapterItem;
import com.project.kws.kotakwarnastudioui.fragment.ItemListView;
import com.project.kws.kotakwarnastudioui.fragment.ItemView;

import java.util.ArrayList;

/**
 * Created by Fajar on 7/4/2014.
 */
public class Drawer_LeftMenu_Impl extends ActionBarActivity implements Drawer_LeftMenu{

    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private ListView lv_drawer;
    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.a_drawerlayout_leftmenu);
        setCustomActionBar();
         setDrawer();

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        ItemListView itemListView = new ItemListView();
        if(!itemListView.isInLayout()){
            fragmentTransaction.add(R.id.content_frame, itemListView, ItemListView.TAG);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void setTitle(CharSequence title) {
        getActionBar().setTitle(title);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        drawerToggle.syncState();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        // Handle your other action bar items...

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void setCustomActionBar(){
        getSupportActionBar().setCustomView(R.layout.actionbar_layout);
        getSupportActionBar().setDisplayOptions(android.support.v7.app.ActionBar.DISPLAY_SHOW_CUSTOM | android.support.v7.app.ActionBar.DISPLAY_SHOW_HOME | android.support.v7.app.ActionBar.DISPLAY_HOME_AS_UP);
        getSupportActionBar().setIcon(
                new ColorDrawable(getResources().getColor(android.R.color.transparent)));
        getSupportActionBar().setBackgroundDrawable(null);

    }

    private void setDrawer(){

        drawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout_leftmenu);
        drawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                R.drawable.ic_drawer,
                R.string.open_drawer,
                R.string.close_drawer
        ){
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        drawerLayout.setDrawerShadow(R.drawable.ic_drawer_shadow, GravityCompat.START);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        String[] drawerList = getResources().getStringArray(R.array.drawer_menu);
        TypedArray imgs = getResources().obtainTypedArray(R.array.drawer_menu_icon);
        ArrayList<CustomDrawerAdapterItem> drawerItems = new ArrayList<CustomDrawerAdapterItem>();
        int i = 0;
        for(String title:drawerList){
            CustomDrawerAdapterItem customDrawerItem = new CustomDrawerAdapterItem();
            customDrawerItem.setTitle(title);
            customDrawerItem.setIcon(imgs.getResourceId(i, -1));
            drawerItems.add(customDrawerItem);
            i++;
        }
        lv_drawer = (ListView) findViewById(R.id.lv_drawer);
        int width = ((getResources().getDisplayMetrics().widthPixels)*4)/5;
        DrawerLayout.LayoutParams params = (android.support.v4.widget.DrawerLayout.LayoutParams) lv_drawer.getLayoutParams();
        params.width = width;
        lv_drawer.setLayoutParams(params);
        lv_drawer.setAdapter(new CustomDrawerAdapter(getApplicationContext(), drawerItems));
        lv_drawer.setOnItemClickListener(new DrawerItemClickListener());
    }

    @Override
    public void onChangeFragment() {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_right, R.animator.slide_out_left);
        ItemView itemView = new ItemView();
        if(!itemView.isInLayout()){
            ft.replace(R.id.content_frame, itemView, ItemView.TAG);
            ft.addToBackStack(null);
            ft.commit();
        }
    }

    @Override
    public void onChangeActivity() {
        Intent intent = new Intent(getApplicationContext(), Search_Menu.class);
        startActivity(intent);
    }

    @Override
    public void onChangeFragmentLeftRightCursor(boolean side) {

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if(side){
            ft.setCustomAnimations(R.animator.slide_in_left, R.animator.slide_out_right, R.animator.slide_in_right, R.animator.slide_out_left);
        }else{
            ft.setCustomAnimations(R.animator.slide_in_right, R.animator.slide_out_left, R.animator.slide_in_left, R.animator.slide_out_right);
        }
        ItemView itemView = new ItemView();
        if(!itemView.isInLayout()){
            ft.addToBackStack(null);
            ft.replace(R.id.content_frame, itemView, ItemView.TAG);
            ft.commit();
        }
    }

    @Override
    public void onBackPressed(){
        FragmentManager fm = getFragmentManager();
        if (fm.getBackStackEntryCount() > 0) {
            fm.popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        if(position==0){
            fragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();
            lv_drawer.setItemChecked(position, true);
            ItemListView itemListView = new ItemListView();
            if(!itemListView.isInLayout()){
                ft.replace(R.id.content_frame, itemListView, ItemListView.TAG);
                ft.commit();
            }
        }
        drawerLayout.closeDrawer(lv_drawer);
    }
 }
