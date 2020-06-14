package com.example.intro;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.todoa3.MainActivity;
import com.example.todoa3.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IntroFragment extends Fragment {

    public IntroFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_intro, container, false);
        Button skip,next,start;
        TextView textView;
        textView =v.findViewById(R.id.text_fgm);
        skip = v.findViewById(R.id.skip_btn);
        next = v.findViewById(R.id.next_btn);
        start = v.findViewById(R.id.start_btn);
        final int pos = getArguments().getInt("position");
        switch (pos){
            case 0:
                textView.setText("Fragment1");
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                });
                next.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                break;
                case 1:
                textView.setText("Fragment2");
                skip.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                });
                break;
                case 2:
                textView.setText("Fragment3");
                skip.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                start.setVisibility(View.VISIBLE);
                start.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getContext(), MainActivity.class));
                    }
                });
                break;
        }


        return v;
    }
}
