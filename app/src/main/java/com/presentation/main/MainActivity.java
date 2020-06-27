package com.presentation.main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.App;
import com.data.BoredApiClient;
import com.example.todoa3.R;
import com.intro.IntroActivity;
import com.model.BoredAction;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Spinner spinner;
    private String type;
    private TextView quest;
    private Button next;
    private BoredAction boredAction;
    private ImageView imageView;
    private int participants;

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
        imageView = findViewById(R.id.acces_imageView);
        next = findViewById(R.id.next_btn);
        spinner = findViewById(R.id.spiner);
        spinner.setOnItemSelectedListener(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.boredApiClient.getAction(type,new BoredApiClient.BoredActionCallback() {

                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        quest.setText(boredAction.getActivity());
                        Log.d("pop","kolichestvo "+boredAction.getParticipants());
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
public void setImage(int pos){

        if (pos>3){
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
}
