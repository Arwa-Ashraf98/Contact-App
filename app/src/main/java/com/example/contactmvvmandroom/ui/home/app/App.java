package com.example.contactmvvmandroom.ui.home.app;

import android.app.Application;

import com.example.contactmvvmandroom.data.local.MyRoomDatabase;


public class App extends Application {
    // initialize room data base once application run


    @Override
    public void onCreate() {
        super.onCreate();
        MyRoomDatabase.initRoom(this);
    }
}
