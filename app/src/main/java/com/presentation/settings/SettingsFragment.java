package com.presentation.settings;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todoa3.R;
import com.presentation.favorites.FavoritesFragment;

public class SettingsFragment extends Fragment {

    public static Fragment newInstance(){
        return new SettingsFragment();
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }
}
