package com.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.LinearGradient;
import android.os.Bundle;
import android.util.Log;
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
import com.example.todoa3.R;
import com.google.android.material.progressindicator.ProgressIndicator;
import com.intro.IntroActivity;
import com.model.BoredAction;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private String type;
    private TextView quest;
    private TextView price;
    private Button next;
    private BoredAction boredAction;
    private ImageView imageView;
    private int participants;
    private float startPrice;
    private float endPrice;
    private ProgressBar progressBar;
    private float startAcces;
    private float endAccess;
    private SimpleRangeView seekbar;
    private SimpleRangeView seekba_access;

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
//        boredAction = new BoredAction(null,type,null,null,null,null,null);
        quest = findViewById(R.id.quest);
        price = findViewById(R.id.price);
        imageView = findViewById(R.id.acces_imageView);
        next = findViewById(R.id.next_btn);
        spinner = findViewById(R.id.spiner);
        seekbar = findViewById(R.id.price_sb);
        progressBar = findViewById(R.id.progress_bar);
        seekba_access = findViewById(R.id.accesibility_sb);
        inputPrise(seekbar);
        inputAccessibility(seekba_access);
        spinner.setOnItemSelectedListener(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.boredApiClient.getAction(type, startPrice, endPrice, new BoredApiClient.BoredActionCallback() {

                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        Log.d("pop", "Accessibility :" + startAcces);
                        quest.setText(boredAction.getActivity());
                        price.setText(boredAction.getPrice());
                        progressBar.setProgress((int) (boredAction.getAccessibility()*100));
                        Log.d("pop", "kolichestvo " + boredAction.getParticipants());
                        Log.d("pop", "cena :" + boredAction.getPrice());
                        participants = boredAction.getParticipants();
                        setImage(participants);
                        Log.d("pop", boredAction.toString());
                    }

                    @Override
                    public void onFailure(Exception ex) {
                        Toast.makeText(MainActivity.this, "Error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("pop", ex.getMessage());
                    }

                });
            }
        });


    }


    public void setImage(int pos) {

        if (pos > 3) {
            imageView.setImageResource(R.drawable.ic_group);
        } else {
            switch (pos) {
                case 1:
                    imageView.setImageResource(R.drawable.ic_user_icon);
                    break;
                case 2:
                    imageView.setImageResource(R.drawable.ic_user_icon_two);
                    break;
                case 3:
                    imageView.setImageResource(R.drawable.ic_user_icon_three);
                    break;

            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        type = parent.getSelectedItem().toString();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void inputPrise(SimpleRangeView view) {
        Log.d("pop","inputPrise");
        view.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
               startPrice=(float) (i/10);

                Log.d("pop", "Start price ;" + startPrice);


            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                endPrice = (float) (i/10);
                Log.d("pop", "endPrice ;" + endPrice);
            }
        });
    }

    public void inputAccessibility(SimpleRangeView view) {
        view.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                startAcces = i;
                startAcces = 0.1f;
                Log.d("pop", "Start price ;" + startPrice);
            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                endAccess = i;
                endAccess = 1f;
                Log.d("pop", "End price ;" + endPrice);
            }
        });
    }
}
