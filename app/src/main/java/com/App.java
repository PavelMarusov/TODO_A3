package com;

import android.app.Application;

import com.data.BoredApiClient;

public class App extends Application {
public static BoredApiClient boredApiClient;
    @Override
    public void onCreate() {
        super.onCreate();
        boredApiClient = new BoredApiClient();
    }

}
