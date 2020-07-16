package com;

import android.app.Application;

import androidx.room.Room;

import com.data.BoredApiClient;
import com.data.db.BoredDataBase;
import com.data.local.BoredStorage;

public class App extends Application {
public static BoredApiClient boredApiClient;
public  static BoredDataBase boredDataBase;
public static BoredStorage boredStorage;
    @Override
    public void onCreate() {
        super.onCreate();
        boredApiClient = new BoredApiClient();
        boredDataBase = Room.databaseBuilder(this,
                BoredDataBase.class ,
                "bored_bd")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();

        boredStorage = new BoredStorage(boredDataBase.boredDao());
    }

}
