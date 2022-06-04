package com.example.contactmvvmandroom.data.local;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.contactmvvmandroom.data.Models.ModelUser;


@Database(entities = {ModelUser.class}, version = 1)
public abstract class MyRoomDatabase extends RoomDatabase {
    public static MyRoomDatabase myRoomDatabase;

    public abstract ContactDao getDao();

    public static synchronized void initRoom(Context context) {
        if (myRoomDatabase == null) {
            myRoomDatabase = Room.
                    databaseBuilder(context, MyRoomDatabase.class, "Contact_DataBase")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }
    }

    public static MyRoomDatabase getInstance() {
        return myRoomDatabase;
    }


}
