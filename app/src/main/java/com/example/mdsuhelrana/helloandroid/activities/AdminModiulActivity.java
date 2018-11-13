package com.example.mdsuhelrana.helloandroid.activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.mdsuhelrana.helloandroid.R;
import com.example.mdsuhelrana.helloandroid.adminMdiulPackage.AdminCmpStatusFragment;
import com.example.mdsuhelrana.helloandroid.adminMdiulPackage.AdminMisspeopleStatusFragment;

public class AdminModiulActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_modiul);
        toolbar=findViewById(R.id.custom_toolbar_Id);
        toolbar.setTitle("Admin+");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        tabLayout=findViewById(R.id.andminTab_Id);
        viewPager=findViewById(R.id.andminViewPager_Id);
        tabLayout.addTab(tabLayout.newTab().setText("complain Status").setIcon(R.drawable.handcrufft));
        tabLayout.addTab(tabLayout.newTab().setText("missing Status").setIcon(R.drawable.handcrufft));
        AdminViewPager adapter=new AdminViewPager(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
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
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }
    public class AdminViewPager extends FragmentPagerAdapter {

        int count;

        public AdminViewPager(FragmentManager fm, int count) {
            super(fm);
            this.count = count;
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {

                case 0:
                    return new AdminCmpStatusFragment();

                case 1:
                    return new AdminMisspeopleStatusFragment();

            }
            return null;
        }

        @Override
        public int getCount() {
            return count;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int menuItem=item.getItemId();
        switch (menuItem){
            case R.id.menu_logout_Id:
                startActivity(new Intent(this,MainActivity.class));
                break;
            case R.id.menu_exit_Id:
                android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Exit Application?");
                alertDialogBuilder
                        .setMessage("Do you want to exit and automatically loged out!")
                        .setCancelable(false)
                        .setPositiveButton("Yes",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        moveTaskToBack(true);
                                        android.os.Process.killProcess(android.os.Process.myPid());
                                        System.exit(1);
                                    }
                                })

                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {

                                dialog.cancel();
                            }
                        });

                android.app.AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
