package com.example.apptest;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

/**
 * Class is not used anymore, default page set to HomeActivity
 */
public class Recommend extends AppCompatActivity {

    private SectionsAdapter mSectionsAdapter;
    private ViewPager mViewPager;

    private void launchSettings() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void launchHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    launchHome();
                    return true;
                case R.id.navigation_filter:
                    return true;
                case R.id.navigation_settings:
                    launchSettings();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);



    }

//    private void setupViewPager(ViewPager viewPager) {
//        SectionsAdapter adapter = new SectionsAdapter(getSupportFragmentManager());
//        adapter.addFrag(new HomeFragment());
//    }

}
