package com.example.intro;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.todoa3.MainActivity;
import com.example.todoa3.R;

public class IntroActivity extends AppCompatActivity {
    ViewPager pager;
    ViewPagerAdapter viewPagerAdapter;
    Button next, skip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        pager = findViewById(R.id.viewpager);
        pager.setAdapter(viewPagerAdapter);
        next = findViewById(R.id.next_btn);
        skip = findViewById(R.id.skip_btn);
        nextClick();
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("pop","onClick next");
                pager.setCurrentItem(pager.getCurrentItem() + 1, true);
            }
        });
        skip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroActivity.this, MainActivity.class));
                finish();
            }
        });

    }

    public void nextClick() {

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                if (pager.getCurrentItem() == 2) {
                    Log.d("pop","if uslovie");
                    skip.setText("Start");
                    skip.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            startActivity(new Intent(IntroActivity.this, MainActivity.class));
                            finish();
                        }
                    });
                    next.setVisibility(View.INVISIBLE);

                } else {
                    skip.setText("Skip");
                    next.setVisibility(View.VISIBLE);

                }
            }
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    public class ViewPagerAdapter extends FragmentPagerAdapter {

        public ViewPagerAdapter(@NonNull FragmentManager fm) {
            super(fm, ViewPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            IntroFragment fragment = new IntroFragment();
            Bundle bundle = new Bundle();
            bundle.putInt("position", position);
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public int getCount() {
            return 3;
        }


    }
}
