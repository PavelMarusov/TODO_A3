package com.intro;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

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
        TextView textView;
        textView =v.findViewById(R.id.text_fgm);
        final int pos = getArguments().getInt("position");
        switch (pos){
            case 0:
                textView.setText("Fragment1");
                break;
                case 1:
                textView.setText("Fragment2");
                break;
                case 2:
                textView.setText("Fragment3");
                break;
        }


        return v;
    }
}
