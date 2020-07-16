package com.presentation.favorites;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.App;
import com.example.todoa3.R;
import com.model.BoredAction;

import java.util.ArrayList;
import java.util.List;

public class FavoritesFragment extends Fragment {

public static Fragment newInstance(){
    return new FavoritesFragment();
}
private AdapterFavorites adapter;
private RecyclerView recyclerView;
private List<BoredAction> actionList;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_favorites, container, false);
        recyclerView = v.findViewById(R.id.favorite_recycler);
        String key = getArguments().getString("action");
        actionList = new ArrayList<>();
        adapter =  new AdapterFavorites(actionList);
        recyclerView.setAdapter(adapter);
        return v;
    }
}
