package com.presentation.main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.App;
import com.data.BoredApiClient;
import com.data.Pref;
import com.example.todoa3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.intro.IntroActivity;
import com.model.BoredAction;
import com.presentation.favorites.FavoritesFragment;
import com.presentation.settings.SettingsFragment;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private BottomNavigationView mBottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        boolean isShow = Pref.getInstance(this).isShown();
        if (!isShow) {
            startActivity(new Intent(this, IntroActivity.class));
            finish();
            return;
        }
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.main_view_pager);
        mBottomNavigationView = findViewById(R.id.main_botton_nav);
        mViewPager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        mViewPager.setOffscreenPageLimit(2);
        mBottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.nav_main:
                                mViewPager.setCurrentItem(0);
                                break;
                                case R.id.nav_favorites:
                                    mViewPager.setCurrentItem(1);
                                break;
                                case R.id.nav_settings:
                                    mViewPager.setCurrentItem(2);
                                break;
                        }
                        return false;
                    }
                }
        );


    }


    public static class MainPagerAdapter extends FragmentPagerAdapter {


        public MainPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            Fragment fragment = new Fragment();
            switch (position) {
                case 0:
                    fragment = MainFragment.newInstance();
                    break;
                case 1:
                    fragment = FavoritesFragment.newInstance();
                    break;
                case 2:
                    fragment = SettingsFragment.newInstance();
                    break;
            }
return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }
    }
}

