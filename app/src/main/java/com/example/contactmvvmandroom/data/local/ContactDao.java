package com.example.contactmvvmandroom.data.local;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.contactmvvmandroom.data.Models.ModelUser;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface ContactDao {
    // function of DataBase
    // ------------------------------

    // take list to insert in its parameter
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable insertUser(ModelUser modelUser);


    // return list of user
    @Query("select * from contact_database")
    Single<List<ModelUser>> showAllContact();

    @Query("update contact_database set first_name = :firstName , last_name = :lastName , number = :phone where id = :id ")
    Completable updateUser(String firstName, String lastName, String phone, int id);

    @Delete
    Completable deleteUser(ModelUser modelUser);
}
