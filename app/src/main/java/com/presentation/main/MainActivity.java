package com.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.todoa3.R;
import com.intro.IntroActivity;

public class MainActivity extends AppCompatActivity {

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
    }
}
