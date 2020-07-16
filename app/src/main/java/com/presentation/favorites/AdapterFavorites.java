package com.presentation.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todoa3.R;
import com.model.BoredAction;

import java.util.List;

public class AdapterFavorites extends RecyclerView.Adapter<AdapterFavorites.HolderFavorites> {
    private List<BoredAction> actionList;

    public AdapterFavorites(List<BoredAction> actionList) {
        this.actionList = actionList;
    }

    @NonNull
    @Override
    public HolderFavorites onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.list_favorites, parent, false);
        return new HolderFavorites(v);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderFavorites holder, int position) {
        holder.onBind(actionList.get(position));
    }

    @Override
    public int getItemCount() {
        return actionList.size();
    }

    public class HolderFavorites extends RecyclerView.ViewHolder {
        TextView textView;

        public HolderFavorites(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.favor_hold_text);
        }

        public void onBind(BoredAction action) {
            textView.setText(action.getActivity());
        }
    }
}
