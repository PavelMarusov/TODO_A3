package com.data;

import android.content.Context;
import android.content.SharedPreferences;

public class Pref {
    public static final String PREF_KEY = "isShown";
    private SharedPreferences preferences;
    public static volatile Pref instance;

    public Pref(Context context) {
        instance = this;
        preferences = context.getSharedPreferences("setting", Context.MODE_PRIVATE);
    }

    public static Pref getInstance (Context context){
        if (instance==null){
            new Pref(context); }
        return instance;
    }
    public boolean isShown(){
        return preferences.getBoolean(PREF_KEY,false);
    }
    public void saveShow(){
        preferences.edit().putBoolean(PREF_KEY,true).apply();
    }
}
