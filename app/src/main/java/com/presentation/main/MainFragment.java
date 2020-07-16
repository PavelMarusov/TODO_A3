package com.presentation.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.model.BoredAction;

import org.jetbrains.annotations.NotNull;

import me.bendik.simplerangeview.SimpleRangeView;

/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment  implements AdapterView.OnItemSelectedListener {
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
    private ImageView likeImageView;

    public MainFragment() {
        // Required empty public constructor
    }

    public static Fragment newInstance() {
        return new MainFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_main, container, false);

        quest = v.findViewById(R.id.quest);
        price = v.findViewById(R.id.price);
        likeImageView = v.findViewById(R.id.like_imageview);
        imageView = v.findViewById(R.id.acces_imageView);
        next = v.findViewById(R.id.next_btn);
        spinner = v.findViewById(R.id.spiner);
        seekbar = v.findViewById(R.id.price_sb);
        progressBar = v.findViewById(R.id.progress_bar);
        seekba_access = v.findViewById(R.id.accesibility_sb);
        inputPrise(seekbar);
        inputAccessibility(seekba_access);
        spinner.setOnItemSelectedListener(this);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                App.boredApiClient.getAction(type, startPrice, endPrice, new BoredApiClient.BoredActionCallback() {

                    @Override
                    public void onSuccess(BoredAction boredAction) {
                        quest.setText(boredAction.getActivity());
                        likeImageView.setImageResource(R.drawable.ic_favorite);
                        likeImageView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Log.d("pop", "Like on click");
                                likeImageView.setImageResource(R.drawable.ic_favorite_pressed);
                               App.boredStorage.saveBoredAction(boredAction);
                               String action = App.boredStorage.getBoredAction(boredAction.getKey()).toString();
                                Log.d("pop", "Receive ;" + boredAction.toString());
                                Log.d("pop", "Read from DB " + App.boredStorage.getBoredAction(boredAction.getKey()));
                            }
                        });
                        price.setText(boredAction.getPrice());
                        progressBar.setProgress((int) (boredAction.getAccessibility() * 100));
                        Log.d("pop", "kolichestvo " + boredAction.getParticipants());
                        Log.d("pop", "cena :" + boredAction.getPrice());
                        participants = boredAction.getParticipants();
                        setImage(participants);
                        Log.d("pop", boredAction.toString());
                    }

                    @Override
                    public void onFailure(Exception ex) {
                        Toast.makeText(getContext(), "Error" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                        Log.d("pop", ex.getMessage());
                    }

                });
            }
        });
        return v;
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
        Log.d("pop", "inputPrise");
        view.setOnTrackRangeListener(new SimpleRangeView.OnTrackRangeListener() {
            @Override
            public void onStartRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                startPrice = (float) (i / 10);

                Log.d("pop", "Start price ;" + startPrice);


            }

            @Override
            public void onEndRangeChanged(@NotNull SimpleRangeView simpleRangeView, int i) {
                endPrice = (float) (i / 10);
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
