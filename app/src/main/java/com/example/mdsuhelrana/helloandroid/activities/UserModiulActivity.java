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
import android.view.MenuItem;

import com.example.mdsuhelrana.helloandroid.R;
import com.example.mdsuhelrana.helloandroid.userModiulPackage.ComplainFragment;
import com.example.mdsuhelrana.helloandroid.userModiulPackage.ComplainStatusFragment;
import com.example.mdsuhelrana.helloandroid.userModiulPackage.MissingPeopleFragment;
import com.example.mdsuhelrana.helloandroid.userModiulPackage.MissingPeopleStatusFragment;

public class UserModiulActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_modiul);
        toolbar=findViewById(R.id.custom_toolbar_Id);
        toolbar.setTitle("User+");
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        viewPager=findViewById(R.id.userviewPager_Id);
        tabLayout=findViewById(R.id.userTablayout_Id);
        tabLayout.addTab(tabLayout.newTab().setText("complain").setIcon(R.drawable.complain_icon));
        tabLayout.addTab(tabLayout.newTab().setText("misspeople").setIcon(R.drawable.misspeople_icon));
        tabLayout.addTab(tabLayout.newTab().setText("cmpstatus").setIcon(R.drawable.complain_icon));
        tabLayout.addTab(tabLayout.newTab().setText("misstatus").setIcon(R.drawable.misspeople_icon));

        UserpagerAdapter adapter=new UserpagerAdapter(getSupportFragmentManager(),tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
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

    }
    public class UserpagerAdapter extends FragmentPagerAdapter{
        int totalTab;

        public UserpagerAdapter(FragmentManager fm, int totalTab) {
            super(fm);
            this.totalTab=totalTab;
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new ComplainFragment();
                case 1:
                    return new MissingPeopleFragment();
                case 2:
                    return new ComplainStatusFragment();
                case 3:
                    return new MissingPeopleStatusFragment();
            }
            return null;
        }
        @Override
        public int getCount() {
            return totalTab;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
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
