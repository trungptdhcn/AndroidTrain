package com.example.OnlineDio.nevigation;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.example.OnlineDio.R;

/**
 * Created with IntelliJ IDEA.
 * User: Trung
 * Date: 14/10/2013
 * Time: 14:00
 * To change this template use File | Settings | File Templates.
 */
public class NavigationActivity extends SherlockFragmentActivity
{
    final String[] data ={"one","two","three"};
    final String[] fragments =
            {
                    "com.example.OnlineDio.HomeFragment",
            };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_ACTION_BAR);
        setContentView(R.layout.navigation);
        Log.e(this.getPackageName().toString(),"Yes");
        getSupportActionBar().hide();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getSupportActionBar().getThemedContext(), android.R.layout.simple_list_item_1, data);

        final DrawerLayout drawer = (DrawerLayout)findViewById(R.id.navigation_drawer_layout);
        final ListView navList = (ListView) findViewById(R.id.navigation_lvDrawer);
        navList.setAdapter(adapter);
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int pos,long id){
                drawer.setDrawerListener( new DrawerLayout.SimpleDrawerListener(){
                    @Override
                    public void onDrawerClosed(View drawerView){
                        super.onDrawerClosed(drawerView);
                        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
                        tx.replace(R.id.navigation_main_FrameLayout, Fragment.instantiate(NavigationActivity.this, fragments[pos]));
                        tx.commit();
                    }
                });
                drawer.closeDrawer(navList);
            }
        });
        FragmentTransaction tx = getSupportFragmentManager().beginTransaction();
        tx.replace(R.id.navigation_main_FrameLayout,Fragment.instantiate(NavigationActivity.this, fragments[0]));
        tx.commit();
    }
}
